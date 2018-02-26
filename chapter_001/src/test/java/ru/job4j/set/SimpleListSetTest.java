package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleListSetTest {

    @Test
    /**
     * Тесты для контейнера с типом String
     */
    public void getAllTestForString() {
        SimpleListSet<String> simple = new SimpleListSet<>();
        simple.add("first");
        simple.add("second");
        simple.add("third");
        simple.add("four");
        assertThat(simple.size(), is(4));
        simple.add("first");
        assertThat(simple.size(), is(4));
        Iterator<String> simpleiter = simple.iterator();
        assertThat(simpleiter.next(),is("first"));
        assertThat(simpleiter.hasNext(), is(true));
    }
}