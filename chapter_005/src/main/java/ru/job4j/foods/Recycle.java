package ru.job4j.foods;

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
            FoodAdv fooda = (FoodAdv) food;
            result = trash.accept(food, check) && fooda.isCanReproduct() && this.checkFree();
        } catch (ClassCastException e) {
        }
            return result;
    }
}
