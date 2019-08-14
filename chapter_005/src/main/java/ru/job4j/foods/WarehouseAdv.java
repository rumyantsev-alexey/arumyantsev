package ru.job4j.foods;

import java.util.Date;

/**
 * Склад расширяющий склад Warehouse
 */
public class WarehouseAdv extends Store<Food> {
    private final Warehouse ware;

    public WarehouseAdv(String name, Warehouse ware) {
        super(name);
        this.ware = ware;
    }

    public WarehouseAdv(String name, int capacity, Warehouse ware) {
        super(name, capacity);
        this.ware = ware;
    }
    @Override
    boolean accept(Food food, Date check) {
        return !ware.accept(food, check) && food.expPercent(check) < 25 && this.checkFree();
    }
}
