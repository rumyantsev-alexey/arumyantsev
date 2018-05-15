package ru.job4j.bombermen;

/**
 * Класс реализует монстров в игре
 */
public class Monster extends Figure implements Runnable {

    Monster (final Board board, final int initX, final int initY) {
        super(Type.MONSTER, board, initX, initY);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

        }
    }

    @Override
    void action(final Cell cell) {

    }
}
