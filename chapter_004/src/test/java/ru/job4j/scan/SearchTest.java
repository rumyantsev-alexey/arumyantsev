package ru.job4j.scan;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTest {
    private static final String PARENT = System.getProperty("java.io.tmpdir");
    private static final List<String> EXTS = List.of("tmp", "log", "qqq");

    @Test
    public void existFileTest() {
        File myfile = new File(PARENT + "/joke/smile/rrrr.qqq");
        myfile.getParentFile().mkdirs();
        try {
            myfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<File> result = Search.files(PARENT, EXTS);
//        result.forEach(System.out::println);
        assertTrue(result.contains(new File("/tmp/joke/smile/rrrr.qqq")));
    }

}