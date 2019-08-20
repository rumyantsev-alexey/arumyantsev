package ru.job4j.generator;

/**
 * Исключение, вызванное отсутствием нужного ключа в мапе ключей
 */
public class NoKeyException extends Exception {

    public NoKeyException(String s) {
        super(s);
    }
}
