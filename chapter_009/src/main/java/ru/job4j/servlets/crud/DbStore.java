package ru.job4j.servlets.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс реализует постоянный слой и является реализацией интерфейса Story
 */
public class DbStore implements Store<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private static final Logger log = Logger.getLogger(DbStore.class.getName());

    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/servlet");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("tester");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement()) {
            st.execute("create table if not exists users (id INTEGER PRIMARY KEY ," +
                    "name VARCHAR , login VARCHAR , email VARCHAR , createDate TIMESTAMP );");
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param model пользователь
     * @return успех
     */
    @Override
    public boolean add(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into users (id, name, login, email, createDate)" +
                     "values (?,?,?,?,?)")) {
            st.setInt(1, model.getId());
            st.setString(2, model.getName());
            st.setString(3, model.getLogin());
            st.setString(4, model.getEmail());
            st.setTimestamp(5, model.getRes());
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует изменение записи в списке
     * @param model запись
     * @return успех
     */
    @Override
    public boolean update(User model) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("update users set name = ?, login = ?, email = ? where id = ?")) {
            st.setInt(4, model.getId());
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getEmail());
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id айди записи
     * @return успех
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("delete from users where id = ?")) {
            st.setInt(1, id);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод возращает все записи в списке
     * @return список
     */
    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement();
             ResultSet rst = st.executeQuery("select * from users")) {
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getInt(1));
                user.setName(rst.getString(2));
                user.setLogin(rst.getString(3));
                user.setEmail(rst.getString(4));
                user.setRes(rst.getTimestamp(5));
                result.add(user);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод возвращает запись по ее айди
     * @param id айди записи
     * @return запись
     */
    @Override
    public User findById(int id) {
        User user = new User();
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select * from users where id = ?")){
            st.setInt(1, id);
             try (ResultSet rst = st.executeQuery()) {
                 if (rst.next()) {
                     user.setId(rst.getInt(1));
                     user.setName(rst.getString(2));
                     user.setLogin(rst.getString(3));
                     user.setEmail(rst.getString(4));
                     user.setRes(rst.getTimestamp(5));
                 }
             }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return user;
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate(final int count) {
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            this.add(new User("name" + rnd.nextInt(10000),"login" + rnd.nextInt(10000), "email" + rnd.nextInt(10000) + "@test.com" ));
        }
    }
}

