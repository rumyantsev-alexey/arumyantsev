package ru.job4j.servlets.crud;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Метод реализует постоянный слой и является реализацией интерфейса Story
 */
public class MemoryStore implements Store {
    private static final Logger log = Logger.getLogger(MemoryStore.class.getName());
    private ConcurrentHashMap<Integer, User> db = new ConcurrentHashMap<>();
    private static final MemoryStore mstory = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return mstory;
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param name имя
     * @return успех
     */
    @Override
    public boolean add(String name) {
        Random rnd = new Random();
        User usr = new User();
        usr.setName(name);
        usr.setId(rnd.nextInt(10000));
        usr.setRes(new Timestamp(System.currentTimeMillis()));
        while (db.containsKey(usr.getId())) {
            usr.setId(rnd.nextInt(10000));
        }
        return db.put(usr.getId(), usr) == null;
    }

    /**
     * Метод реализует изменение записи в списке
     * @param id айди записи
     * @param newname новое имя
     * @return успех
     */
    @Override
    public boolean update(int id, String newname) {
        return db.replace(id, new User(id, newname)) != null;
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id
     * @return успех
     */
    @Override
    public boolean delete(int id) {
        return db.remove(id) != null;
    }

    /**
     * Метод возращает все записи в списке
     * @return список
     */
    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        for(User u: db.values()) {
            result.add(u);
        }
        return result;
    }

    @Override
    public User findById(int id) {
        return db.get(id);
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate(final int count) {
        Random rnd = new Random();
        for (int i=0; i < count; i++) {
            this.add("name" + rnd.nextInt(10000));
        }
    }

    /**
     * Метод реализует изменение записи с помощью другой записи
     * @param usr новая запись
     * @return успех
     */
    public boolean updateByUser(final User usr) {
        return db.replace(usr.getId(), findById(usr.getId()), usr);
    }

    /**
     * Метод реализует создние записи с помощью заданных 3х полей
     * @param name имя
     * @param login логин
     * @param email емейл
     * @return успех
     */
    public boolean addFull(final String name, final String login, final String email) {
        Random rnd = new Random();
        User usr = new User();
        usr.setName(name);
        usr.setLogin(login);
        usr.setEmail(email);
        usr.setId(rnd.nextInt(10000));
        usr.setRes(new Timestamp(System.currentTimeMillis()));
        while (db.containsKey(usr.getId())) {
            usr.setId(rnd.nextInt(10000));
        }
        return db.put(usr.getId(), usr) == null;
    }

}
