package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public static String getKey(String str) {
        return str.split("=")[0];
    }

    public static String getValue(String str) {
        return str.split("=")[1];
    }
    public static boolean hasEqual(String str) {
        return str.contains("=");
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines().filter(Config::hasEqual).collect(Collectors.toMap(Config::getKey, Config::getValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.size() == 0 || values.get(key) == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Config("chapter_004/src/main/resources/app.properties"));
    }
}