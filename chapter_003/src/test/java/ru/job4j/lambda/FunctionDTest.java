package ru.job4j.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FunctionDTest {
    private FunctionD function = new FunctionD();


    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = function.diapason(5, 8, x -> x * x + 5);
        List<Double> expected = Arrays.asList(30D, 41D, 54D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        List<Double> result = function.diapason(5, 8, x -> Math.log(x) * 100);
        List<Double> expected = Arrays.asList(160.943D, 179.175D, 194.591D);
        int i = 0;
        for (Double exp: expected) {
            Double res = result.get(i++);
            assertEquals(res, exp, 0.001D);
        }
     }

}