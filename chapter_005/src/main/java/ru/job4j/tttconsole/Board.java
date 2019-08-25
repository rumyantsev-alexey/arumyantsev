package ru.job4j.tttconsole;

import lombok.Getter;

/**
 * Класс описывает игровое поле в 3Т
 */
public class Board {

    @Getter
    private Mark[][] board;

    @Getter
    private final int size;

    /**
     * Конструктор
     * @param size размер квадратного поля
     */
    public Board(int size) {
        this.board = new Mark[size][size];
        this.size = size;
        this.initBoard();
    }

    /**
     * Метод заполняет пустое поле
     */
    private void initBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.o;
            }
        }
    }

    /**
     * Метод выдает значок который стоит на поле в точке Point
     * @param point точка
     * @return значок
     */
    public Mark getPointVal(Point point) {
        Mark result = null;
        if (checkRestictBoard(point)) {
            result = board[point.getX() - 1][point.getY() - 1];
        }
        return result;
    }

    /**
     * Метод проверяет нахождение точки на поле
     * @param point точка
     * @return находится ли на поле
     */
    public boolean checkRestictBoard(Point point) {
        return point.getX() <= size && point.getX() > 0 && point.getY() <= size && point.getY() > 0;
    }

    /**
     * Метод устанавливает значок в точку
     * @param point точка
     * @param mark значек
     * @return успех
     */
    public boolean setPointVal(Point point, Mark mark) {
        boolean result = false;
        if (checkRestictBoard(point) && getPointVal(point) == Mark.o) {
            board[point.getX() - 1][point.getY() - 1] = mark;
            result = true;
        }
        return result;
    }
}
