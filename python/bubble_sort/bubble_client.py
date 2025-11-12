import grpc
import bubble_pb2
import bubble_pb2_grpc

def run():
    numbers = [5, 3, 8, 1, 2, 9, 4]
    print("Sending:", numbers)
    with grpc.insecure_channel("localhost:50051") as channel:
        stub = bubble_pb2_grpc.SorterStub(channel)
        req = bubble_pb2.SortRequest(numbers=numbers)
        resp = stub.BubbleSort(req)
        print("Sorted result from server:", list(resp.numbers))

if __name__ == "__main__":
    run()