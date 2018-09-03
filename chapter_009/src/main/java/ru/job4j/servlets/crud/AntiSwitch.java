package ru.job4j.servlets.crud;

import java.util.HashMap;
import java.util.function.BiPredicate;

/**
 * Класс реализующий механизм AntiSwitch
 */
public class AntiSwitch {
    private HashMap<String, BiPredicate<Integer,String>> list = new HashMap<>();

    /**
     * Метод реализует сохранение значения и соответствующего ему действия
     * @param choose значение
     * @param action действие
     */
    public void load(String choose, BiPredicate<Integer,String> action) {
        list.put(choose, action);
    }

    /**
     * Метод реализует выполнение действия, соответствующего значению
     * @param choose значение
     * @param id параметры
     * @param name параметры
     * @return успех
     */
    public boolean run(String choose, int id, String name) {
        return list.get(choose) == null || list.get(choose).test(id, name);
    }

}
