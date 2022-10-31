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
        }
    }
    public static void main(String[] args) throws IOException {
        var server = new Thread(new Server(1234));
        server.start();
        System.out.println("Server starts");
    }
}
