package com.kotakov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int PORT = 23566;

    public static void server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT, 2);
             Socket clientSocket = serverSocket.accept(); // ждем подключения
             PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            List<String> child = Arrays.asList("игрушка", "играть", "машинка", "кукла", "Пётр",
                    "дед мороз", "каша", "юла", "поиграй со мной ", "в", "прятки", "мяч", "пора спать", "кушать");
            List<String> adult = Arrays.asList("моя", "искусственная интелекта", "слабая", "глупая", " только учусь",
                    "мозг", "забыл..", "паника", "Ломоносов", "где я?", "поговори со мной", "я ушёл", "космос");

            System.out.println("New connection accepted");

            final String name = serverIn.readLine();
            String clientChoice = null;

            serverOut.println(String.format("Привет %s, твой порт: %d \n" +
                    "Для прервания сеанса свзи введи 'стоп'.\n" +
                    "Ты ребенок? (да/нет).\n", name, clientSocket.getPort()));

            if (serverIn.readLine().equalsIgnoreCase("нет")){
                serverOut.println(String.format("\t\tТогда поговорим о разуме!!!  %s, что ты об этом думаешь?\n", name));
                while (true){
                    clientChoice = serverIn.readLine();
                    if (clientChoice.equalsIgnoreCase("стоп")){
                        serverOut.println(String.format("\t\tВидимо мой блестящий ум тебя утомил.. Пока, %s \n", name));
                        break;
                    }
                    serverOut.println(String.format("\t\t%s, я вот что думаю: %s, %s, %s. Что на это скажешь? \n", name,
                            adult.get(new Random().nextInt(adult.size())),
                            adult.get(new Random().nextInt(adult.size())),
                            adult.get(new Random().nextInt(adult.size()))));
                }
            } else {
                serverOut.println(String.format("\t\tТогда поговорим об играх и игрушках!!!  %s, что ты об этом думаешь?\n", name));
                while (true){
                    clientChoice = serverIn.readLine();
                    if (clientChoice.equalsIgnoreCase("стоп")){
                        serverOut.println(String.format("\t\tВидимо я уже взрослый и тебе со мной не интересно.. Пока, %s \n", name));
                        break;
                    }
                    serverOut.println(String.format("\t\t%s, я вот что думаю: %s, %s, %s. Что на это скажешь? \n", name,
                            child.get(new Random().nextInt(child.size())),
                            child.get(new Random().nextInt(child.size())),
                            child.get(new Random().nextInt(child.size()))));
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        server();
    }
}
