package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrixint {

    public static List<Integer> convertMatrix(Integer[][] mint) {
        return Stream.of(mint).flatMap(Stream::of).collect(Collectors.toList());
    }
}
