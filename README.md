# gRPC Bubble-Sort Service Chain (Java servers + Python client)

Summary
- Demo of service chaining (Sorter → Passer → Swapper) implemented with gRPC.
- Three independent Java services (each runs on its own port):
  - SorterServer (orchestrator) — listens on 50051 and calls Passer at 50052  
    [`io.grpc.examples.helloworld.SorterServer`](java/examples/src/main/java/io/grpc/examples/helloworld/SorterServer.java)
  - PasserServer — listens on 50052 and calls Swapper at 50053  
    [`io.grpc.examples.helloworld.PasserServer`](java/examples/src/main/java/io/grpc/examples/helloworld/PasserServer.java)
  - SwapperServer — listens on 50053 and performs compare/swap  
    [`io.grpc.examples.helloworld.SwapperServer`](java/examples/src/main/java/io/grpc/examples/helloworld/SwapperServer.java)
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
  cd /home/abdulrahman/testing-GRPC/java
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.helloworld.SwapperServer
  ```
- Start Passer (port 50052):
  ```bash
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.helloworld.PasserServer
  ```
- Start Sorter (port 50051):
  ```bash
  java -cp examples/build/classes/java/main:examples/build/resources/main:examples/build/install/examples/lib/* \
    io.grpc.examples.helloworld.SorterServer
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

Notes & next steps
- This demo runs all services on localhost but uses separate processes and ports to demonstrate service chaining across network boundaries.
- To containerize: run each Java server in its own container and update ManagedChannel targets to use container hostnames/ports.
- To use Gradle-generated start scripts instead of java -cp commands, we can add separate application entries in `examples/build.gradle`; tell me if you want start scripts added.

Files of interest
- Java servers:
  - [SorterServer](java/examples/src/main/java/io/grpc/examples/helloworld/SorterServer.java)
  - [PasserServer](java/examples/src/main/java/io/grpc/examples/helloworld/PasserServer.java)
  - [SwapperServer](java/examples/src/main/java/io/grpc/examples/helloworld/SwapperServer.java)
- Proto:
  - [bubble.proto](java/examples/src/main/proto/bubble.proto)
- Python client & stubs:
  - [python/bubble_sort/bubble_client.py](python/bubble_sort/bubble_client.py)
  - [python/bubble_sort/bubble_pb2.py](python/bubble_sort/bubble_pb2.py)
  - [python/bubble_sort/bubble_pb2_grpc.py](python/bubble_sort/bubble_pb2_grpc.py)

If you want, I can:
- Add Gradle application entries to produce start scripts for each server.
- Provide Dockerfiles and docker-compose to run the three Java servers and the Python client as containers.
- Add unit/integration tests that start the servers and assert correct chaining.