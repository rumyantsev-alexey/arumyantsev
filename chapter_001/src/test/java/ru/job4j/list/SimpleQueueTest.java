package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleQueueTest {

    @Test
    /**
     * Тесты для очереди с типом Integer
     */
    public void getAllTestForInteger() {
        SimpleQueue<Integer> simple = new SimpleQueue<>();
        simple.push(1);
        simple.push(2);
        simple.push(3);
        simple.push(4);
        assertThat(simple.size(), is(4));
        simple.push(5);
        assertThat(simple.poll(), is(1));
        simple.poll();
        simple.poll();
        assertThat(simple.poll(), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    /**
     * Тест ошибки poll() при окончании значений
     */
    public void getPollException() {
        SimpleStack<Integer> simple = new SimpleStack<>();
        simple.push(1);
        simple.push(2);
        simple.poll();
        simple.poll();
        simple.poll();
    }
}