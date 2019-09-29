package ru.job4j.annotconfig;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Getter
    private final String name;

    @Autowired
    public User(@Value("Alex") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
