package ru.job4j.tttconsole;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс поределяет точку
 */
@AllArgsConstructor
public class Point {

    @Getter
    private int x;

    @Getter
    private int y;

    @Override
    public String toString() {
        return x + "-" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}
