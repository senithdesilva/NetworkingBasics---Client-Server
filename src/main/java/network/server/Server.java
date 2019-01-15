package main.java.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

// Establishing a TCP connection between two computers using sockets
// Uses the Socket class to listen for clients on a port number specified.
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private static final Integer PORT = 7777;

    public static void main(String[] args) {
        // The communication is to occur on the specified port.
        // In this case its port: 7777
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server is Ready");
            LOGGER.info("Waiting fo Client on port: " + serverSocket.getLocalSocketAddress());
            // The accept() method waits until a client connects to the server on the given port.
            Socket server = serverSocket.accept();
            LOGGER.info("Connected to Client: " + server.getRemoteSocketAddress());

            // GET response from the Client
            DataInputStream inputStream = new DataInputStream(server.getInputStream());
            LOGGER.info(inputStream.readUTF());

            // POST response for the Client
            DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
            outputStream.writeUTF("Thank you for connecting to Server: " + server.getLocalSocketAddress() + "\nGoodbye!");

            // Closes the socket
            server.close();

        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Exception Occurred: ", e);
        }
    }
}
