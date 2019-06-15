package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class School {

    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        List<Student> result = new ArrayList<>();
        for (Student st: students) {
            if (predict.test(st)) {
                result.add(st);
            }
        }
        return result;
    }
}
