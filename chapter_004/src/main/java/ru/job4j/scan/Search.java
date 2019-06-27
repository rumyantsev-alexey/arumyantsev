package ru.job4j.scan;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс ищет все файлы от заданной директории и имеющие заданные расширения
 */
public class Search {

    /**
     * Метод ищет все файлы от заданной директории и имеющие заданные расширения
     * @param parent исходная директория
     * @param exts заданные расширения
     * @return список файлов
     */
    public static List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList();
        File dir = new File(parent);
        if (dir.exists() && dir.isDirectory()) {
            result = contentDir(dir);
        }
        return result.stream().filter(x -> checkExts(x, exts)).collect(Collectors.toList());
    };

    /**
     * Метод проверяет что файл имеет одно из заданных расширений
     * @param file файл
     * @param exts список расширений
     * @return истина
     */
    private static boolean checkExts(File file, List<String> exts) {
        String ext = file.getName().replaceAll("^.*\\.(.*)$", "$1");
        return exts.stream().anyMatch(x -> x.equals(ext));
    }

    /**
     * Метод возвращает список файлов в заданной директории
     * @param dir директория
     * @return список файлов
     */
    private static List<File> contentDir(File dir) {
        List<File> result = new ArrayList<>();
        List<File> list = dir.listFiles() != null ? Arrays.asList(dir.listFiles()) : new ArrayList<>();
        for (File cfile: list) {
            if (cfile.isDirectory()) {
                result.addAll(contentDir(cfile));
            } else {
                result.add(cfile);
            }
        }
        return result;
    }
}