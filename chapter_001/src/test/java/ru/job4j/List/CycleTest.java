package ru.job4j.List;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест для проверки метода проверки цикличности списка
 */
public class CycleTest {


    @Test
    public void getAllTestForInteger() {
        Cycle cycle = new Cycle();
        SimpleNode<Integer> first = new SimpleNode(1);
        SimpleNode<Integer> two = new SimpleNode(2);
        SimpleNode<Integer> third = new SimpleNode(3);
        SimpleNode<Integer> four = new SimpleNode(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(cycle.hasCycle(first), is(true));
        assertThat(cycle.hasCycle(four), is(true));
        four.next = null;
        assertThat(cycle.hasCycle(first), is(false));
    }
}