package ru.job4j.todo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Класс определяющий фабрику сессий гипбернейт
 */
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}
