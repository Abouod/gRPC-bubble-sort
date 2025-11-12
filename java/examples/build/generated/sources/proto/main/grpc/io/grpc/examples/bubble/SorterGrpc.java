package io.grpc.examples.bubble;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class SorterGrpc {

  private SorterGrpc() {}

  public static final java.lang.String SERVICE_NAME = "bubble.Sorter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.bubble.SortRequest,
      io.grpc.examples.bubble.SortReply> getBubbleSortMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BubbleSort",
      requestType = io.grpc.examples.bubble.SortRequest.class,
      responseType = io.grpc.examples.bubble.SortReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.bubble.SortRequest,
      io.grpc.examples.bubble.SortReply> getBubbleSortMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.bubble.SortRequest, io.grpc.examples.bubble.SortReply> getBubbleSortMethod;
    if ((getBubbleSortMethod = SorterGrpc.getBubbleSortMethod) == null) {
      synchronized (SorterGrpc.class) {
        if ((getBubbleSortMethod = SorterGrpc.getBubbleSortMethod) == null) {
          SorterGrpc.getBubbleSortMethod = getBubbleSortMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.bubble.SortRequest, io.grpc.examples.bubble.SortReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BubbleSort"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.SortRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.SortReply.getDefaultInstance()))
              .setSchemaDescriptor(new SorterMethodDescriptorSupplier("BubbleSort"))
              .build();
        }
      }
    }
    return getBubbleSortMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SorterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SorterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SorterStub>() {
        @java.lang.Override
        public SorterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SorterStub(channel, callOptions);
        }
      };
    return SorterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SorterBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SorterBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SorterBlockingV2Stub>() {
        @java.lang.Override
        public SorterBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SorterBlockingV2Stub(channel, callOptions);
        }
      };
    return SorterBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SorterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SorterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SorterBlockingStub>() {
        @java.lang.Override
        public SorterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SorterBlockingStub(channel, callOptions);
        }
      };
    return SorterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SorterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SorterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SorterFutureStub>() {
        @java.lang.Override
        public SorterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SorterFutureStub(channel, callOptions);
        }
      };
    return SorterFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Orchestrator: calls Passer repeatedly to fully sort the list
     * </pre>
     */
    default void bubbleSort(io.grpc.examples.bubble.SortRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SortReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBubbleSortMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Sorter.
   */
  public static abstract class SorterImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SorterGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Sorter.
   */
  public static final class SorterStub
      extends io.grpc.stub.AbstractAsyncStub<SorterStub> {
    private SorterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SorterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SorterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Orchestrator: calls Passer repeatedly to fully sort the list
     * </pre>
     */
    public void bubbleSort(io.grpc.examples.bubble.SortRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SortReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBubbleSortMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Sorter.
   */
  public static final class SorterBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SorterBlockingV2Stub> {
    private SorterBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SorterBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SorterBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Orchestrator: calls Passer repeatedly to fully sort the list
     * </pre>
     */
    public io.grpc.examples.bubble.SortReply bubbleSort(io.grpc.examples.bubble.SortRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getBubbleSortMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Sorter.
   */
  public static final class SorterBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SorterBlockingStub> {
    private SorterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SorterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SorterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Orchestrator: calls Passer repeatedly to fully sort the list
     * </pre>
     */
    public io.grpc.examples.bubble.SortReply bubbleSort(io.grpc.examples.bubble.SortRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBubbleSortMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Sorter.
   */
  public static final class SorterFutureStub
      extends io.grpc.stub.AbstractFutureStub<SorterFutureStub> {
    private SorterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SorterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SorterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Orchestrator: calls Passer repeatedly to fully sort the list
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.bubble.SortReply> bubbleSort(
        io.grpc.examples.bubble.SortRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBubbleSortMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BUBBLE_SORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BUBBLE_SORT:
          serviceImpl.bubbleSort((io.grpc.examples.bubble.SortRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SortReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getBubbleSortMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.bubble.SortRequest,
              io.grpc.examples.bubble.SortReply>(
                service, METHODID_BUBBLE_SORT)))
        .build();
  }

  private static abstract class SorterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SorterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.bubble.Bubble.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Sorter");
    }
  }

  private static final class SorterFileDescriptorSupplier
      extends SorterBaseDescriptorSupplier {
    SorterFileDescriptorSupplier() {}
  }

  private static final class SorterMethodDescriptorSupplier
      extends SorterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SorterMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SorterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SorterFileDescriptorSupplier())
              .addMethod(getBubbleSortMethod())
              .build();
        }
      }
    }
    return result;
  }
}
