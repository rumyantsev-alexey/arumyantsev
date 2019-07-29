package ru.job4j.rpncalc;

/**
 * Исключение, вызваное использованием неизвестной операцией калькулятора
 */
public class IllegalOperationException extends Exception {
    public IllegalOperationException(String str) {
        super(str);
    }
}
