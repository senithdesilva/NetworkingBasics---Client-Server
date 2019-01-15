package main.java.network.client;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    private static final String SERVER_NAME = "localhost";
    private static final Integer PORT = 7777;

    public static void main(String[] args) {

        try (Socket client = new Socket(SERVER_NAME, PORT)) {

            LOGGER.info("Connected to Server: " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String serverResponse = in.readUTF();

            LOGGER.config("Server Response: " + serverResponse);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception Occurred: ", e);
        }
    }
}
