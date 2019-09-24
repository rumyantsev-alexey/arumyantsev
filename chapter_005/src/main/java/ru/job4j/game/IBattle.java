package ru.job4j.game;

import java.util.List;

/**
 * Интерфейс описывающий битву
 */
public interface IBattle {
    /**
     * Метод реализует битву
     */
    void fight();

    /**
     * Метод показывает лог законченной битвы
     * @return лог
     */
    List<String> showBattleLog();
}
