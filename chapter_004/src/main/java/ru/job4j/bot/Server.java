package ru.job4j.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        String ask;
        List<String> resp = new ArrayList<>();
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            System.out.println("появился клиент )))");
            do {
                System.out.println("ждем вопрос...");
                ask = in.readLine();
                System.out.println("Заданный вопрос: " + ask);
                System.out.println("ответ послан...");
                resp = Constants.ANSW.getOrDefault(ask, List.of("Вопрос не понятен"));
                resp.forEach(out::println);
                out.println();
            } while (!"выход".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Великий Оракул снова работает !!! ))");
        System.out.println("ждем клиента...");
        try {
            try (final Socket socket = new ServerSocket(Constants.PORT).accept()) {
                new Server(socket).run();
            }
        } catch (IOException e) {
            System.out.println("Технические проблему у Великого Оракула");
            throw e;
        }
    }
}
