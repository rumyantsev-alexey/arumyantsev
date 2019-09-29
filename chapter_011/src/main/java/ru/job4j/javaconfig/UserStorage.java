package ru.job4j.javaconfig;

public class UserStorage implements Storage<User> {
    private final Storage<User> storage;

    public UserStorage(Storage<User> storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        this.storage.add(user);
    }
}
