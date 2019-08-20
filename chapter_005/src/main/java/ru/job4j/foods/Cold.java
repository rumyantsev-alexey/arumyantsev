package ru.job4j.foods;

import java.util.Date;

/**
 * Склад для продуктов помеченных как Овощи
 */
public class Cold extends Store<Food> {

    public Cold(String name) {
        super(name);
    }

    public Cold(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    boolean accept(Food food, Date check) {
        boolean result = false;
        try {
            FoodAdv fooda = (FoodAdv) food;
            result = food.expPercent(check) < 100 && fooda.isVegetable() && this.checkFree();
        } catch (ClassCastException e) {
        }
        return result;
    }
}
