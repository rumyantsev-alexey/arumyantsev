package ru.job4j.servlets.crud;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс описывающий функционал хранилища записей
 */
public interface Store<K> {

    boolean add(K model);

    boolean update(K model);

    boolean delete(int id);

    ArrayList<K> findAll();

    K findById(int id);

    Integer checkLogin(String login, String pass);

    String roleByRoleId(int role_id);

    Integer roleidByRole(String role);

    ArrayList<String> findAllRoles();

    boolean addRole(Integer id, String name);

    boolean addPage(String name);

    boolean addLink(String role, String page);

    boolean accessToPage(Integer role_id, String link);
    
    Integer pageidByPage(String page);

}
