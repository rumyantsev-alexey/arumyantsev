package ru.job4j.foods;

import java.util.Date;
import java.util.List;

/**
 * Класс контроля качества продуктов
 */
public class ControlQuality {

    /**
     * Метод проверяет продукт на сооствествие по порядку в котором определены правила
     * @param currtime дата проверки
     * @param food продукт
     * @param stores список складов
     * @throws NoRulesException продукт не прошел ни один тест
     */
    public static void pass(Date currtime, Food food, List<Store<Food>> stores) throws NoRulesException {
        boolean status = false;
        for (Store<Food> store: stores) {
            if (store.accept(food, currtime)) {
                store.add(food);
                status = true;
                break;
            }
        }
       if (!status) {
            throw new NoRulesException("Продукт никуда не ушел ((");
        }
    }
}
