package ru.job4j.tttconsole;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Абстрактный класс игрока в 3Т
 */
@AllArgsConstructor
abstract class Player {

    @Getter
    private String name;

    abstract Point getStep(Board board);

    @Override
    public String toString() {
        return "Player:" + name;
    }
}
