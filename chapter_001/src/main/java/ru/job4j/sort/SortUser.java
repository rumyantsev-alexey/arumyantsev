package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    // преобразуем список пользователей в дерево
    public static TreeSet<User> sort (final List<User> users) {
        return new TreeSet<User>(users);
    }
}
