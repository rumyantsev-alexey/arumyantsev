package ru.job4j.foods;

import java.util.Date;

/**
 * Класс магазина продуктов
 */
public final class Shop extends Store<Food> {

    public Shop(String name) {
        super(name);
    }

    public Shop(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    boolean accept(Food food, Date check) {
        boolean result = false;
        int prc = food.expPercent(check);
        if (prc >= 25 && prc < 100 && this.checkFree()) {
            if (prc >= 75) {
                food.setPrice(food.getDiscountPrice());
            }
//            this.add(food);
            result = true;
        }
        return result;
    }
}
