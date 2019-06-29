package ru.job4j.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Класс прдназначен для архивирования, разархивирования и получения списка файлов
 */
public class Zip {

    /**
     * Метод выдает список файлов от задданного каталога и не содержит файлов с задданым расширением
     * @param root исходная директория
     * @param ext расширение, которого не должно быть
     * @return список файлов
     */
    public static List<File> seekBy(String root, String ext) {
        List<File> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(root))) {
           result = walk.map(x -> new File(x.toString())).filter(f -> f.isFile() && !f.getName().endsWith(ext)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод архивирует файлы из списка
     * @param sources список файлов
     * @param target архив
     */
    public static void pack(List<File> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)));) {
                for (File file: sources) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * Метод разархивирует архив в определенную директорию
     * @param source арихив
     * @param dest директория
     */
    public static void unpack(File source, File dest) {
        try (ZipInputStream unzip = new ZipInputStream(new BufferedInputStream(new FileInputStream(source)))) {
            ZipEntry entry = unzip.getNextEntry();
            while (entry != null) {
                Path cpath = Paths.get(dest + "/" + entry.getName());
                Files.createDirectories(cpath.getParent());
                Files.createFile(cpath);
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(cpath.toString()))) {
                    byte[] bytesIn = new byte[1024];
                    int read;
                    while ((read = unzip.read(bytesIn)) != -1) {
                        bos.write(bytesIn, 0, read);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                entry = unzip.getNextEntry();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args arg = new Args(args);
        List<File> list = Zip.seekBy(arg.directory(), arg.exclude());
//        list.forEach(System.out::println);
        Zip.pack(list, new File(arg.output()));
//        Zip.unpack(new File(arg.output()), new File(System.getProperty("java.io.tmpdir")));
    }
}
