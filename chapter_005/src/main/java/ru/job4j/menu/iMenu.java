package ru.job4j.menu;

import java.util.List;

public interface iMenu {
    void createMenu(List<Node> menu);
    List<Node> getAllMenu();
}
