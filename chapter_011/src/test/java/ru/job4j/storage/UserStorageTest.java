package ru.job4j.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class UserStorageTest {
    ApplicationContext context;
    UserStorage us;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(JavaTestConfig.class);
        us = context.getBean("userS", UserStorage.class);
    }

    @Test
    public void testAddUserInUserStorage() {
        us.add(new User("Dima"));
        assertEquals("Dima", us.getAll().get(0).getName());
    }

    @Test
    public void testAddMoreUsersInUserStorage() {
        us.add(new User("Petya"));
        us.add(new User("Alex"));
        assertEquals(2, us.getAll().size());
    }

    @After
    public void destroy() {
        ((ConfigurableApplicationContext) context).close();
    }

}