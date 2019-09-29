package ru.job4j.xmlconfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

import static org.junit.Assert.*;

public class UserStorageTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

    @Test
    public void testUserBean() {
        assertEquals("Alex", context.getBean("user", User.class).getName());
    }

    @Test
    public void testUsingMemoryStorageInUserStorage() throws FileNotFoundException {
        OutputStream buff = new ByteArrayOutputStream();
        PrintStream wr = new PrintStream(buff);
        System.setOut(wr);
        UserStorage us = context.getBean("userS", UserStorage.class);
        us.add(context.getBean("user", User.class));
        assertEquals("Save Alex in memory" + System.lineSeparator(), buff.toString());
        System.setOut(System.out);
    }

}