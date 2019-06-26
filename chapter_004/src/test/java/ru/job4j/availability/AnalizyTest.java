package ru.job4j.availability;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static org.junit.Assert.assertTrue;

/**
 * Класс тестирует класс Analixy
 */
public class AnalizyTest {
    private static final String SOURCE = "src/main/resources/server.log";
    private static final String TARGET = "src/main/resources/unavailable.csv";

    /**
     * Проверяем наличие выходного файла и проверяем первую строчку в нем
     */
    @Test
    public void analizyTest() {
        Analizy.unavailable(SOURCE, TARGET);

        assertTrue(new File(TARGET).exists());

        try (BufferedReader read = new BufferedReader(new FileReader(TARGET))) {
            assertTrue(read.readLine().equals("10:57:01;10:59:01;"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}