package ru.job4j.availability;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Класс для анализа файла лога сосотояний сервера
 */
public class Analizy {

    public static void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        Record rcd = null;
        Boolean state = false;
        String res = "";
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String cline = read.readLine();
            while (cline != null) {
                if (!cline.isBlank()) {
                    rcd = new Record(cline);
                    if (rcd.getStatus() == Status.BAD || rcd.getStatus() == Status.VERY_BAD) {
                        if (!state) {
                            state = true;
                            res = rcd.getTime();
                        }
                    } else {
                        if (state) {
                            state = false;
                            res = res + ";" + rcd.getTime() + ";";
                            result.add(res);
                            res = "";
                        }
                    }
                }
                cline = read.readLine();
            }
            if (state) {
                result.add(res + ";;");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter write = new BufferedWriter(new FileWriter(target))) {
            for (String str: result) {
                write.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}