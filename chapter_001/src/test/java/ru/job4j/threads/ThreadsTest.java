package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Тест для показа мультипоточности
 */
public class ThreadsTest {
    Threads t = new Threads("The Whale is an 1851 novel by American writer Herman Melville. The book is sailor Ishmael's narrative of the obsessive quest of Ahab, captain of the whaling ship Pequod, for revenge on Moby Dick, the white whale that on the ship's previous voyage bit off Ahab's leg at the knee.");

    @Test
    /**
     * Тест подсчитывает количество пробелов и слов в предложении и потом проверяет полученные значения
     */
    public void testThreads() {
        new Thread(t. new countSpaces()).start();
        new Thread(t. new countWords()).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(t.counts, is(49));
        assertThat(t.countw, is(50));
    }
}