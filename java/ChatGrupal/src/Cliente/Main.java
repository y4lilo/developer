package Cliente;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = null;
        String HOST = "localhost";
        int PORT = 54321;
        try {
            cliente = new Cliente(HOST, PORT);
        } catch (IOException ex) {
            System.err.println("No se ha podido abrir el cliente contra el HOST:" + HOST + " y el PORT:" + PORT);
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            cliente.connect();
        } catch (IOException ex) {
            System.err.println("Error en la comunicación con el servidor");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }
}
