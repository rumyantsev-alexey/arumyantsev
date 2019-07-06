package ru.job4j.bot;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final InputStream input;

    public Client(Socket socket, InputStream input) {
        this.socket = socket;
        this.input = input;
    }

    public void run() {
        String str;
        String cmd;
        Scanner scn = new Scanner(input);
        try {
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                do {
                    System.out.print("Задавайте ваш вопрос: ");
                    cmd = scn.nextLine();
                    out.println(cmd);
                    str = in.readLine();
                    while (!str.isBlank()) {
                        System.out.println("Великий Оракул говорит: " + str);
                        str = in.readLine();
                    }
                } while (!"выход".equals(cmd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать к Великому Оракулу!");
        try {
            try (Socket socket = new Socket(InetAddress.getByAddress(Constants.IP), Constants.PORT)) {
                new Client(socket, System.in).run();
            }
        } catch (IOException e) {
            System.out.println("Технические проблемы с посещением Великого Оракула");
            throw e;
        }
    }
}
