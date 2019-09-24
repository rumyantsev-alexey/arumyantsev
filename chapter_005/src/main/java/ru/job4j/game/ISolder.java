package ru.job4j.game;

/**
 * Интерфейс описывает солдата
 */
public interface ISolder {
    /**
     * Основная атака
     * @return удар по противнику
     */
    Hit atackMain();

    /**
     * Дополнительная атака
     * @return удар по противнику
     */
    Hit atackDop();

    /**
     * Метод описывает получение удара солдатом
     * @param hit удар по солдату
     * @return солдат после удара
     */
    ISolder hit(Hit hit);

    /**
     * Живой ли солдат
     * @return живой?
     */
    boolean isAlive();
}
