package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionD {

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        int count = start;
        while (count < end) {
            result.add(func.apply((double) count));
            count++;
        }

        return result;
    }

}
