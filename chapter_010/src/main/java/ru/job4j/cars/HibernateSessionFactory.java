package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Класс определяющий фабрику сессий гипбернейт
 */
public class HibernateSessionFactory {

    private static final SessionFactory INSTANCE =  new Configuration().configure().buildSessionFactory();


    public static SessionFactory getSessionFactory() {
        return INSTANCE;
    }

    public static void shutdown() {
        INSTANCE.close();
    }

}
