package ru.job4j.condition;

public class Point {
    private int x;
    private int y;
    private final int a = 15;
    private final int b = 9;


    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    public boolean is(int x, int y) {
        return y == a * x + b;
    }
}
