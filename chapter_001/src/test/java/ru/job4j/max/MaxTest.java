package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class  MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenThreeMoreFirstAndSecond() {
        Max maxim = new Max();
        int result = maxim.max3(5, 7, 9);
        assertThat(result, is(9));
    }

}
