package ru.job4j.game;

/**
 * Класс реализует функционал эльфа мага
 */
public class ElfMage extends ASolder {

    public ElfMage() {
        super(Rise.Elf, Spec.Mage);
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
