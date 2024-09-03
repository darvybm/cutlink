package acortadorrn;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.41.0)",
    comments = "Source: AcortadorRn.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AcortadorServiceGrpc {

  private AcortadorServiceGrpc() {}

  public static final String SERVICE_NAME = "acortadorrn.AcortadorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUrl,
      acortadorrn.AcortadorRn.ResponseUrl> getAcortarUrlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "acortarUrl",
      requestType = acortadorrn.AcortadorRn.RequestUrl.class,
      responseType = acortadorrn.AcortadorRn.ResponseUrl.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUrl,
      acortadorrn.AcortadorRn.ResponseUrl> getAcortarUrlMethod() {
    io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUrl, acortadorrn.AcortadorRn.ResponseUrl> getAcortarUrlMethod;
    if ((getAcortarUrlMethod = AcortadorServiceGrpc.getAcortarUrlMethod) == null) {
      synchronized (AcortadorServiceGrpc.class) {
        if ((getAcortarUrlMethod = AcortadorServiceGrpc.getAcortarUrlMethod) == null) {
          AcortadorServiceGrpc.getAcortarUrlMethod = getAcortarUrlMethod =
              io.grpc.MethodDescriptor.<acortadorrn.AcortadorRn.RequestUrl, acortadorrn.AcortadorRn.ResponseUrl>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "acortarUrl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  acortadorrn.AcortadorRn.RequestUrl.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  acortadorrn.AcortadorRn.ResponseUrl.getDefaultInstance()))
              .setSchemaDescriptor(new AcortadorServiceMethodDescriptorSupplier("acortarUrl"))
              .build();
        }
      }
    }
    return getAcortarUrlMethod;
  }

  private static volatile io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUsuario,
      acortadorrn.AcortadorRn.ResponseUrls> getGetUrlsUsuarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUrlsUsuario",
      requestType = acortadorrn.AcortadorRn.RequestUsuario.class,
      responseType = acortadorrn.AcortadorRn.ResponseUrls.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUsuario,
      acortadorrn.AcortadorRn.ResponseUrls> getGetUrlsUsuarioMethod() {
    io.grpc.MethodDescriptor<acortadorrn.AcortadorRn.RequestUsuario, acortadorrn.AcortadorRn.ResponseUrls> getGetUrlsUsuarioMethod;
    if ((getGetUrlsUsuarioMethod = AcortadorServiceGrpc.getGetUrlsUsuarioMethod) == null) {
      synchronized (AcortadorServiceGrpc.class) {
        if ((getGetUrlsUsuarioMethod = AcortadorServiceGrpc.getGetUrlsUsuarioMethod) == null) {
          AcortadorServiceGrpc.getGetUrlsUsuarioMethod = getGetUrlsUsuarioMethod =
              io.grpc.MethodDescriptor.<acortadorrn.AcortadorRn.RequestUsuario, acortadorrn.AcortadorRn.ResponseUrls>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUrlsUsuario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  acortadorrn.AcortadorRn.RequestUsuario.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  acortadorrn.AcortadorRn.ResponseUrls.getDefaultInstance()))
              .setSchemaDescriptor(new AcortadorServiceMethodDescriptorSupplier("getUrlsUsuario"))
              .build();
        }
      }
    }
    return getGetUrlsUsuarioMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AcortadorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceStub>() {
        @java.lang.Override
        public AcortadorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AcortadorServiceStub(channel, callOptions);
        }
      };
    return AcortadorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AcortadorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceBlockingStub>() {
        @java.lang.Override
        public AcortadorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AcortadorServiceBlockingStub(channel, callOptions);
        }
      };
    return AcortadorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AcortadorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AcortadorServiceFutureStub>() {
        @java.lang.Override
        public AcortadorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AcortadorServiceFutureStub(channel, callOptions);
        }
      };
    return AcortadorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AcortadorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void acortarUrl(acortadorrn.AcortadorRn.RequestUrl request,
        io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrl> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcortarUrlMethod(), responseObserver);
    }

    /**
     */
    public void getUrlsUsuario(acortadorrn.AcortadorRn.RequestUsuario request,
        io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrls> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUrlsUsuarioMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAcortarUrlMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                acortadorrn.AcortadorRn.RequestUrl,
                acortadorrn.AcortadorRn.ResponseUrl>(
                  this, METHODID_ACORTAR_URL)))
          .addMethod(
            getGetUrlsUsuarioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                acortadorrn.AcortadorRn.RequestUsuario,
                acortadorrn.AcortadorRn.ResponseUrls>(
                  this, METHODID_GET_URLS_USUARIO)))
          .build();
    }
  }

  /**
   */
  public static final class AcortadorServiceStub extends io.grpc.stub.AbstractAsyncStub<AcortadorServiceStub> {
    private AcortadorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcortadorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AcortadorServiceStub(channel, callOptions);
    }

    /**
     */
    public void acortarUrl(acortadorrn.AcortadorRn.RequestUrl request,
        io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrl> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcortarUrlMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUrlsUsuario(acortadorrn.AcortadorRn.RequestUsuario request,
        io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrls> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUrlsUsuarioMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AcortadorServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AcortadorServiceBlockingStub> {
    private AcortadorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcortadorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AcortadorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public acortadorrn.AcortadorRn.ResponseUrl acortarUrl(acortadorrn.AcortadorRn.RequestUrl request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcortarUrlMethod(), getCallOptions(), request);
    }

    /**
     */
    public acortadorrn.AcortadorRn.ResponseUrls getUrlsUsuario(acortadorrn.AcortadorRn.RequestUsuario request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUrlsUsuarioMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AcortadorServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AcortadorServiceFutureStub> {
    private AcortadorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AcortadorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AcortadorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<acortadorrn.AcortadorRn.ResponseUrl> acortarUrl(
        acortadorrn.AcortadorRn.RequestUrl request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcortarUrlMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<acortadorrn.AcortadorRn.ResponseUrls> getUrlsUsuario(
        acortadorrn.AcortadorRn.RequestUsuario request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUrlsUsuarioMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACORTAR_URL = 0;
  private static final int METHODID_GET_URLS_USUARIO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AcortadorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AcortadorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ACORTAR_URL:
          serviceImpl.acortarUrl((acortadorrn.AcortadorRn.RequestUrl) request,
              (io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrl>) responseObserver);
          break;
        case METHODID_GET_URLS_USUARIO:
          serviceImpl.getUrlsUsuario((acortadorrn.AcortadorRn.RequestUsuario) request,
              (io.grpc.stub.StreamObserver<acortadorrn.AcortadorRn.ResponseUrls>) responseObserver);
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

  private static abstract class AcortadorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AcortadorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return acortadorrn.AcortadorRn.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AcortadorService");
    }
  }

  private static final class AcortadorServiceFileDescriptorSupplier
      extends AcortadorServiceBaseDescriptorSupplier {
    AcortadorServiceFileDescriptorSupplier() {}
  }

  private static final class AcortadorServiceMethodDescriptorSupplier
      extends AcortadorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AcortadorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AcortadorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AcortadorServiceFileDescriptorSupplier())
              .addMethod(getAcortarUrlMethod())
              .addMethod(getGetUrlsUsuarioMethod())
              .build();
        }
      }
    }
    return result;
  }
}
