package ru.job4j.bombermen;

/**
 * Абстрактный класс Фигура - основа для всех фигур в игре
 */
public abstract class Figure {
    // текущие координаты фигуры - не финальные так как могут меняться во время игры
    private Cell curXY;
    // тип фигуры
    private final Type type ;
    // игровая доска
    private final Board board;

    /**
     * Конструктор с параметрами
     * @param type тип фигуры
     * @param board доска
     * @param initX
     * @param initY
     */
    Figure (final Type type, final Board board, final int initX, final int initY) {
        this.type = type;
        this.board = board;
        this.curXY = new Cell(initX, initY);
    }

    public void setCurXY(final Cell curXY) {
        this.curXY = curXY;
    }

    public Cell getCurXY() {
        return curXY;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Абстрактный метод должен реализовывать некие действия фигуры
     * @param cell координаты на которые надо воздействовать
     */
    abstract void action(final Cell cell);

    @Override
    public String toString() {
        return curXY.toString();
    }
}
