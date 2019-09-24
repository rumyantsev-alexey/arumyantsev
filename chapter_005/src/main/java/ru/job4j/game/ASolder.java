package ru.job4j.game;

import lombok.Getter;

/**
 * Абстрактный класс описывающий бойца. Вводит поля раса и специализация для всех солдат
 */
abstract class ASolder implements ISolder {
    @Getter
    private final Rise rise;

    @Getter
    private final Spec spec;

    public ASolder(Rise rise, Spec spec) {
        this.rise = rise;
        this.spec = spec;
    }
}
