package ru.job4j.generator;

import org.junit.Test;

import java.util.Map;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    @Test
    public void testMoreOneKeysGenerator() throws NoKeyException, TooMuchKeysException {
        Generator gen = new SimpleGenerator();
        String simplestring = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Alex", "subject", "Writing");
        assertEquals("I am a Alex, Who are Writing?", gen.generate(simplestring, keys));
    }

    @Test
    public void testOneKeysGenerator() throws NoKeyException, TooMuchKeysException {
        Generator gen = new SimpleGenerator();
        String simplestring = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = Map.of("sos", "aaaaa");
        assertEquals("Help, aaaaa, aaaaa, aaaaa", gen.generate(simplestring, keys));
    }

    @Test(expected = NoKeyException.class)
    public void testNoKeyException() throws NoKeyException, TooMuchKeysException {
        Generator gen = new SimpleGenerator();
        String simplestring = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Alex");
        gen.generate(simplestring, keys);
    }

    @Test (expected = TooMuchKeysException.class)
    public void testTooMuchKeysException() throws NoKeyException, TooMuchKeysException {
        Generator gen = new SimpleGenerator();
        String simplestring = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of("name", "Alex", "subject", "Writing", "sos", "aaaaa");
        gen.generate(simplestring, keys);
    }
}