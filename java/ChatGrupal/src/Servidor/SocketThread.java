package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SocketThread extends Thread {

    private final DataInputStream recibirDatos;
    private final DataOutputStream enviarDatos;
    private static Map<String, DataOutputStream> clientes = new HashMap<>();
    private final Socket socket;
    private String identificacion;


    public SocketThread(Socket socket) throws IOException {
        this.socket = socket;
        this.recibirDatos = new DataInputStream(socket.getInputStream());
        this.enviarDatos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                //ASIGNACIÓN DE CLAVE AL HASHMAP
                enviarDatos.writeUTF("Ingrese su nombre: ");
                identificacion = recibirDatos.readUTF().toUpperCase();

                if (!clientes.containsKey(identificacion)) {
                    clientes.put(identificacion, enviarDatos);//INTRODUCCIÓN DE CLIENTES AL HASHMAP
                    break;
                }
                enviarDatos.writeUTF("Nombre en uso, prueba otro.");
            }


            System.out.println("Actualmente hay " + clientes.size() + " clientes conectados");
            mensajeBienvenida("Bienvenido " + identificacion + " al servidor.");

            recibir();
        } catch (IOException e) {
            System.err.println("Error en la conexión con el cliente");

        }

    }

    private void recibir() {

        new Thread(() -> {
            try {
                while (true) {
                    String msg = null;
                    msg = recibirDatos.readUTF();
                    enviar("[" + identificacion + "]" + ":" + msg, enviarDatos);
                    System.out.println("[" + identificacion + "]" + msg);
                }
            } catch (IOException ex) {
                System.err.println("Cliente " + identificacion + " desconectado");
            } finally {
                cerrarConexion();
            }
        }).start();

    }

    private void mensajeBienvenida(String text) {
        try {
            enviarDatos.writeUTF("[SERVIDOR]" + text);
            enviarDatos.flush();
        } catch (IOException e) {
            System.err.println("Error al mandar el mensaje de bienvenida");
        }
    }

    private void enviar(String text, DataOutputStream remitente) {
        Iterator<DataOutputStream> iterator = clientes.values().iterator();
        while (iterator.hasNext()) {

            DataOutputStream cliente = iterator.next();

            //COMPROBACIÓN SI EL CLIENTE ES EL RECEPTOR ES EL MISMO QUE EL EMISOR
            if (cliente == remitente) {
                continue;
            }

            try {

                cliente.writeUTF(text);
                cliente.flush();


            } catch (IOException ex) {
                iterator.remove();
            }
        }
    }

    private void cerrarConexion() {
        //ELIMINAMOS DE LA LISTA EL CLIENTE SI SE CIERRA LA CONEXIÓN
        clientes.remove(identificacion);

        try {
            recibirDatos.close();
            enviarDatos.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión");
        }
        System.out.println("Actualmente hay " + clientes.size() + " clientes conectados");
    }
}
