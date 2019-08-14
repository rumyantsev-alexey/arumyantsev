package ru.job4j.foods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            Method recycle = food.getClass().getMethod("isVegetable");
            result = food.expPercent(check) < 100 && (Boolean) recycle.invoke(food) && this.checkFree();

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        }

        return result;
    }
}
