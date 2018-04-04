package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProgInterupTest {
    static final String text = "The Whale is an 1851 novel by American writer Herman Melville. The book is sailor Ishmael's narrative of the obsessive quest of Ahab, captain of the whaling ship Pequod, for revenge on Moby Dick, the white whale that on the ship's previous voyage bit off Ahab's leg at the knee.";

    @Test
    /**
     * Тест когда времени не хватает для подсчета всех символов
     */
    public void testShotTime() {
        ProgInterup threads = new ProgInterup(text, 500);
        new Thread(threads. new Time()).start();
        Thread t = new Thread(threads. new CountChar());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(threads.count < 278, is(true));
    }

    @Test
    /**
     * Тест когда времени хватает для подсчета всех символов
     */
    public void testLotTime() {
        ProgInterup threads = new ProgInterup(text, 1000);
        new Thread(threads. new Time()).start();
        Thread t = new Thread(threads. new CountChar());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(threads.count, is(278));
    }
}