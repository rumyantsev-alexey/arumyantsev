package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {
    StudentScoreComparator ssc = new StudentScoreComparator();

    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        List<Student> result = new ArrayList<>();
        for (Student st: students) {
            if (predict.test(st)) {
                result.add(st);
            }
        }
        return result;
    }

    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable).sorted(ssc.reversed()).takeWhile(s -> s.getScore() > bound).collect(Collectors.toList());

    }
}
