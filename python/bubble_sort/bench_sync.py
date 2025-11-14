import time
import statistics
from concurrent.futures import ThreadPoolExecutor, as_completed
import grpc
import bubble_pb2
import bubble_pb2_grpc

HOST = "localhost:50051"
TOTAL_REQUESTS = 1000      # total requests to send
CONCURRENCY = 50          # number of concurrent workers
PAYLOAD = [5, 3, 8, 1, 2, 9, 4]  # change size to test larger payloads

def do_request(stub):
    req = bubble_pb2.SortRequest(numbers=PAYLOAD)
    t0 = time.perf_counter()
    resp = stub.BubbleSort(req)
    t1 = time.perf_counter()
    return (t1 - t0)  # seconds

def main():
    with grpc.insecure_channel(HOST) as channel:
        stub = bubble_pb2_grpc.SorterStub(channel)
        latencies = []
        start = time.perf_counter()
        with ThreadPoolExecutor(max_workers=CONCURRENCY) as ex:
            futures = [ex.submit(do_request, stub) for _ in range(TOTAL_REQUESTS)]
            for f in as_completed(futures):
                try:
                    latencies.append(f.result())
                except Exception as e:
                    print("request failed:", e)
        elapsed = time.perf_counter() - start

    total = len(latencies)
    if total == 0:
        print("No successful requests")
        return

    lat_ms = [x * 1000.0 for x in latencies]
    print(f"requests: {total}")
    print(f"elapsed: {elapsed:.3f} s")
    print(f"throughput: {total/elapsed:.2f} req/s")
    print("latency ms: min %.3f  median %.3f  mean %.3f  p90 %.3f  p99 %.3f  max %.3f" % (
        min(lat_ms),
        statistics.median(lat_ms),
        statistics.mean(lat_ms),
        sorted(lat_ms)[int(0.90*total)-1],
        sorted(lat_ms)[int(0.99*total)-1],
        max(lat_ms),
    ))

if __name__ == "__main__":
    main()