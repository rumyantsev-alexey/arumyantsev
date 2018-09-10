package ru.job4j.servlets.crud;

import java.util.ArrayList;

/**
 * Интерфейс описывающий функционал хранилища записей
 */
public interface Store<K> {

    boolean add(K model);

    boolean update(K model);

    boolean delete(int id);

    ArrayList<K> findAll();

    K findById(int id);
}
