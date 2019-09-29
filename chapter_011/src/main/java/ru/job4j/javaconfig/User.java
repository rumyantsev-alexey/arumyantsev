package ru.job4j.javaconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {
    @Getter
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
