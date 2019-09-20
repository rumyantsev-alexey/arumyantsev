package ru.job4j.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс описывает пункт меню
 */
@NoArgsConstructor
public class Node implements INode {
    @Getter
    private String text;

    @Getter
    @Setter
    private int number;

    @Getter
    @Setter
    private  Node parent = null;

    @Getter
    @Setter
    private List<Node> childs = new ArrayList<>();

    @Setter
    private Consumer<String> todo = (x) -> { System.out.println("Выбран пункт меню " + x); };

    public Node(String name) {
        this.text = name;
    }

    /**
     * Метод добавляет к пункту меню его дочерний пункт
     * @param node дочерний узел
     */
    private void addChild(final Node node) {
        if (node != null) {
            childs.add(node);
            node.setNumber(childs.size());
            node.setParent(this);
        }
    }

    /**
     * Метод добавляет к пункту меню список дочерних пунктов
     * @param nodes список
     */
    public void addNodes(final List<Node> nodes) {
        nodes.forEach(this::addChild);
    }

    /**
     * Метод выполняет операцию связанную с данным пунктом меню
     */
    public void doOperation() {
        todo.accept(this.text);
    }

    @Override
    public String toString() {
        return "Node{" + text + '}';
    }
}
