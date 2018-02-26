package ru.job4j.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс представляет собой динамический список типа E с базовыми функциями
 * @param <E> необходимый тип данных
 */
public class LinkList<E> implements Iterable<E>  {

    // ссылка на первый узел списка
    private Node<E> firstNode;
    // ссылка на последний узел списка
    private Node<E> lastNode;
    // размер списка
    private int size = 0;
    // счетчик изменений контейнера
    private int modCount = 0;

    /**
     * Конструктор
     */
    public LinkList() {
    }

    /**
     * Метод подсчета текущего размера хранилища данных
     * @return размер
     */
    public int size() {
        return size;
    }

    public Node<E> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node<E> firstNode) {
        this.firstNode = firstNode;
    }

    public Node<E> getLastNode() {
        return lastNode;
    }

    public void setLastNode(Node<E> lastNode) {
        this.lastNode = lastNode;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Метод добавляет объект в хранилище
     * @param model объект для добавления
     */
    public void add(E model) {
        if (size == 0) {
            this.firstNode = new Node<E>(model);
            this.lastNode = this.firstNode;
        } else {
            this.lastNode.setRight( new Node<E> (model));
            this.lastNode.getRight().setLeft( this.lastNode);
            this.lastNode = this.lastNode.getRight();
        }
        size++;
        modCount++;
    }

    /**
     * Метод получает объект в хранилище по нужному индексу
     * В случаи отсутствия нужного индекса выдает ошибку
     * @param index индекс хранения
     * @return объект типа E
     */
    public E get(int index) {
        if (index < size) {
            int i = 0;
            Iterator<E> iter = this.iterator();
            while (i++ < index) {
                iter.next();
            }
            return iter.next();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    /**
     *  Метод создает итератор для контейнера
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pointer = 0;
            Node<E> pointNode = firstNode;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = pointNode.getData();
                    pointer++;
                    pointNode = pointNode.getRight();
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
