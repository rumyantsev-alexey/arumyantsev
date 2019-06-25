package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigTest {
    Config cfg = new Config("src/main/resources/app.properties");

    @Test
    public void loadPropertiesTest() {
        cfg.load();
        assertEquals("postgres", cfg.value("hibernate.connection.username"));
    }

    @Test (expected = java.lang.UnsupportedOperationException.class)
    public void noKeyExceptionTest() {
        cfg.load();
        cfg.value("dskjldsdak");
    }
}