package ru.job4j.tttconsole;

/**
 * Класс значков для 3Т
 */
public enum Mark {
                    X("X"),
                    O("O"),
                    o(" ");

    private String print;

    Mark(String title) {
        this.print = title;
    }

    public String getPrint() {
        return print;
    }

    @Override
    public String toString() {
        return getPrint();
    }
}
