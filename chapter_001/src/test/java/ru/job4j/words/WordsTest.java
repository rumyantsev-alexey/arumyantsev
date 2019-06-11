package ru.job4j.words;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordsTest {

    @Test
    /**
     * Тестируем различные слова
     */
    public void testCompareWords() {
        assertThat(Words.compareTwoWords("корова", "ваокор"), is(true));
        assertThat(Words.compareTwoWords("абракадабр", "адабрабрак"), is(true));
        assertThat(Words.compareTwoWords("корова", "ваокора"), is(false));
        assertThat(Words.compareTwoWords("корова", "ваовор"), is(false));
    }
}