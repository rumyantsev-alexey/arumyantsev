package ru.job4j.rpncalc;


/**
 * Кастомер с поддержкой исключений
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface MyConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
