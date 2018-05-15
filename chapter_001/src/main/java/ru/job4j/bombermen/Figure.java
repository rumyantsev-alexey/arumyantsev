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
    protected final Board board;
    // направление движения фигуры
    // поле волатильное и не финальное так как служит для управления фигурой из вне
    private volatile Direction direct = Direction.STOP ;

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

    public void setDirect(Direction direct) {
        this.direct = direct;
    }

    public Direction getDirect() {
        return direct;
    }

    /**
     * Метод перемещает фигуру с текущих координат в новые (в текущей версии не используется)
     * @param cell новые координаты
     * @return успешность
     */
    public boolean move(final Cell cell) {
        boolean result = false;
        if ( !board.isLock(cell) && this.type != Type.WALL) {
            board.unlock(curXY);
            curXY = cell;
            board.lock(curXY);
            result = true;
        }
        return result;
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
