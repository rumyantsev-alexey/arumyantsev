package ru.job4j.storage;

import java.util.ArrayList;
import java.util.List;

public class MemoryStorage<E> implements Storage<E> {
    private List<E> store = new ArrayList<>();

    @Override
    public void add(E e) {
        store.add(e);
    }

    @Override
    public List<E> getAll() {
        return store;
    }
}
