package ru.job4j.rpncalc;

import lombok.Getter;

import java.util.*;

/**
 * Класс калькулятора типа RPN.. функции задаются в потомках
 */
public class RPNCalc {

    @Getter
    private String lastoper;

    @Getter
    private MyStack stack = new MyStack();

    @Getter
    private Map<String, MyConsumer<MyStack, NoArgumentException>> operations = new HashMap<>();

    public void solve(final String oper) throws IllegalOperationException, NoArgumentException {
        if (operations.containsKey(oper)) {
            lastoper = oper;
            operations.get(oper).accept(stack);
        } else {
            throw new IllegalOperationException("нет такой операции");
        }
    }

}
