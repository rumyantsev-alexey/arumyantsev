package ru.job4j.game;

import java.util.List;

/**
 * Абстрактный класс описывающий отряд
 */
abstract class ASquad implements ISquad {
    private final List<ISolder> solders;

    public ASquad(List<ISolder> solders) {
        this.solders = solders;
    }
}
