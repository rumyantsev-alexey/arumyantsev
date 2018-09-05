package ru.job4j.servlets.crud;

import java.util.ArrayList;

/**
 * Интерфейс описывающий функционал хранилища записей
 */
public interface Store {

    boolean add(String name);

    boolean update(int id, String newname);

    boolean delete(int id);

    ArrayList<User> findAll();

    User findById(int id);
}
