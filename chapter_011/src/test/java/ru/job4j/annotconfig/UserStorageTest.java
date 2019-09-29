package ru.job4j.annotconfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserStorageTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-anno.xml");

    @Test
    public void testUserBean() {
        assertEquals("Alex", context.getBean("user", User.class).getName());
    }

    @Test
    public void testUsingMemoryStorageInUserStorage() throws FileNotFoundException {
        OutputStream buff = new ByteArrayOutputStream();
        PrintStream wr = new PrintStream(buff);
        System.setOut(wr);
        UserStorage us = context.getBean("userStorage", UserStorage.class);
        us.add(context.getBean("user", User.class));
        assertEquals("Save Alex in DB" + System.lineSeparator(), buff.toString());
        System.setOut(System.out);
    }

}