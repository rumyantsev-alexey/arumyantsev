package ru.job4j.bombermen;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Класс реализует игру Bombermen
 */
public class Bombermen implements Runnable{
    private final int sizeX;
    private final int sizeY;
    private final int countWall;
    private final int countMonster;
    private final Board board;
    // буфер обмена направлением движения между потоками
    private final SynchronousQueue<Direction> dir = new SynchronousQueue<>();
    // буфер для потоков
    private final LinkedList<Thread> bufThread = new LinkedList<>();
    private final Hero hero;

    Bombermen(final int sizeX, final int sizeY, final int countWall, final int countMonster) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.countWall = countWall;
        this.countMonster = countMonster;
        this.board = new Board(sizeX, sizeY);
        this.hero = new Hero(this.board, 0, 0, dir);
    }

    /**
     * Метод проводит подготовительные действия игры
     * Запускает поток доски, расставляет стены, монстров  и героя
     */
    public void init() {
        Random rn = new Random();
        Cell cell;

        bufThread.add(new Thread(board, "Board"));
        bufThread.getLast().start();

        int i = countWall;
        while (i  > -1) {
            cell = new Cell(rn.nextInt(sizeX), rn.nextInt(sizeY));
            if (board.isLock(cell)) {
                continue;
            }
            board.lock(cell);
            i--;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.board);

        do {
            cell = new Cell(rn.nextInt(sizeX), rn.nextInt(sizeY));
        } while (board.isLock(cell));
        hero.setCurXY(cell);
        board.lock(cell);
        bufThread.add(new Thread(hero, "Hero"));
        System.out.println(String.format("Stand HERO in %s", hero));

/*        i = countMonster;
        while (i  > 0) {
            x = rn.nextInt(sizeX);
            y = rn.nextInt(sizeY);
            if (board.isLock(x, y)) {
                continue;
            }
            buffer.offer(new Thread(new Monster(board, x, y), String.format("Monster-%s", i)));
            i--;
        } */
    }

    /**
     * Поток для игры
     */
    public void run() {
        // запускаем все потоки кроме доски
        for(Thread thread: bufThread) {
            if (thread.getName() != "Board") {
                thread.start();
            }
        }
        // 10 раз двигаем героя
        for (int i = 0; i < 10; i++) {
            try {
                dir.put(getDirect());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // останавливаем все потоки
        for(Thread thread: bufThread) {
                thread.interrupt();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Метод выдает случайное направление движения
     * @return направление
     */
    private Direction getDirect() {
        Random rn = new Random();
        return Direction.values()[rn.nextInt(4)+1];
    }
}
