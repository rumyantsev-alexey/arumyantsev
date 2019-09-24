package ru.job4j.game;

/**
 * Класс реализует функционал эльфа солдата
 */
public class ElfSolder extends ASolder {

    public ElfSolder() {
        super(Rise.Elf, Spec.Melee);
    }

    @Override
    public Hit atackMain() {
        return null;
    }

    @Override
    public Hit atackDop() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public ISolder hit(Hit hit) {
        return null;
    }
}
