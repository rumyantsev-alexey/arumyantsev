package ru.job4j.chat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final static String LOGFILE = "chapter_004/src/main/resources/log.txt";
    private List<String> answers = new ArrayList<>();
    private boolean ansflag = true;
    private boolean stopflag = false;
    private List<String> log = new ArrayList<>();
    private Map<String, Consumer<String>> commands = Map.of("стоп", (x) -> ansflag = false,
                                                        "продолжить", (x) -> ansflag = true,
                                                        "закончить", (x) -> stopflag = true);

    public ConsoleChat(File answer) {
        try {
            answers = Files.lines(Paths.get(answer.getPath()), StandardCharsets.UTF_8).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Net fila (((");
            e.printStackTrace();
        }
    }

    public void chat() {
        String input;
        System.out.println("Welcome to ConsoleChat");
        log.add("Welcome to ConsoleChat");
        Scanner scn = new Scanner(System.in);
        while (!stopflag) {
            System.out.print(">>");
            input = scn.nextLine();
            log.add(">>" + input);
            commands.getOrDefault(input, (x) -> { }).accept(input);
            if (ansflag && !stopflag) {
                input = getCurrTime() + " " + getAnswer();
                System.out.println(input);
                log.add(input);
            }
        }
        System.out.println("BYE-BYE");
        log.add("BYE-BYE");
        saveLog();
    }

    private String getAnswer() {
        Random rnd = new Random();
        int len = answers.size();
        String result = "";
        if (len > 0) {
            result = answers.get(rnd.nextInt(len));
        }
        return result;
    }

    private void saveLog() {
        try {
            Files.write(Paths.get(LOGFILE), log, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Лог чата записан " + getCurrTime() + " в " + LOGFILE);
    }

    private String getCurrTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static void main(String[] args) {
        final String ANSFILE = "chapter_004/src/main/resources/answers.txt";
        ConsoleChat cc = new ConsoleChat(new File(ANSFILE));
        cc.chat();
    }
}
