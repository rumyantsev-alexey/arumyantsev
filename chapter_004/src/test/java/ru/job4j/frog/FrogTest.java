package ru.job4j.frog;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Класс тестирует движение лягушки к цели
 */
public class FrogTest {

    /**
     * Метод тестирует поведение лягушки для конкретных параметров
     */
    @Test
    public void testFrog() {
        final Point begin = new Point(7, 11);
        final Point end = new Point(9, 8);
        final List<Point> forest = List.of(new Point(9, 14), new Point(5, 5), new Point(9, 9));
        System.out.printf("Начальное местоположение лягушки: %s%n", begin);
        System.out.printf("Куда надо попасть лягушке: %s%n", end);
        System.out.println("Местоположение деревьев:");
        forest.forEach(System.out::println);
        Frog frog = new Frog(begin, end, forest);
        System.out.printf("--------------%n%nПОСКАКАЛИ!!!%n%n");
        List<Point> result = frog.runFrog();
        assertEquals(6, result.size());
        if (result.size() > 0) {
            System.out.printf("%nПуть лягушки в обратном порядке (%s прыжков):%n", result.size() - 1);
            result.forEach(System.out::println);
        } else {
            System.out.printf("%nНе смогла доскакать !!!%n");
        }
    }

}