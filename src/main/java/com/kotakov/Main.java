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
             Socket clientSocket = serverSocket.accept();
             PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            List<String> child = Arrays.asList("игрушка", "играть", "машинка", "кукла", "Пётр",
                    "дед мороз", "каша", "юла", "поиграй со мной ", "в", "прятки", "мяч", "пора спать", "кушать");
            List<String> adult = Arrays.asList("моя", "искусственная интелекта", "слабая", "глупая", " только учусь",
                    "мозг", "забыл..", "паника!!! ", "Ломоносов", ", где я?", ", поговори со мной", ". Я ушёл", " - это космос");

            System.out.println("New connection accepted");

            serverOut.println("Привет, я очень умный СЕРВЕР. Как теб зовут?");

            final String name = serverIn.readLine();
            String clientChoice = null;

            serverOut.println(String.format("Привет %s, твой порт: %d, Для прерывания сеанса свзи введи 'стоп'." +
                    "Ты ребенок? (да/нет).", name, clientSocket.getPort()));

            if (serverIn.readLine().equalsIgnoreCase("нет")) {
                serverOut.println(String.format("\t\tТогда поговорим о разуме!!!  %s, что ты об этом думаешь?", name));
                while (true) {
                    clientChoice = serverIn.readLine();
                    if(clientChoice.equalsIgnoreCase("стоп")){
                        serverOut.println(String.format("\t\tВидимо мой блестящий ум тебя утомил.. Пока, %s \n", name));
                        break;
                    }

                    serverOut.println(String.format("%s - это отлична мысль!!!\t\tЯ вот что думаю: %s %s %s!!! Что ты на это скажешь, %s?",
                            clientChoice,
                            adult.get(new Random().nextInt(adult.size())),
                            adult.get(new Random().nextInt(adult.size())),
                            adult.get(new Random().nextInt(adult.size())),
                            name));
                    serverOut.flush();
                }

            } else {
                serverOut.println(String.format("\t\tТогда поговорим об играх и игрушках!!!  %s, что ты об этом думаешь?", name));
                while (true) {
                    clientChoice = serverIn.readLine();
                    if (clientChoice.equalsIgnoreCase("стоп")) {
                        serverOut.println(String.format("\t\tВидимо я уже взрослый и тебе со мной не интересно.. Пока, %s", name));
                        break;
                    }
                    serverOut.println(String.format("%s - это отлична мысль!!!\t\tЯ вот что думаю: %s, %s, %s!!! Что ты на это скажешь, %s?",
                            clientChoice,
                            child.get(new Random().nextInt(child.size())),
                            child.get(new Random().nextInt(child.size())),
                            child.get(new Random().nextInt(child.size())),
                            name));
                    serverOut.flush();
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