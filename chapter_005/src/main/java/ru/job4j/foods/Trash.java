package ru.job4j.foods;

import java.util.Date;

/**
 * Класс свалки продуктов
 */
public class Trash extends Store<Food> {

    public Trash(String name) {
        super(name);
    }

    public Trash(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    boolean accept(Food food, Date check) {
        boolean result = false;
        if (food.expPercent(check) >= 100 && this.checkFree()) {
//            this.add(food);
            result = true;
        }
        return result;
    }
}
