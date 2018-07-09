package ru.job4j.tracker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * Класс дл хранилища заявок и работы с ним
 * @author Alex Rumyantcev
 * @version $Id$
 */

public class Tracker implements AutoCloseable {
    private static String driver;
    private static String url;
    private static String user;
    private static String pass;
    private static String db;
    private static String pathsql;
    private static Properties prt = new Properties();
    private Connection con = null;

    @Override
    public void close() throws Exception {

    }

    /**
     * Метод считывает параметры, проверяет корректность драйверов и БД
     * @param propert путь к файлу с параметрами
     * @return трекер с параметрами
     */
    public static Tracker initTracker(String propert) {
        boolean result = false;
        Tracker tracker = new Tracker();
        try {
            prt.load(new FileInputStream(propert));
            tracker.driver = prt.getProperty("driver");
            tracker.url = prt.getProperty("url");
            tracker.user = prt.getProperty("user");
            tracker.pass = prt.getProperty("pass");
            tracker.db = prt.getProperty("db");
            tracker.pathsql = prt.getProperty("pathsql");
            Class.forName(driver);
            tracker.con = DriverManager.getConnection(url, user, pass);
            tracker.con.close();
            result = true;
        } catch (SQLException e) {
            System.out.println("DB connection error");
        } catch (ClassNotFoundException e) {
            System.out.println("DB drivers error");
        } catch( FileNotFoundException e) {
            System.out.println("Property file not found");
        } catch (IOException e) {
            System.out.println("Property file error");
        }
        return result? tracker: null;
    }

    /**
     * Метод проверяет существование БД, создает ее (если она не существует) и также создает если необходимо таблицы
     * @return истина если удалось создать таблицы
     */
    public boolean checkAll() {
        if (!checkDB()) {
            initDB();
        }
        return initTables();
    }

    /**
     * Метод проверяет наличие БД
     * @return истина если БД существует
     */
    private boolean checkDB() {
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url, user, pass);
        Statement st = con.createStatement();) {
            ResultSet resultSet = st.executeQuery("select count(*) from pg_catalog.pg_database where datname = '"+ db + "';");
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Check DB error");
        }
        return result;
    }

    /**
     * Метод создает БД
     * @return истина если БД успешно создалась
     */
    private boolean  initDB() {
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url, user, pass);
        Statement st = con.createStatement();){
            st.execute("CREATE DATABASE "+ db + ";");
            result = true;
        } catch (SQLException e) {
            System.out.println("Create DB error");
        }
        return result;
    }

    /**
     * Метод удаляет таблицу item
     * нужен для тестов
     */
    public void  delTable() {
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             Statement st = con.createStatement();){
            st.execute("drop table item cascade;");
            result = true;
        } catch (SQLException e) {
            System.out.println("Delete table error");
        }
    }

    /**
     * Метод создает таблицы в БД, если они не были созданы
     * @return истина - успех выполнения
     */
    private boolean initTables() {
        boolean result = false;
        List <String> op = null;
        try {
            op = Files.readAllLines(Paths.get(pathsql));
            try (Connection con = DriverManager.getConnection(url+db, user, pass);) {
                for (String str : op) {
                    if (str.length() > 0) {
                        try (Statement st = con.createStatement()) {
                            st.execute(str);
                        }
                    }
                }
                result = true;
            } catch (SQLException e) {
                System.out.println("Create table SQL create script error");
            }
        } catch (IOException e) {
            System.out.println("Read SQL create script error");
        }
        return result;
    }

    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public boolean add(final Item item) {
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             PreparedStatement st = con.prepareStatement("insert into item values (?,?,?,?);");){
            st.setString(1, item.getId());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.setTimestamp(4, new Timestamp(item.getCreat()));
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Add record in DB error");
        }
        return result;
    }

    /**
     * Метод реализующий изменение заявки в хранилище
     * @param item измененная заявка
     */
    public boolean update(final Item item) {
        boolean result = false;
        if (delete(item)) {
            if (add(item)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод реалезующий удаление заявки из хранилища
     * @param item существующая заявка
     */
    public boolean delete(final Item item) {
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             PreparedStatement st = con.prepareStatement("delete from item where id = ?;")){
            st.setString(1, item.getId());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Delete record in DB error");
        }
        return result;
    }

    /**
     * Метод реализующий получение всех заявок из хранилища
     * @return ArrayList все существующие заявки
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             Statement st = con.createStatement();
             ResultSet res = st.executeQuery("select * from item;");){
            while (res.next()) {
                Item item = new Item();
                item.setId(res.getString(1));
                item.setName(res.getString(2));
                item.setDesc(res.getString(3));
                item.setCreat(res.getTimestamp(4).getTime());
                result.add(item);
            }
        } catch (SQLException e) {
            System.out.println("findAll DB error");
        }
        return result;
    }

    /**
     * Метод реализующий поиск заявки в хранилище по имени
     * @param key имя заявки
     * @return Item[]  все найденные заявки
     */
    public ArrayList<Item> findByName(final String key) {
        ArrayList<Item> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             PreparedStatement st = con.prepareStatement("select * from item where name = ? ;")){
            st.setString(1,key);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Item item = new Item();
                item.setId(res.getString(1));
                item.setName(res.getString(2));
                item.setDesc(res.getString(3));
                item.setCreat(res.getTimestamp(4).getTime());
                result.add(item);
            }
        } catch (SQLException e) {
            System.out.println("findByName DB error");
        }
        return result;
    }

    /**
     * Метод реалезующий поиск заявки в хранилище по id
     * @param id id заявки
     * @return Item найденная заявка
     */
    public Item findById(final String id) {
        Item item = new Item();
        try (Connection con = DriverManager.getConnection(url+db, user, pass);
             PreparedStatement st = con.prepareStatement("select * from item where id = ? ;");){
            st.setString(1,id);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                item.setId(res.getString(1));
                item.setName(res.getString(2));
                item.setDesc(res.getString(3));
                item.setCreat(res.getTimestamp(4).getTime());
            }
        } catch (SQLException e) {
            System.out.println("findById DB error");
        }
        return item;
    }
}
