package Servidor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
        } catch (IOException ex) {
            System.err.println("No se ha podido inicializar el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            server.waitConnections(); //Inicio de la comunicación con el cliente
        } catch (IOException ex) {
            System.err.println("No se ha podido inicializar la comunicación con el cliente");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }
}