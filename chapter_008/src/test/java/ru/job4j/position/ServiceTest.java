package ru.job4j.position;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует основной инструментарий класса Service
 */
public class ServiceTest {
    Service serv = new Service("position.properties");

    @Before
    public void initData() {
        serv.clearData();
        serv.sampleGenerate(10);
    }

    /**
     * Метод проверяет работоспособность методов добавления и удаления
     */
    @Test
    public void testAddDeleteTask() {
        serv.add("name0000", 5478);
        assertThat(serv.findByName("name0000"), is(5478));
        serv.delete("name0000", 5478);
        assertThat(serv.findByName("name0000"), is(0));
    }

    /**
     * Метод проверяет поднятие задачи в списке позиций
     */
    @Test
    public void testUpTask() {
        serv.add("name0000", 5478);
        serv.up("name0000", 5478);
        assertThat(serv.findByName("name0000") == 5478, is(false));
    }

    /**
     * Метод проверяет понижение задачи в списке позиций
     */
    @Test
    public void testDownTask() {
        serv.add("name0000", 5478);
        serv.down("name0000", 5478);
        assertThat(serv.findByName("name0000") == 5478, is(false));
    }
}