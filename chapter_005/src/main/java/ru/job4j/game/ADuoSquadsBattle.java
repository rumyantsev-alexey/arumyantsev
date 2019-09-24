package ru.job4j.game;

/**
 * Абстрактный класс для описания битвы двух сквадов
 */
abstract class ADuoSquadsBattle implements IBattle {
    private final ISquad sq1;
    private final ISquad sq2;

    /**
     * Конструктор битвы
     * @param sq1 первый отряд
     * @param sq2 второй отряд
     */
    public ADuoSquadsBattle(ISquad sq1, ISquad sq2) {
        this.sq1 = sq1;
        this.sq2 = sq2;
    }
}
