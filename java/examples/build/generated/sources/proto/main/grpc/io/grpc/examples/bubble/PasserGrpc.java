package io.grpc.examples.bubble;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class PasserGrpc {

  private PasserGrpc() {}

  public static final java.lang.String SERVICE_NAME = "bubble.Passer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.bubble.PassRequest,
      io.grpc.examples.bubble.PassReply> getDoPassMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DoPass",
      requestType = io.grpc.examples.bubble.PassRequest.class,
      responseType = io.grpc.examples.bubble.PassReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.bubble.PassRequest,
      io.grpc.examples.bubble.PassReply> getDoPassMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.bubble.PassRequest, io.grpc.examples.bubble.PassReply> getDoPassMethod;
    if ((getDoPassMethod = PasserGrpc.getDoPassMethod) == null) {
      synchronized (PasserGrpc.class) {
        if ((getDoPassMethod = PasserGrpc.getDoPassMethod) == null) {
          PasserGrpc.getDoPassMethod = getDoPassMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.bubble.PassRequest, io.grpc.examples.bubble.PassReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DoPass"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.PassRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.bubble.PassReply.getDefaultInstance()))
              .setSchemaDescriptor(new PasserMethodDescriptorSupplier("DoPass"))
              .build();
        }
      }
    }
    return getDoPassMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PasserStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PasserStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PasserStub>() {
        @java.lang.Override
        public PasserStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PasserStub(channel, callOptions);
        }
      };
    return PasserStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static PasserBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PasserBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PasserBlockingV2Stub>() {
        @java.lang.Override
        public PasserBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PasserBlockingV2Stub(channel, callOptions);
        }
      };
    return PasserBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PasserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PasserBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PasserBlockingStub>() {
        @java.lang.Override
        public PasserBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PasserBlockingStub(channel, callOptions);
        }
      };
    return PasserBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PasserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PasserFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PasserFutureStub>() {
        @java.lang.Override
        public PasserFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PasserFutureStub(channel, callOptions);
        }
      };
    return PasserFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Do one bubble pass (one iteration over the array)
     * </pre>
     */
    default void doPass(io.grpc.examples.bubble.PassRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.PassReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDoPassMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Passer.
   */
  public static abstract class PasserImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PasserGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Passer.
   */
  public static final class PasserStub
      extends io.grpc.stub.AbstractAsyncStub<PasserStub> {
    private PasserStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PasserStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PasserStub(channel, callOptions);
    }

    /**
     * <pre>
     * Do one bubble pass (one iteration over the array)
     * </pre>
     */
    public void doPass(io.grpc.examples.bubble.PassRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.bubble.PassReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDoPassMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Passer.
   */
  public static final class PasserBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<PasserBlockingV2Stub> {
    private PasserBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PasserBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PasserBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Do one bubble pass (one iteration over the array)
     * </pre>
     */
    public io.grpc.examples.bubble.PassReply doPass(io.grpc.examples.bubble.PassRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDoPassMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Passer.
   */
  public static final class PasserBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PasserBlockingStub> {
    private PasserBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PasserBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PasserBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Do one bubble pass (one iteration over the array)
     * </pre>
     */
    public io.grpc.examples.bubble.PassReply doPass(io.grpc.examples.bubble.PassRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDoPassMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Passer.
   */
  public static final class PasserFutureStub
      extends io.grpc.stub.AbstractFutureStub<PasserFutureStub> {
    private PasserFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PasserFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PasserFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Do one bubble pass (one iteration over the array)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.bubble.PassReply> doPass(
        io.grpc.examples.bubble.PassRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDoPassMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DO_PASS = 0;

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
        case METHODID_DO_PASS:
          serviceImpl.doPass((io.grpc.examples.bubble.PassRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.bubble.PassReply>) responseObserver);
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
          getDoPassMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.bubble.PassRequest,
              io.grpc.examples.bubble.PassReply>(
                service, METHODID_DO_PASS)))
        .build();
  }

  private static abstract class PasserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PasserBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.bubble.Bubble.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Passer");
    }
  }

  private static final class PasserFileDescriptorSupplier
      extends PasserBaseDescriptorSupplier {
    PasserFileDescriptorSupplier() {}
  }

  private static final class PasserMethodDescriptorSupplier
      extends PasserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PasserMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (PasserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PasserFileDescriptorSupplier())
              .addMethod(getDoPassMethod())
              .build();
        }
      }
    }
    return result;
  }
}
