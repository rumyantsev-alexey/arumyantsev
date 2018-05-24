package ru.job4j.bombermen;

import java.util.concurrent.SynchronousQueue;

/**
 * Класс реализует героя в игре
 */
public class Hero extends Figure implements Runnable {

    // буфер обмена направлением движения между потоками
    private final SynchronousQueue<Direction> dir;

    Hero (final Board board, final int initX, final int initY, final SynchronousQueue<Direction> dir) {
        super(Type.HERO, board, initX, initY);
        this.dir = dir;
    }

    @Override
    /**
     * Метод реализует поток для героя
     * Управление движением происходит с помощью изменения параметра Direct класса Figure
     */
    public void run() {
        Direction d;
        System.out.println(String.format("%s begining",Thread.currentThread().getName()));
        while (!Thread.currentThread().isInterrupted()) {
            try {
                d = dir.take();
                System.out.println(String.format("Direction: %s", d));
                if (this.getBoard().move(this.getCurXY(), cellDirect(this.getCurXY(), d))) {
                    this.setCurXY(cellDirect(this.getCurXY(), d));
                }
            } catch (InterruptedException e) {
                break;
            }
            System.out.println(String.format("Hero here %s", this));
        }
        System.out.println(String.format("%s ended", Thread.currentThread().getName()));
    }

    /**
     * Метод реализует изменение ячейки при движении в определенном направлении и на определенной доске
     * @param cell исходное положение
     * @param direct направление
     * @return новое положение
     */
    private Cell cellDirect(final Cell cell, final Direction direct) {
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
        return result;
    }

    @Override
    void action(Cell cell) {

    }

}
