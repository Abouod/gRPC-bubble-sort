package io.grpc.examples.helloworld;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.examples.bubble.SwapReply;
import io.grpc.examples.bubble.SwapRequest;
import io.grpc.examples.bubble.SwapperGrpc;
import io.grpc.stub.StreamObserver;

public class SwapperServer {

    private static final Logger logger = Logger.getLogger(SwapperServer.class.getName());
    private Server server;
    private static final int PORT = 50053;

    private void start() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        server = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create())
                .executor(executor)
                .addService(new SwapperImpl())
                .build()
                .start();
        logger.info("SwapperServer started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                SwapperServer.this.stop();
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
        final SwapperServer s = new SwapperServer();
        s.start();
        s.blockUntilShutdown();
    }

    static class SwapperImpl extends SwapperGrpc.SwapperImplBase {

        @Override
        public void compareSwap(SwapRequest req, StreamObserver<SwapReply> responseObserver) {
            int a = req.getA();
            int b = req.getB();
            logger.info("SwapperImpl.compareSwap received (" + a + "," + b + ")");
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            SwapReply reply = SwapReply.newBuilder().setA(a).setB(b).build();
            logger.info("SwapperImpl.compareSwap returning (" + a + "," + b + ")");
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
