package ru.job4j.rpncalc;

/**
 * Исключение, вызваное отсутствием в сетке необходимого числа аргументов для операции калькулятора
 */
public class NoArgumentException extends Exception {
    public NoArgumentException(String str) {
        super(str);
    }
}
