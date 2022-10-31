package com.khoalt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private int port;
    private ServerSocket serverSocket;
    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }
    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                HandleAClientTask task = new HandleAClientTask(socket);
                new Thread(task).start();
            }
        } catch (Exception ex) {
            System.out.println("Cannot run server");
            ex.printStackTrace();
            closeServerSocket();
        }
    }
    public void closeServerSocket() {
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {
            var server = new Thread(new Server(1234));
            server.start();
            System.out.println("Server starts");
        } catch (Exception ex) {
            System.err.println("Failed to create chat server");
            ex.printStackTrace();
        }
    }
}
