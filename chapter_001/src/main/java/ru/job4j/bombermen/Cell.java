package ru.job4j.bombermen;

/**
 * Класс реализует ячейку игровой доски
 */
public class Cell {
    private final int x;
    private final int y;

    Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
