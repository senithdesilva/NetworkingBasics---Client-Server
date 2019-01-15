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
        // The client instantiates a Socket object, specifying the server name and the port number to connect to.
        // In this case, server is local host and the server port is 7777 as specified.
        try (Socket client = new Socket(SERVER_NAME, PORT)) {

            LOGGER.info("Connected to Server: " + client.getRemoteSocketAddress());

            // The client's OutputStream is connected to the server's InputStream
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            // The client's InputStream is connected to the server's OutputStream.
            InputStream inputStreamFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inputStreamFromServer);
            String serverResponse = in.readUTF();

            LOGGER.info("Server Response: " + serverResponse);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception Occurred: ", e);
        }
    }
}
