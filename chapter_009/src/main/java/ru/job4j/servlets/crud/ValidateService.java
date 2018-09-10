package ru.job4j.servlets.crud;

import java.util.ArrayList;

/**
 * Класс реализующий слой логики
 */
public class ValidateService {
    private static final ValidateService vserv = new ValidateService();
//    private MemoryStore ms = MemoryStore.getInstance();
    private DbStore ms = DbStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return vserv;
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param name имя
     * @return успех
     */
    public boolean add(String name) {
        return name != null && ms.add(new User(name,null, null));
    }

    /**
     * Метод реализует изменение записи в списке
     * @param id айди записи
     * @param newname новое имя
     * @return успех
     */
    public boolean update(int id, String newname) {
        return id > -1 && newname != null && ms.findById(id) != null && ms.update(new User(id, newname));
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id
     * @return успех
     */
    public boolean delete(int id) {
        return id > -1 && ms.findById(id) != null && ms.delete(id);
    }

    /**
     * Метод возращает все записи в списке
     * @return список
     */
    public ArrayList<User> findAll() {
        return ms.findAll();
    }

    public User findById(final int id) {
        return id > -1 ? ms.findById(id) : null;
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate (final int count) {
        if(count > 0) {
            ms.generate(count);
        }
    }

    /**
     * Метод реализует изменение записи с помощью другой записи
     * @param usr новая запись
     * @return успех
     */
    public boolean updateByUser(final User usr) {
        return usr != null && ms.update(usr);
    }

    /**
     * Метод реализует создние записи с помощью заданных 3х полей
     * @param name имя
     * @param login логин
     * @param email емейл
     * @return
     */
    public boolean addFull(final String name, final String login, final String email) {
        return name != null && ms.add(new User(name, login, email));
    }
}
