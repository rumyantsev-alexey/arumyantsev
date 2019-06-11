package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenDistanceDiffPointsThen10() {
        // создаем два объекта класса Point.
        Point left = new Point(0, 0);
        Point right = new Point(0, 10);
        // Создаем объект треугольник и в качестве точек передает null.
        // так как нам не требуется их участие.
        Triangle triangle = new Triangle(null, null, null);

        double rsl = triangle.distance(left, right);

        assertThat(rsl, closeTo(10, 0.01));
    }
    @Test
    public void whenSemiPerimetrSetThreeDistance() {
        double ab = 3;
        double ac = 5;
        double bc = 8;
        // Создаем объект треугольник и в качестве точек передает null.
        // так как нам не требуется их участие.
        Triangle triangle = new Triangle(null, null, null);

        //Посчитаем периметр без контроля реальности треугольника
        double pp = triangle.period(ab, ac, bc);
        assertThat(pp, closeTo(16D, 0.1));
    }
    @Test
    public void checkExistTriangle() {
        double ab = 3;
        double ac = 5;
        double bc = 6;
        Triangle triangle = new Triangle(null, null, null);
        assertThat(triangle.exist(ab, ac, bc), is(true));

    }
}
