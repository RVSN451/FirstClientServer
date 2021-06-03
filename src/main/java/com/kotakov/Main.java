package com.kotakov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static final int PORT = 23566;

    public static void server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT, 2);
             Socket clientSocket = serverSocket.accept(); // ждем подключения
             PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("New connection accepted");

            final String name = serverIn.readLine();

            serverOut.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        server();
    }
}
