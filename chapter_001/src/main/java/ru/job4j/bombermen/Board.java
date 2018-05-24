package ru.job4j.bombermen;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс реализует функционал игровой доски
 */
public class Board implements Runnable{
    // игровая доска
    private final ReentrantLock[][] board;
    // размер доски
    private final Cell sizeXY;
    // стек для запросов на блокировку ячеек
    private final ConcurrentLinkedQueue<Cell> lock = new ConcurrentLinkedQueue<>();
    // стек для запросов на разблокировку ячеек
    private final ConcurrentLinkedQueue<Cell > unlock = new ConcurrentLinkedQueue<>();

    /**
     * Конструктор с начальной инициализацией доски
     * @param sizeX размер по оси X
     * @param sizeY размер по оси Y
     */
    Board (final int sizeX, final int sizeY) {
        board = new ReentrantLock[sizeX][sizeY];
        sizeXY = new Cell( sizeX,sizeY);
        for(int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++){
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Метод реализует перемещение фигуры из начальной точки а конечную
     * @param source начальная точка
     * @param dest конечная точка
     * @return успех перемещения
     * @throws InterruptedException прокидываем наверх прерывание для краткости кода
     */
    public boolean move(final Cell source, final Cell dest) throws InterruptedException {
        boolean result = false;
        if (dest.getX() < getSizeXY().getX() && dest.getX() >= 0 && dest.getY() < getSizeXY().getY()
                && dest.getY() >= 0 && board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {

            this.unlock(source);
            board[dest.getX()][dest.getY()].unlock();
            this.lock(dest);
            result = true;
            System.out.println(String.format("Move from %s to %s", source, dest));
        } else {
            System.out.println("No move");
        }
        return result;
    }

    /**
     * Метод помещает в стек запрос на блокировку
     * @param cell ячейка
     */
    public void lock(final Cell cell) {
        lock.offer(cell);
    }

    /**
     * Метод помещает в стек запрос на разблокировку
     * @param cell ячейка
     */
    public void unlock(final Cell cell) {
        unlock.offer(cell);
    }

    /**
     * Метод возвращает заблокированна ячейка или нет
     * @param cell ячейка
     * @return
     */
    public boolean isLock(final Cell cell) {
        return board[cell.getX()][cell.getY()].isLocked();
    }

    public Cell getSizeXY() {
        return sizeXY;
    }

    @Override
    /**
     * Метод реализует поток для игровой доски
     * Поток проверяет стеки и реализует необходимые действия
     */
    public void run() {
        Cell cell;
        System.out.println(String.format("%s begining", Thread.currentThread().getName()));
        while (!Thread.currentThread().isInterrupted()) {
            while(!lock.isEmpty()) {
                cell = lock.poll();
                if (!isLock(cell)) {
                    board[cell.getX()][cell.getY()].lock();
                    System.out.println(cell + " locked");
                }
            }
            while(!unlock.isEmpty()) {
                cell = unlock.poll();
                board[cell.getX()][cell.getY()].unlock();
                System.out.println(cell + " unlocked");
            }
        }
        System.out.println(Thread.currentThread().getName() + " ended");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < sizeXY.getX(); i++) {
            for (int j = 0; j < sizeXY.getY(); j++) {
                s.append( isLock(new Cell(j, i)) ? "X" : "0");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
