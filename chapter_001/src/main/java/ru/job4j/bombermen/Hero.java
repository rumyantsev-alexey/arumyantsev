package ru.job4j.bombermen;

import java.util.concurrent.TimeUnit;

/**
 * Класс реализует героя в игре
 */
public class Hero extends Figure implements Runnable {

    Hero (final Board board, final int initX, final int initY) {
        super(Type.HERO, board, initX, initY);
    }

    @Override
    /**
     * Метод реализует поток для героя
     * Управление движением происходит с помощью изменения параметра Direct класса Figure
     */
    public void run() {
        System.out.println(String.format("%s begining",Thread.currentThread().getName()));
        board.getBoard()[this.getCurXY().getX()][this.getCurXY().getY()].lock();
        while (!Thread.currentThread().isInterrupted()) {
            if (this.getDirect() != Direction.STOP) {
                System.out.println(String.format("Direction: %s", this.getDirect()));
                this.move(Cell.cellDirect(this.getCurXY(), this.getDirect(), board));
                this.setDirect(Direction.STOP);
                System.out.println(String.format("Hero here %s", this));
            }
        }
        System.out.println(String.format("%s ended", Thread.currentThread().getName()));
    }

    @Override
    /**
     * Метод передвигает героя по доске (актуален только в текущей версии)
     */
    public boolean move(final Cell cell) {
        boolean result = false;
        try {
            result = board.getBoard()[cell.getX()][cell.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ( result && !cell.equals(this.getCurXY()) ) {
            System.out.println(String.format("Move from %s to %s", this.getCurXY(), cell));
            board.getBoard()[this.getCurXY().getX()][this.getCurXY().getY()].unlock();
            this.setCurXY(cell);
            result = true;
        }
        return result;
    }

    @Override
    void action(Cell cell) {

    }

}
