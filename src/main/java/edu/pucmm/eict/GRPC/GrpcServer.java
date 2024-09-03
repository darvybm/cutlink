package edu.pucmm.eict.GRPC;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private final int port;
    private final Server server;

    public GrpcServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new AcortadorService()).build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Servidor iniciado en el puerto " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Apagando servidor...");
            GrpcServer.this.stop();
            System.out.println("Servidor apagado.");
        }));
    }

    public void stop() {
        server.shutdown();
        System.out.println("gRPC server stopped");
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }
}
