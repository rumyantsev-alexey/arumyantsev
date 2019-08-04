package ru.job4j.foods;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Абстрактный класс склада
 * @param <T> тип тавара
 */
abstract class Store<T> {
    private static final int DEFAULTCAPACITY = 100;

    private List<T> goods = new ArrayList<>();

    @Getter
    private String name;

    private int capacity;

    private int count = 0;

    public Store(String name) {
        this.name = name;
        this.capacity = DEFAULTCAPACITY;
    }
    public Store(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void add(T good) {
        goods.add(good);
        count++;
    }

    public List<T> listGoods() {
        return new ArrayList<>(goods);
    }

    public void clear() {
        goods = new ArrayList<>();
    }

    public boolean checkFree() {
        return count < capacity;
    }

    abstract boolean accept(Food food, Date check);
}
