package ru.job4j.generator;

import java.util.Map;

public interface Generator {
    String generate(String template, Map<String, String> keys) throws NoKeyException, TooMuchKeysException;
}
