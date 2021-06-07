package com.kotakov;


import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String consoleReadString() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void main(String[] args) {


        try (Socket clientSocket = new Socket("localhost", Main.PORT);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {

            String resp = null;

            while (true) {
                resp = in.readLine();
                System.out.println(resp);

                String choice = consoleReadString();
                if (choice.equalsIgnoreCase("стоп")) {
                    out.println(choice);
                    resp = in.readLine();
                    System.out.println(resp);
                    break;
                } else {
                    out.println(choice);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
