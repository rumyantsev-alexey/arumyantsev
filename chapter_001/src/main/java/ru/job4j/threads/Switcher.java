package ru.job4j.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс реализует тестовую задачу Switcher
 */
public class Switcher {
    //количество символов передаваемых потоком за один цикл
    private static final int COUNT = 10;
    private final StringBuffer string = new StringBuffer();
    private volatile int cntOne  = COUNT;
    private volatile int cntTwo  = COUNT;
    private final ReentrantLock lckOne = new ReentrantLock();
    private final ReentrantLock lckTwo = new ReentrantLock();

    /**
     * Метод возвращет текущую строку
     * @return строка
     */
    public StringBuffer getString() {
        return string;
    }

    /**
     * Метод добавляет число в строку
     * @param number число
     */
    public void add(int number) {
        string.append(number);
    }

    /**
     * Поток, добавляющий 1 к строке 10 раз
     */
    protected Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean result;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    result = lckOne.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                if (result) {
                    while (cntOne > 0 && !Thread.currentThread().isInterrupted()) {
                        add(1);
                        cntOne--;
                    }
                    lckOne.unlock();
                }
                try {
                    result = lckTwo.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                if (result) {
                    cntTwo = COUNT;
                    lckTwo.unlock();
                }
                System.out.println("Thread1 - current string - "+string);
                while (cntTwo > 0 && !Thread.currentThread().isInterrupted()) {

                }
            }
            System.out.println("Thread1 end");
        }
    });

    /**
     * Поток, добавляющий 2 к строке 10 раз
     */
    protected  Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean result;
            while (!Thread.currentThread().isInterrupted()) {
                 while(cntOne > 0 && !Thread.currentThread().isInterrupted()) {

                 }
                try {
                    result = lckTwo.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                if (result) {
                    while (cntTwo > 0 && !Thread.currentThread().isInterrupted()) {
                        add(2);
                        cntTwo--;
                    }
                    lckTwo.unlock();
                }
                try {
                    result = lckOne.tryLock(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                if (result) {
                    cntOne = COUNT;
                    lckOne.unlock();
                }
                System.out.println("Thread2 - current string - "+ string);
            }
            System.out.println("Thread2 end");
        }
    });

}
