package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MatrixintTest {

    private static final int SIZEX = 10;
    private static final int SIZEY = 5;
    Integer[][] matrix = new Integer[SIZEX][SIZEY];

    @Before
    public void generate() {
        Random rnd = new Random();
        IntStream.range(0, SIZEX).forEach(x -> { IntStream.range(0, SIZEY).forEach(y -> matrix[x][y] = rnd.nextInt(1000)); });
    }

    @Test
    public void convertMatrixDuoToListTest() {
        List<Integer> result = Matrixint.convertMatrix(matrix);
        assertEquals(50, result.size());
        assertTrue(result.get(1) != 0);
    }

}