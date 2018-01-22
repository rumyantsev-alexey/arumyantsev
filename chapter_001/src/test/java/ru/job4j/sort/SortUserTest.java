package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    /**
     * Тестирование конвертации списка в дерево с заданным порядком сортировки.
     * Проверяем что нименьший по возрасту элемент расположен в дереве первым
     */
    @Test
    public void getConverUsersListToTree() {
        List<User> users = new ArrayList<>();
        users.add( new User("igor", 35));
        users.add( new User("alex", 15));
        users.add( new User("sveta", 25));
        users.add( new User("anton", 33));
        TreeSet<User> result = SortUser.sort(users);
        assertThat(result.first().equals(new User ("alex",15)), is(true));
    }

}