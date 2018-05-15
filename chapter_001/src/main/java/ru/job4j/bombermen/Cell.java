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

    /**
     * Метод реализует изменение ячейки при движении в определенном направлении и на определенной доске
     * @param cell исходное положение
     * @param direct направление
     * @param board доска
     * @return новое положение
     */
    public static Cell cellDirect(final Cell cell, final Direction direct, final Board board) {
        Cell result = cell;
        switch (direct) {
            case UP:    result = new Cell(cell.getX(), cell.getY()-1);
                        break;
            case DOWN:  result = new Cell(cell.getX(), cell.getY()+1);
                        break;
            case LEFT:  result = new Cell(cell.getX()-1, cell.getY());
                        break;
            case RIGHT: result = new Cell(cell.getX()+1, cell.getY());
                        break;
        }
        if (!(result.getX() < board.getSizeXY().getX() && result.getX() >= 0 && result.getY() < board.getSizeXY().getY() && result.getY() >= 0)) {
            result = cell;
        }
        return result;
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
