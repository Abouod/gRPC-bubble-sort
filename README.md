# gRPC Bubble-Sort Service Chain (Java servers + Python client)

Summary
- Demo of service chaining (Sorter → Passer → Swapper) implemented with gRPC.
- Three independent Java services (each runs on its own port):
  - SorterServer (orchestrator) — listens on 50051 and calls Passer at 50052  
    [`io.grpc.examples.bubblesort.SorterServer`](java/examples/src/main/java/io/grpc/examples/bubblesort/SorterServer.java)
  - PasserServer — listens on 50052 and calls Swapper at 50053  
    [`io.grpc.examples.bubblesort.PasserServer`](java/examples/src/main/java/io/grpc/examples/bubblesort/PasserServer.java)
  - SwapperServer — listens on 50053 and performs compare/swap  
    [`io.grpc.examples.bubblesort.SwapperServer`](java/examples/src/main/java/io/grpc/examples/bubblesort/SwapperServer.java)
- Python client sends an integer list to Sorter and receives the sorted list:
  [`python/bubble_sort/bubble_client.py`](python/bubble_sort/bubble_client.py)
- Protobuf: [`java/examples/src/main/proto/bubble.proto`](java/examples/src/main/proto/bubble.proto)

Prerequisites
- JDK (11+) and `javac` on PATH or set `JAVA_HOME`.
- Gradle wrapper is present in `java/`.
- Python 3 (venv recommended).
- Python packages: `grpcio` and `grpcio-tools` (for generating stubs if needed).

Build steps (Java)
1. From repository java/examples build the examples module:
   ```bash
   cd /java/examples
   ./gradlew installDist
    ```

Run servers (separate terminals)
- Start Swapper (port 50053):
  ```bash
  cd /java
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.bubblesort.SwapperServer
  ```
- Start Passer (port 50052):
  ```bash
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.bubblesort.PasserServer
  ```
- Start Sorter (port 50051):
  ```bash
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.bubblesort.SorterServer
  ```

Note: each server prints an INFO line at startup. The servers call each other over localhost and log per-RPC events when invoked.

Python client
- The generated Python stubs (if present) are in `python/bubble_sort/`:
  - `bubble_pb2.py`, `bubble_pb2_grpc.py`
- Run client:
  ```bash
  # activate your venv if using one
  cd /python/bubble_sort
  python3 bubble_client.py
  ```
- Expected output:
  - "Sending: [ ... ]" printed by client
  - Sorted result printed, e.g. `Sorted result from server: [1,2,3,...]`
  - Servers' terminals show logging lines proving the chain: Sorter → Passer → Swapper

Validation and debugging
- Verify ports are listening:
  ```bash
  ss -ltnp | grep -E ':50051|:50052|:50053'
  ```
- Use grpcurl to list and call services:
  ```bash
  grpcurl -plaintext localhost:50051 list
  grpcurl -plaintext localhost:50052 list
  grpcurl -plaintext localhost:50053 list

  # call Swapper directly
  grpcurl -plaintext -d '{"a":5,"b":3}' localhost:50053 bubble.Swapper/CompareSwap

  # call Passer directly
  grpcurl -plaintext -d '{"numbers":[5,3,8,1],"pass_index":0}' localhost:50052 bubble.Passer/DoPass

  # call Sorter directly
  grpcurl -plaintext -d '{"numbers":[5,3,8,1]}' localhost:50051 bubble.Sorter/BubbleSort
  ```
- Logs: servers log per-RPC info lines such as:
  - `SwapperImpl.compareSwap received (5,3)` and `returning (3,5)`
  - `PasserImpl.doPass invoked passIndex=0 list=[...]`
  - `SorterImpl.bubbleSort invoked with: [...]`

Common problems & fixes
- UNIMPLEMENTED / "Method not found": service not listening on the expected port or wrong target. Ensure target host/port in the Java ManagedChannel matches the server process and that you started the correct server.
- ClassNotFoundException when using Gradle start script: ensure you built from the Java project root (`./gradlew :examples:build :examples:installDist`) so the generated distribution contains the `examples.jar`.
- Python imports fail: run the client from the directory that contains `bubble_pb2.py` / `bubble_pb2_grpc.py`, or set `PYTHONPATH` accordingly.

Benchmarking (latency / throughput)
- Two small benchmark clients are included under `python/bubble_sort/`:
  - `bench_sync.py` — multithreaded blocking client (ThreadPoolExecutor)
  - `bench_async.py` — asyncio + `grpc.aio` client (concurrent async calls)

- Basic usage
  1. Start SwapperServer, PasserServer, SorterServer.
  2. Activate Python venv.
  3. Run a benchmark:
     ```bash
     # sync (blocking)
     python3 python/bubble_sort/bench_sync.py

     # async (aio)
     python3 python/bubble_sort/bench_async.py
     ```
  4. Scripts print: total requests, elapsed time, throughput (req/s), and latency percentiles (min/median/mean/p90/p99/max).

- Tuning parameters (edit top of bench files):
  - TOTAL_REQUESTS — number of requests to send
  - CONCURRENCY — parallel workers / concurrent tasks
  - PAYLOAD — list size to test CPU-bound behavior

- Using ghz (recommended for production-like load)
  ```bash
  ghz --insecure -n 10000 -c 50 -d '{"numbers":[5,3,8,1,2,9,4]}' localhost:50051 bubble.Sorter/BubbleSort
  ```
  ghz reports latency distribution, throughput and errors.

- Interpreting results
  - throughput ≈ successful_requests / elapsed_seconds
  - latency increases as you saturate CPU/threads; because services are chained sequentially, expect additional network round-trips vs in-process sorting.
  - the slowest service and RPC overhead will bound throughput.

Server-side monitoring
- Monitor CPU/MEM while benchmarking:
  ```bash
  top
  htop
  pidstat -u 1
  ```
- JVM monitoring (optional): `jstat -gc <pid>`, `jcmd <pid> VM.system_properties`

## Running with Docker Compose

Docker setup runs each Java service in its own container with published ports. Services communicate using container hostnames defined in docker-compose.yml.

Prerequisites
- Docker and Docker Compose installed.
- Java build/install completed on host (required before building images).

Build and run containers

1. Build Java distribution (must run on host before docker build):
   ```bash
   cd /home/abdulrahman/testing-GRPC/java
   ./gradlew :examples:clean :examples:build :examples:installDist --no-daemon
   ```

2. Build and start containers:
   ```bash
   cd /home/abdulrahman/testing-GRPC
   docker compose build
   docker compose up -d
   ```

3. Verify services are running:
   ```bash
   docker compose ps
   docker compose logs -f sorter
   docker compose logs -f passer
   docker compose logs -f swapper
   ```

4. Run Python client from container:
   ```bash
   # simple client
   docker compose run --rm client bubble_client.py
   
   # run benchmarks
   docker compose run --rm client bench_sync.py
   docker compose run --rm client bench_async.py
   ```

5. Stop and remove containers:
   ```bash
   docker compose down
   ```

Docker architecture notes
- Each Java server (Swapper, Passer, Sorter) runs in its own container.
- Services read target hosts/ports from environment variables (SWAPPER_HOST, SWAPPER_PORT, PASSER_HOST, PASSER_PORT, SORTER_HOST, SORTER_PORT).
- Ports 50051, 50052, 50053 are published to host so you can also run Python client locally against localhost:50051.
- Python client container reads SORTER_HOST env var (defaults to "localhost" for local runs, set to "sorter" in docker-compose).

Files added for Docker
- `java/Dockerfile.java` — multi-purpose Java server image (copies build/install/examples)
- `python/Dockerfile.py` — Python client image
- `docker-compose.yml` — orchestrates 3 server services + client service

Important: always run `./gradlew :examples:installDist` on host before `docker compose build` so the Dockerfile can copy the build/install/examples directory.

Notes & next steps
- This demo runs services sequentially across processes (distributed-sequential). To move toward distributed-parallel:
  - use async stubs / parallelize calls in Passer (parallel compareSwap calls)
  - shard work across multiple Swapper instances (load-balanced)
- Current architecture creates/destroys ManagedChannel per RPC which adds latency. For production, reuse channels/stubs (create once at server startup).
- To deploy across multiple hosts, update docker-compose service definitions or use Kubernetes with service discovery.

Files of interest
- Java servers:
  - `java/examples/src/main/java/io/grpc/examples/bubblesort/SorterServer.java`
  - `java/examples/src/main/java/io/grpc/examples/bubblesort/PasserServer.java`
  - `java/examples/src/main/java/io/grpc/examples/bubblesort/SwapperServer.java`
- Proto:
  - `java/examples/src/main/proto/bubble.proto`
- Python client & stubs:
  - `python/bubble_sort/bubble_client.py`
  - `python/bubble_sort/bubble_pb2.py`
  - `python/bubble_sort/bubble_pb2_grpc.py`
- Benchmarks:
  - `python/bubble_sort/bench_sync.py`
  - `python/bubble_sort/bench_async.py`
- Docker:
  - `java/Dockerfile.java`
  - `python/Dockerfile.py`
  - `docker-compose.yml`
