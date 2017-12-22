package ru.job4j.chess;

/**
 * Класс, определяющий игровую клетку
 * @author Alex Rumyantcev
 * @version $Id$
 */
public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Получить координату Х клетки
     * @return
     */
    public int getX(){
        return this.x;
    }

    /**
     * Получить координату Y клетки
     * @return
     */
    public int getY(){
        return this.y;
    }
}
