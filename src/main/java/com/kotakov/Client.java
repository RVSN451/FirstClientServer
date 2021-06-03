package com.kotakov;


import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {


        try (Socket clientSocket = new Socket("localhost", Main.PORT);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {
            out.println("CLIENT");

            String resp = in.readLine();
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

