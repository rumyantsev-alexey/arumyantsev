package ru.job4j.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует меню и его представление
 */
@NoArgsConstructor
public class Menu {

    @Getter
    private Node root = new Node();

    /**
     * Метод отображает номер пункта меню в меню (зависит от местоположения в меню)
     * @param node пункт меню
     * @return номер в формате x.x.x.
     */
    protected String getNumberMenuPoint(final Node node) {
        Node current = node;
        String result = "";
        while (current != null) {
            result = (current.getNumber() != 0 ? Integer.toString(current.getNumber()) + "." : "") + result;
            current = current.getParent();
        }
        return result;
    }

    /**
     * Метод преобразует дерево меню в список узлов
     * @param root корень меню
     * @return список всех пунктов меню
     */
    protected List<Node> getAllMenu(final Node root) {
        List<Node> result = new ArrayList<>();
        if (root.getParent() != null) {
            result.add(root);
        }
        for (Node nnn: root.getChilds()) {
            result.addAll(this.getAllMenu(nnn));
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<Node> menu = this.getAllMenu(this.root);
        for (Node nnn: menu) {
            String num = this.getNumberMenuPoint(nnn);
            int lvl = num.split("\\.").length;
            for (int i = 1; i < lvl; i++) {
                result.append("\t");
            }
            result.append(num + " " + nnn.getText() + "%n");
        }
        return result.toString();
    }
}
