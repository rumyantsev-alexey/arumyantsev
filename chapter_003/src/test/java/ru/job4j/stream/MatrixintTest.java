package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MatrixintTest {

    @Test
    public void convertMatrixDuoToListTest() {
        Matrixint mtrx = new Matrixint(10, 5);
        Integer[][] mint = mtrx.generate();

        List<Integer> result = Stream.of(mint).flatMap(Stream::of).collect(Collectors.toList());
        assertEquals(50, result.size());
    }

}