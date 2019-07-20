package ru.job4j.findfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Args {

    public static List<String> parseCmdLineForFindFile(String[] args) {

        List<String> result = new ArrayList<>();
        List<String> cmdline = Arrays.asList(args);

        int idx;

        idx = cmdline.indexOf("-d") + 1;
        if (idx > 0 && cmdline.get(idx).charAt(0) != '-') {
            result.add(cmdline.get(idx));
        }

        idx = cmdline.indexOf("-o") + 1;
        if (idx > 0 && cmdline.get(idx).charAt(0) != '-') {
            result.add(cmdline.get(idx));
        }

        idx = cmdline.indexOf("-n") + 1;
        if (idx > 0 && cmdline.get(idx).charAt(0) != '-') {
            result.add(cmdline.get(idx));
        }

        result.add(idx > 0 ? cmdline.get(idx + 1) : null);

        return result;
    }
}
