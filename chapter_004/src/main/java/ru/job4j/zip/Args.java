package ru.job4j.zip;

import java.util.*;
import java.util.stream.Collectors;

public class Args {
    private Map<String, String> args = new HashMap<>();

    public Args(String[] args) {
        this.args = this.parse(args);
    }

    public String directory() {
        return args.getOrDefault("-d", null);
    }

    public String output() {
        return args.getOrDefault("-o", null);
    }

    public String exclude() {
        String result = null;
        if (args.containsKey("-e")) {
            result = args.get("-e").replaceAll("^.*\\.(.*)$", "$1");
        }
        return result;
    }

    private Map<String, String> parse(String[] args) {
        Map<String, String> result = new HashMap<>();
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-d") || args[i].equals("-o") || args[i].equals("-e")) {
                result.put(args[i++], args[i++]);
            } else {
                i++;
            }
        }
        return result;
    }

}
