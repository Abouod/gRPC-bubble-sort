package io.grpc.examples.bubblesort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.examples.bubble.PassReply;
import io.grpc.examples.bubble.PassRequest;
import io.grpc.examples.bubble.PasserGrpc;
import io.grpc.examples.bubble.SwapReply;
import io.grpc.examples.bubble.SwapRequest;
import io.grpc.examples.bubble.SwapperGrpc;
import io.grpc.stub.StreamObserver;

public class PasserServer {

    private static final Logger logger = Logger.getLogger(PasserServer.class.getName());
    private Server server;
    private static final int PORT = 50052;
    private static final String SWAPPER_HOST = "localhost";
    private static final int SWAPPER_PORT = 50053;

    private void start() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        server = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create())
                .executor(executor)
                .addService(new PasserImpl())
                .build()
                .start();
        logger.info("PasserServer started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                PasserServer.this.stop();
            } catch (InterruptedException ignored) {
            }
            executor.shutdown();
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final PasserServer s = new PasserServer();
        s.start();
        s.blockUntilShutdown();
    }

    static class PasserImpl extends PasserGrpc.PasserImplBase {

        @Override
        public void doPass(PassRequest req, StreamObserver<PassReply> responseObserver) {
            List<Integer> list = new ArrayList<>(req.getNumbersList());
            int n = list.size();
            int passIndex = req.getPassIndex();
            logger.info("PasserImpl.doPass invoked passIndex=" + passIndex + " list=" + list);

            ManagedChannel channel = ManagedChannelBuilder.forAddress(SWAPPER_HOST, SWAPPER_PORT)
                    .usePlaintext()
                    .build();
            SwapperGrpc.SwapperBlockingStub swapStub = SwapperGrpc.newBlockingStub(channel);

            try {
                for (int j = 0; j < n - 1 - passIndex; j++) {
                    int a = list.get(j);
                    int b = list.get(j + 1);
                    logger.info("PasserImpl calling Swapper.compareSwap for indices " + j + " pair=(" + a + "," + b + ")");
                    SwapRequest swapReq = SwapRequest.newBuilder().setA(a).setB(b).build();
                    SwapReply swapReply = swapStub.compareSwap(swapReq);
                    list.set(j, swapReply.getA());
                    list.set(j + 1, swapReply.getB());
                    logger.info("PasserImpl swap result indices " + j + " -> (" + swapReply.getA() + "," + swapReply.getB() + ")");
                }
            } finally {
                channel.shutdownNow();
            }

            PassReply reply = PassReply.newBuilder().addAllNumbers(list).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
