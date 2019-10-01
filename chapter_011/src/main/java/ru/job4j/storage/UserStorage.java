package ru.job4j.storage;

import java.util.List;

public class UserStorage implements Storage<User> {
    private final Storage<User> storage;

    public UserStorage(Storage<User> storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        this.storage.add(user);
    }

    @Override
    public List<User> getAll() {
        return storage.getAll();
    }
}
