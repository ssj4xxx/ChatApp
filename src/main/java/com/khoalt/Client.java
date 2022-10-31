package com.khoalt;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    Socket socket;
    BufferedReader clientReader;
    BufferedWriter clientWriter;
    String username;

    public Client(Socket socket, String username) throws IOException {
        this.username = username;
        this.socket = socket;
        clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public Thread sender() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner input = new Scanner(System.in);
                while (socket.isConnected()) {
                    try {

                        clientWriter.write(input.nextLine());
                        clientWriter.newLine();
                        clientWriter.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return thread;
    }

    public Thread receiver() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        System.out.println(clientReader.readLine());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return thread;
    }

    public void sendNameToServer() {
        try {
            clientWriter.write(this.username);
            clientWriter.newLine();
            clientWriter.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
