package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

    private final String HOST;
    private final int PORT;
    private final Socket socket;
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    public Cliente(String HOST, int PORT) throws IOException {
        this.HOST = HOST;
        this.PORT = PORT;
        System.out.println("Iniciando Socket contra el HOST:" + HOST + " y PORT:" + PORT);
        socket = new Socket(HOST, PORT);
    }

    public void connect() throws IOException {

        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());


        enviarDatos.flush();
        recibir();
        enviar();


    }

    //MÉTODO PARA RECIBIR DATOS DEL SERVIDOR
    private void recibir() {

        new Thread(() -> {
            while (true) {
                String msg;
                try {
                    msg = recibirDatos.readUTF();
                    System.out.println(msg);
                } catch (IOException ex) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Cliente desconectado");
                    }
                }
            }
        }).start();

    }

    //MÉTODO PARA ENVIAR DATOS AL SERVIDOR
    private void enviar() {

        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();
                try {
                    enviarDatos.writeUTF(msg);
                    enviarDatos.flush();
                } catch (IOException ex) {
                    System.err.println("Error al enviar datos al servidor");
                }
            }
        }).start();
    }
}

