package ru.job4j.tracker;
// интерфейс для ввода
public interface Input {
    String ask(String question);
    int ask(String question,int[] range);
}
