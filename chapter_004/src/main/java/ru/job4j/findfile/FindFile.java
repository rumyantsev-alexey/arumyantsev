package ru.job4j.findfile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindFile {

    private static List<String> params = new ArrayList<>();

    private static boolean checkFilenamePattern(File file) {
        String pre;
        boolean result = false;
        switch (params.get(3)) {
           case "-m" :
                pre = "glob:";
                break;
            case "-r" :
                pre = "regex:";
                break;
            default:
                pre = "file";
        }

        if (!"file".equals(pre)) {
            FileSystem fileSystem = FileSystems.getDefault();
            PathMatcher pathMatcher = fileSystem.getPathMatcher(pre + params.get(2));
            Path path = Paths.get(file.getName());
            result = pathMatcher.matches(path);
        } else {
            result = file.getName().equals(params.get(2));
        }
        return result;
    }

    public static List<File>  findFileByMask() throws IOException {
        return Files.walk(Paths.get(params.get(0)))
                .filter(Files::isRegularFile)
                .map(x -> new File(x.toString()))
                .filter(FindFile::checkFilenamePattern)
                .collect(Collectors.toList());
    }


    public static void saveResultSearch(List<File> files) {
        try {
            Files.write(Paths.get(params.get(1)), files.stream().map(File::getPath).collect(Collectors.toList()), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Ошибка при записи результата !!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Поиск файла запущен...");
        System.out.println();
        params = Args.parseCmdLineForFindFile(args);
        if (params.size() == 4) {
            System.out.println("Заданы следующие параметры:");
            System.out.printf("Исходная директория для поиска: %s", params.get(0));
            System.out.println();
            System.out.printf("Поиск файла: %s с ключом %s", params.get(2), params.get(3));
            System.out.println();
            System.out.printf("Запись результата в файл: %s", params.get(1));
            System.out.println();
            try {
                List<File> result = FindFile.findFileByMask();
                System.out.println();
                System.out.println("Результат поиска:");
                result.forEach(System.out::println);
                System.out.println();
                FindFile.saveResultSearch(result);
                System.out.println("Результат записан");
                System.out.println();
            } catch (IOException e) {
                System.out.println("Ошибка при поиске файла !!!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка в параметрах !!!");
        }
        System.out.println("Поиск файла завешен");
    }
}
