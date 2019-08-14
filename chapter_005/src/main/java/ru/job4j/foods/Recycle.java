package ru.job4j.foods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Склад для продуктов которые возможно переработать
 */
public class Recycle extends Store<Food> {

    private final Trash trash;

    public Recycle(String name, Trash trash) {
        super(name);
        this.trash = trash;
    }

    public Recycle(String name, int capacity, Trash trash) {
        super(name, capacity);
        this.trash = trash;
    }
    @Override
    boolean accept(Food food, Date check) {
        boolean result = false;
        try {
            Method recycle = food.getClass().getMethod("isCanReproduct");
            result = trash.accept(food, check) && (Boolean) recycle.invoke(food) && this.checkFree();

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        }

        return result;
    }
}
