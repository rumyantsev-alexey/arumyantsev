package ru.job4j.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Student {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int score;
}
