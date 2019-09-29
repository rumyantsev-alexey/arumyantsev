package ru.job4j.annotconfig;

import org.springframework.stereotype.Component;

@Component
public class MemoryStorage<E> implements Storage<E> {

    @Override
    public void add(E e) {
        System.out.printf("Save %s in memory%n", e);
    }
}
