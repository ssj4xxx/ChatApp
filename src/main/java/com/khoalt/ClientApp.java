package com.khoalt;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Socket socket = new Socket("localhost", 1234);
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        Client client = new Client(socket, name);
        client.sendNameToServer();
        client.sender().start();
        client.receiver().start();
    }
}
