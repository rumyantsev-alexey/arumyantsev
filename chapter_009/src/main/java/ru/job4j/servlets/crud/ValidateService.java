package ru.job4j.servlets.crud;

import java.util.ArrayList;

/**
 * Класс реализующий слой логики
 */
public class ValidateService {
    private static final ValidateService vserv = new ValidateService();
    private MemoryStore ms = MemoryStore.getInstance();

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
        return name != null && ms.add(name);
    }

    /**
     * Метод реализует изменение записи в списке
     * @param id айди записи
     * @param newname новое имя
     * @return успех
     */
    public boolean update(int id, String newname) {
        return id > -1 && newname != null && ms.findById(id) != null && ms.update(id, newname);
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

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate (final int count) {
        if(count > 0) {
            ms.generate(count);
        }
    }

}
