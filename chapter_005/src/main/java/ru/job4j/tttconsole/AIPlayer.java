package ru.job4j.tttconsole;

import java.util.Random;

/**
 * Класс игрока ИИ в 3Т
 */
public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    /**
     * Метод возвращает следующий ход ишрока ИИ на доске
     * @param board доска
     * @return ход Point
     */
    @Override
    public Point getStep(Board board) {
        Random rnd = new Random();
        Point result = null;
        do {
            result = new Point(rnd.nextInt(board.getSize()) + 1, rnd.nextInt(board.getSize()) + 1);
        } while (!board.checkRestictBoard(result) || !Mark.o.equals(board.getPointVal(result)));
        return result;
    }
}
