package io.grpc.examples.bubble;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class SwapperGrpc {

  private SwapperGrpc() {}

  public static final java.lang.String SERVICE_NAME = "bubble.Swapper";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.bubble.SwapRequest,
      io.grpc.examples.bubble.SwapReply> getCompareSwapMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CompareSwap",
      requestType = io.grpc.examples.bubble.SwapRequest.class,
      responseType = io.grpc.examples.bubble.SwapReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.bubble.SwapRequest,
      io.grpc.examples.bubble.SwapReply> getCompareSwapMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.bubble.SwapRequest, io.grpc.examples.bubble.SwapReply> getCompareSwapMethod;
    if ((getCompareSwapMethod = SwapperGrpc.getCompareSwapMethod) == null) {
      synchronized (SwapperGrpc.class) {
        if ((getCompareSwapMethod = SwapperGrpc.getCompareSwapMethod) == null) {
          SwapperGrpc.getCompareSwapMethod = getCompareSwapMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.bubble.SwapRequest, io.grpc.examples.bubble.SwapReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CompareSwap"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.SwapRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.SwapReply.getDefaultInstance()))
              .setSchemaDescriptor(new SwapperMethodDescriptorSupplier("CompareSwap"))
              .build();
        }
      }
    }
    return getCompareSwapMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SwapperStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SwapperStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SwapperStub>() {
        @java.lang.Override
        public SwapperStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SwapperStub(channel, callOptions);
        }
      };
    return SwapperStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SwapperBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SwapperBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SwapperBlockingV2Stub>() {
        @java.lang.Override
        public SwapperBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SwapperBlockingV2Stub(channel, callOptions);
        }
      };
    return SwapperBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SwapperBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SwapperBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SwapperBlockingStub>() {
        @java.lang.Override
        public SwapperBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SwapperBlockingStub(channel, callOptions);
        }
      };
    return SwapperBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SwapperFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SwapperFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SwapperFutureStub>() {
        @java.lang.Override
        public SwapperFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SwapperFutureStub(channel, callOptions);
        }
      };
    return SwapperFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Compare two numbers and return them (swapped if necessary)
     * </pre>
     */
    default void compareSwap(io.grpc.examples.bubble.SwapRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SwapReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCompareSwapMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Swapper.
   */
  public static abstract class SwapperImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SwapperGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Swapper.
   */
  public static final class SwapperStub
      extends io.grpc.stub.AbstractAsyncStub<SwapperStub> {
    private SwapperStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SwapperStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SwapperStub(channel, callOptions);
    }

    /**
     * <pre>
     * Compare two numbers and return them (swapped if necessary)
     * </pre>
     */
    public void compareSwap(io.grpc.examples.bubble.SwapRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SwapReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCompareSwapMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Swapper.
   */
  public static final class SwapperBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SwapperBlockingV2Stub> {
    private SwapperBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SwapperBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SwapperBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Compare two numbers and return them (swapped if necessary)
     * </pre>
     */
    public io.grpc.examples.bubble.SwapReply compareSwap(io.grpc.examples.bubble.SwapRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCompareSwapMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Swapper.
   */
  public static final class SwapperBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SwapperBlockingStub> {
    private SwapperBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SwapperBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SwapperBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Compare two numbers and return them (swapped if necessary)
     * </pre>
     */
    public io.grpc.examples.bubble.SwapReply compareSwap(io.grpc.examples.bubble.SwapRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCompareSwapMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Swapper.
   */
  public static final class SwapperFutureStub
      extends io.grpc.stub.AbstractFutureStub<SwapperFutureStub> {
    private SwapperFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SwapperFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SwapperFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Compare two numbers and return them (swapped if necessary)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.bubble.SwapReply> compareSwap(
        io.grpc.examples.bubble.SwapRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCompareSwapMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COMPARE_SWAP = 0;

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
        case METHODID_COMPARE_SWAP:
          serviceImpl.compareSwap((io.grpc.examples.bubble.SwapRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.bubble.SwapReply>) responseObserver);
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
          getCompareSwapMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.bubble.SwapRequest,
              io.grpc.examples.bubble.SwapReply>(
                service, METHODID_COMPARE_SWAP)))
        .build();
  }

  private static abstract class SwapperBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SwapperBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.bubble.Bubble.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Swapper");
    }
  }

  private static final class SwapperFileDescriptorSupplier
      extends SwapperBaseDescriptorSupplier {
    SwapperFileDescriptorSupplier() {}
  }

  private static final class SwapperMethodDescriptorSupplier
      extends SwapperBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SwapperMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SwapperGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SwapperFileDescriptorSupplier())
              .addMethod(getCompareSwapMethod())
              .build();
        }
      }
    }
    return result;
  }
}
