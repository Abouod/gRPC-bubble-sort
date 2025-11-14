import asyncio
import time
import statistics
import grpc
import bubble_pb2
import bubble_pb2_grpc

HOST = "localhost:50051"
TOTAL_REQUESTS = 1000
CONCURRENCY = 100
PAYLOAD = [5, 3, 8, 1, 2, 9, 4]

async def do_request(stub):
    req = bubble_pb2.SortRequest(numbers=PAYLOAD)
    t0 = time.perf_counter()
    resp = await stub.BubbleSort(req, timeout=10)  # async unary
    t1 = time.perf_counter()
    return (t1 - t0)

async def run():
    async with grpc.aio.insecure_channel(HOST) as channel:
        stub = bubble_pb2_grpc.SorterStub(channel)
        sem = asyncio.Semaphore(CONCURRENCY)
        latencies = []

        async def worker():
            async with sem:
                try:
                    return await do_request(stub)
                except Exception as e:
                    return ("err", e)

        tasks = [asyncio.create_task(worker()) for _ in range(TOTAL_REQUESTS)]
        start = time.perf_counter()
        for coro in asyncio.as_completed(tasks):
            res = await coro
            if isinstance(res, tuple) and res[0] == "err":
                print("request failed:", res[1])
            else:
                latencies.append(res)
        elapsed = time.perf_counter() - start

    total = len([x for x in latencies if isinstance(x, float)])
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
    asyncio.run(run())