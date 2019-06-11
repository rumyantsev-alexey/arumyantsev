package ru.job4j.servlets.crud;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует подмену класса слоя логики (для тестов)
 */
public class ValidateStub extends ValidateService {

    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 1;

    @Override
    public boolean add(String name) {
        User user = new User();
        user.setId(this.ids++);
        user.setName(name);
        this.store.put(user.getId(), user);
        return true;
    }

    @Override
    public ArrayList<User> findAll() {
        return new ArrayList<User>(this.store.values());
    }

    @Override
    public boolean addFull(String name, String login, String pass, String email, Integer roleid, Integer cityid) {
        User user = new User();
        user.setId(this.ids++);
        user.setName(name);
        user.setLogin(login);
        user.setPass(pass);
        user.setEmail(email);
        user.setRoleid(roleid);
        this.store.put(user.getId(), user);
        return true;
    }

    @Override
    public Integer roleidByRole(String role) {
        return USER_ID;
    }

    @Override
    public boolean updateByUser(User usr) {
        return store.put(usr.getId(), usr) != null;
    }

    @Override
    public boolean delete(int id) {
        return store.remove(id) != null;
    }
}
