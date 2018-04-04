package ru.job4j.threads;

/**
 * Класс содержит два внутренних класса для демонстрации прерывания одного потока другим через определенное время
 */
public class ProgInterup {
    // флаг прерывания второго потока
    private boolean timeout;
    // таймер окончания работы первого потока
    private long timer;
    // текст для работы второго потока
    private String text;
    // количество символов подсчитанных вторым потоком в тексте
    int count;

    /**
     * Конструктор
     * @param text текст для подсчета
     * @param time время работы первого потока (в мс)
     */
    public ProgInterup (String text, long time) {
        this.text = text;
        this.timer = time;
        this.timeout = false;
        this.count = 0;
    }

    /**
     * Класс определяющий поток работающий определенное время
     */
    public class Time implements Runnable {
        @Override
        public void run() {
            long begintime = System.currentTimeMillis();
            System.out.println(" Begin time");
            while (System.currentTimeMillis() - begintime < timer) {

            }
            timeout = true;
            System.out.println(" End time");
        }
    }

    /**
     * Класс определяющий поток, подсчитывающий количество символов в тексте
     */
    public class CountChar implements  Runnable {
        @Override
        public void run() {
            System.out.println(" Begin chars count");
            for (int i = 0; i < text.length() && ! timeout; i++) {
                count++;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( timeout ? String.format("Chars count interrupt. %s chars count.", count) : String.format("End chars count. %s chars count.", count));
        }
    }

}
