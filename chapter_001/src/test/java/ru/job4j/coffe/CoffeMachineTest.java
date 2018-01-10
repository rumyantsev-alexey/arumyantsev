package ru.job4j.coffe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoffeMachineTest {

    @Test
    public void getChangesFrom50() throws Exception {

        // стоимость кофе
        final int price = 32;
        // сумма, закинутая в автомат
        final int value = 50;
        // ожидаемый результат по сдачи
        final ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(10,5,2,1));

        CoffeMachine coffe=new CoffeMachine();

        assertThat(coffe.changes(value, price), is(result));

    }

}