import os
import grpc
import bubble_pb2
import bubble_pb2_grpc

def run():
    numbers = [5, 3, 8, 1, 2, 9, 4]
    print("Sending:", numbers)
    sorter_host = os.getenv("SORTER_HOST", "localhost")
    sorter_port = os.getenv("SORTER_PORT", "50051")
    target = f"{sorter_host}:{sorter_port}"
    with grpc.insecure_channel(target) as channel:
        stub = bubble_pb2_grpc.SorterStub(channel)
        req = bubble_pb2.SortRequest(numbers=numbers)
        resp = stub.BubbleSort(req)
        print("Sorted result from server:", list(resp.numbers))

if __name__ == "__main__":
    run()