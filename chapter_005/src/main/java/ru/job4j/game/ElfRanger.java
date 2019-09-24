package ru.job4j.game;

/**
 * Класс реализует функционал эльфа ренджера
 */
public class ElfRanger extends ASolder {

    public ElfRanger() {
        super(Rise.Elf, Spec.Range);
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
