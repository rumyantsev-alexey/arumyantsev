package ru.job4j.annotconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserStorage implements Storage<User> {
    private final Storage<User> storage;

    @Autowired
    public UserStorage(@Qualifier("jdbcStorage") Storage<User> storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        this.storage.add(user);
    }
}
