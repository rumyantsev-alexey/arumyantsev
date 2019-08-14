package ru.job4j.foods;

import java.util.Date;

/**
 * Класс склада продуктов
 */
public class Warehouse extends Store<Food> {

    public Warehouse(String name) {
        super(name);
    }

    public Warehouse(String name, int capacity) {
        super(name, capacity);
    }
    @Override
    boolean accept(Food food, Date check) {
        boolean result = false;
        if (food.expPercent(check) < 25 && this.checkFree()) {
//            this.add(food);
            result = true;
        }
        return result;
    }
}
