package ru.job4j.cars;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Класс реализует интерфейс Store для hibernate-реализации
 * @param <K> дженерик класс сущностей используемых в этом проекте
 */
public class DbStore<K> implements Store<K> {

    private final SessionFactory factory = HibernateSessionFactory.getSessionFactory();
    private final String entityname;
    private final String fullclass;

    /**
     * Конструктор с определением полного и сокращенного имени класса
     * @param genclass
     */
    public DbStore(Class<K> genclass) {
        fullclass = genclass.getName();
        String[] names = fullclass.split("\\.");
        entityname = names[names.length - 1];
    }

    @Override
    public int add(K model) {
        return this.tx(
                session -> (Integer) session.save(model)
        );
    }

    @Override
    public boolean update(K model) {
        return this.tx(
                session -> {
                    boolean result = true;
                    try {
                        session.update(model);
                    } catch (HibernateException e) {
                        result = false;
                    }
                    return result;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    String qr = "DELETE FROM " + entityname + " WHERE id = :Id";
                    Query query = session.createQuery(qr);
                    query.setParameter("Id", id);
                    return query.executeUpdate() > 0;
                }
        );
    }

    @Override
    public ArrayList<K> findAll() {
        return this.tx(
                session -> {
                    String qr = "FROM " + entityname + " ORDER BY id";
                    Query query = session.createQuery(qr);
                    return (ArrayList<K>) query.list();
                }
        );
    }

    @Override
    public K findById(int id) {
        return this.tx(
                session -> {
                    Class<?> name = null;
                    try {
                        name = Class.forName(fullclass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return (K) session.get(name, id);
                }
        );
    }

    @Override
    public int findIdByModel(K model) {
        int result = 0;
        ArrayList<K> temp = this.findAll();
        for (K one: temp) {
            if (one.equals(model)) {
                try {
                    Method getNameMethod = one.getClass().getMethod("getId");
                    result = (Integer) getNameMethod.invoke(one);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
