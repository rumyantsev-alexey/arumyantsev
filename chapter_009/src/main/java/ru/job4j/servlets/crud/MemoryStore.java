package ru.job4j.servlets.crud;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Класс реализует постоянный слой и является реализацией интерфейса Story
 */
public class MemoryStore implements Store<User> {
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
     * @param usr пользователь
     * @return успех
     */
    @Override
    public boolean add(User usr) {
        Random rnd = new Random();
        while (db.containsKey(usr.getId())) {
            usr.setId(rnd.nextInt(10000));
        }
        return db.put(usr.getId(), usr) == null;
    }

    /**
     * Метод реализует изменение записи в списке
     * @param usr запись
     * @return успех
     */
    @Override
    public boolean update(User usr) {
        return db.replace(usr.getId(), findById(usr.getId()), usr);
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id айди записи
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

    /**
     * Метод возвращает запись по ее айди
     * @param id айди записи
     * @return запись
     */
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
        for (int i = 0; i < count; i++) {
            this.add(new User("name" + rnd.nextInt(10000),"login" + rnd.nextInt(10000), "email" + rnd.nextInt(10000) + "@test.com" ));
        }
    }
}
