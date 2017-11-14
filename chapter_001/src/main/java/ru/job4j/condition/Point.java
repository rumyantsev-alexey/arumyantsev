package ru.job4j.condition;

public class Point {
    private int x;
    private int y;
    private final int A=15;
    private final int B=9;

    private double fun (int x) {
        return A*x+B;
    }

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
        if (y==this.fun(x))
            return true;
        else
            return false;
    }
}
