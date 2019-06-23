package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ControlTaskTest {
    private static final int SIZE = 200;
    private static final int BOUND = 1000;
    private int[] array = new int[SIZE];

    @Before
    public void initArray() {
        Random rnd = new Random();
        array = rnd.ints(SIZE, 0, BOUND).toArray();

    }

    @Test
    public void taskTest() {

        int[] arrayfortest = Arrays.stream(array).filter(x -> x % 2 == 0).toArray();

        double resultSumm = Arrays.stream(array).filter(x -> x % 2 == 0).map(x -> x * x).reduce((x, y) -> x + y).getAsInt();

        assertEquals(true, Arrays.stream(arrayfortest).allMatch(x -> x % 2 == 0));
    }

}
