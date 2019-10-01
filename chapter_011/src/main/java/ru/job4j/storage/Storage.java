package ru.job4j.storage;

import java.util.List;

public interface Storage<E> {
    void add(E e);
    List<E> getAll();
}
