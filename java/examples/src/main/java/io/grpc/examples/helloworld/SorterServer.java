package io.grpc.examples.helloworld;

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
import io.grpc.examples.bubble.SortReply;
import io.grpc.examples.bubble.SortRequest;
import io.grpc.examples.bubble.SorterGrpc;
import io.grpc.stub.StreamObserver;

public class SorterServer {

    private static final Logger logger = Logger.getLogger(SorterServer.class.getName());
    private Server server;
    private static final int PORT = 50051;
    private static final String PASSER_HOST = "localhost";
    private static final int PASSER_PORT = 50052;

    private void start() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        server = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create())
                .executor(executor)
                .addService(new SorterImpl())
                .build()
                .start();
        logger.info("SorterServer started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                SorterServer.this.stop();
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
        final SorterServer s = new SorterServer();
        s.start();
        s.blockUntilShutdown();
    }

    static class SorterImpl extends SorterGrpc.SorterImplBase {

        @Override
        public void bubbleSort(SortRequest req, StreamObserver<SortReply> responseObserver) {
            List<Integer> list = new ArrayList<>(req.getNumbersList());
            logger.info("SorterImpl.bubbleSort invoked with: " + list);
            int n = list.size();

            ManagedChannel channel = ManagedChannelBuilder.forAddress(PASSER_HOST, PASSER_PORT)
                    .usePlaintext()
                    .build();
            PasserGrpc.PasserBlockingStub passStub = PasserGrpc.newBlockingStub(channel);
            try {
                for (int pass = 0; pass < n - 1; pass++) {
                    logger.info("SorterImpl calling Passer.doPass pass=" + pass + " list=" + list);
                    PassRequest passReq = PassRequest.newBuilder().addAllNumbers(list).setPassIndex(pass).build();
                    PassReply passReply = passStub.doPass(passReq);
                    list = new ArrayList<>(passReply.getNumbersList());
                    logger.info("SorterImpl received PassReply pass=" + pass + " -> " + list);
                }
            } finally {
                channel.shutdownNow();
            }

            SortReply reply = SortReply.newBuilder().addAllNumbers(list).build();
            logger.info("SorterImpl returning sorted: " + list);
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
