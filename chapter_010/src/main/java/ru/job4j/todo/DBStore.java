package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Класс реализует интерфейс Store для hibernate
 */
public class DBStore implements Store<ToDo> {
    SessionFactory factory = HibernateSessionFactory.getSessionFactory();

    @Override
    public boolean add(ToDo model) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
            result = true;
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(ToDo model) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE ToDo SET done = :done WHERE id = :Id");
            query.setParameter("done", model.getDone());
            query.setParameter("Id", model.getId());
            if (query.executeUpdate() > 0) {
                result = true;
            }
            transaction.commit();
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ToDo WHERE id = :Id");
            query.setParameter("Id",id);
            if (query.executeUpdate() > 0) {
                result = true;
            }
            transaction.commit();
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public ArrayList<ToDo> findAll() {
        ArrayList<ToDo> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            result = (ArrayList<ToDo>) session.createQuery("FROM ToDo ORDER BY id").list();
            transaction.commit();
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public ToDo findById(int id) {
        ToDo result = null;
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            result = (ToDo) session.get(ToDo.class, id);
            transaction.commit();
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return result;
    }

}
