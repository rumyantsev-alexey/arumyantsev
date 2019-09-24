package ru.job4j.game;

import java.util.List;

/**
 * интерфейс описывает отряд
 */
public interface ISquad {

    /**
     * Есть ли кто живой в отряде?
     * @return есть?
     */
    boolean isEmpty();

    /**
     * Список живых солдат в отряде на текущий момент
     * @return список
     */
    List<ISolder> listSquad();

    /**
     * Подготовка отряда к очередному ходу в битве.
     * Отряд выстраивается в очередь соглассно инициативе
     * @return список
     */
    List<ISolder>  preparationToRoundBattle();
}
