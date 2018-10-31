package ru.job4j.servlets.crud;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Класс реализует структуру и функционал заиси в списке
 */
public class User {
    private Integer  id;
    private String name;
    private String login;
    private String pass;
    private String email;
    private Integer role_id;
    private Timestamp res;
    private Integer city_id;

    public User() {
        Random rnd = new Random();
        this.id = rnd.nextInt(10000);
    }

    public User(final Integer id, final String name) {
        this.id = id;
        this.name = name;
        this.res = new Timestamp(System.currentTimeMillis());
    }

    public User(String name, String login, String pass, String email, Integer role_id, Integer city_id) {
        Random rnd = new Random();
        this.id = rnd.nextInt(10000);
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.role_id = role_id;
        this.res = new Timestamp(System.currentTimeMillis());
        this.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRes() {
        return res;
    }

    public void setRes(Timestamp res) {
        this.res = res;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
