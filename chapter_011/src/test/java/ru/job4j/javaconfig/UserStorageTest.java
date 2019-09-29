package ru.job4j.javaconfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserStorageTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

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