package ru.job4j.rpncalc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Класс описывает функциональность стека для калькулятора типа RPN
 */
public class MyStack {
    private Deque<Double> stack = new ArrayDeque<>();

    public void push(final Double op) {
        stack.push(op);
    }

    public Double pop() throws NoArgumentException {
        if (stack.peek() == null) {
            throw new NoArgumentException("стек пустой");
        }
        return stack.pop();
    }

    public List<Double> list() {
        return List.copyOf(stack);
    }

    public void clearStack() {
        stack = new ArrayDeque<>();
    }

}
