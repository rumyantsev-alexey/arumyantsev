package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс дл хранилища хаявок и работы с ним
 * @author Alex Rumyantcev
 * @version $Id$
 */

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private ArrayList<Item> items = new ArrayList<>();
    private Random rn = new Random();

    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(final Item item) {
        item.setCreat(System.currentTimeMillis());
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(rn.nextInt(10000));
    }


    /**
     * Метод реализующий изменение заявки в хранилище
     * @param item измененная заявка
     */
    public void update(final Item item) {
        items.set(items.lastIndexOf(item),item);
    }
    /**
     * Метод реалезующий удаление заявки из хранилища
     * @param item существующая заявка
     */
    public void delete(final Item item) {
        for (Item iitem: items) {
            if (iitem.equals(item)) {
                items.remove(iitem);
                break;
            }
        }
    }

    /**
     * Метод реализующий получение всех заявок из хранилища
     * @return ArrayList все существующие заявки
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Метод реализующий поиск заявки в хранилище по имени
     * @param key имя заявки
     * @return Item[]  все найденные заявки
     */
    public ArrayList<Item> findByName(final String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item:items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод реалезующий поиск заявки в хранилище по id
     * @param id id заявки
     * @return Item найденная заявка
     */
    public Item findById(final String id) {
        Item result = null;
        for (Item item:items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
