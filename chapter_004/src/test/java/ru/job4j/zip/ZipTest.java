package ru.job4j.zip;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Тестируем класс архивации
 */
public class ZipTest {
    private static final String WORK_DIR = System.getProperty("java.io.tmpdir") + "/temp";

    /**
     * Создаем файл, архивируем его и распаковываем его в другое место
     * @throws IOException
     */
    @Test
    public void execZipUnzipTest() throws IOException {
        Path fpath = Paths.get(WORK_DIR + "/temp.tmp");
        Files.createDirectories(fpath.getParent());
        Files.createFile(fpath);
        List<File> list = List.of(new File(fpath.toString()));
        Zip.pack(list, new File(WORK_DIR + "/test.zip"));
        assertTrue(Files.exists(Paths.get(WORK_DIR + "/test.zip")));
        Zip.unpack(new File(WORK_DIR + "/test.zip"), new File(WORK_DIR + "/test"));
        assertTrue(Files.exists(Paths.get(WORK_DIR + "/test/" + WORK_DIR + "/temp.tmp")));
    }

    /**
     * Чистим за собой
     * @throws IOException
     */
    @After
    public void deleteDump() throws IOException {
        Files.walk(Paths.get(WORK_DIR)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }
}
