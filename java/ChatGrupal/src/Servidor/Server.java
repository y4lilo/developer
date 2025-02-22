package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private final int PORT = 54321;


    public Server() throws IOException {
        System.out.println("Iniciando Servidor en el PORT: " + PORT);
        this.serverSocket = new ServerSocket(PORT);
    }

    public void waitConnections() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept(); //EL CLIENTE QUE SE NOS CONECTA
            System.out.println("Cliente conectado");
            new SocketThread(socket).start();

        }
    }

}
