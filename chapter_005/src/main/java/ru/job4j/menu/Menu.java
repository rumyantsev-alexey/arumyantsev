package ru.job4j.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Класс реализует меню и его представление
 */
@NoArgsConstructor
public class Menu implements IMenu {

    @Getter
    private Node root = new Node();

    /**
     * Метод создвет меню из списка связвнных пунктов меню
     * @param menu список
     */
    @Override
    public void createMenu(List<Node> menu) {
        this.root.addNodes(menu);
    }

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
     * @return список всех пунктов меню
     */
    @Override
    public List<Node> getAllMenu() {
        List<Node> rlist;
        List<Node> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(root);
        do {
            Node cur = stack.pop();
            rlist = cur.getChilds();
            if (rlist.size() > 0) {
                rlist = this.reverseOrder(rlist);
                rlist.forEach(stack::push);
            }
            result.add(cur);
        } while (stack.size() > 0);
        result.remove(0);
        return result;
    }

    private <E> List<E> reverseOrder(List<E> list) {
        List<E> result = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = list.size() - 1; i > -1; i--) {
                result.add(list.get(i));
            }
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<Node> menu = this.getAllMenu();
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
