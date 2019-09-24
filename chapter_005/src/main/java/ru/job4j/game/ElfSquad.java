package ru.job4j.game;

import java.util.List;

/**
 * Класс реализует функционал отряда эльфов
 */
public class ElfSquad extends ASquad {

    /**
     * Конструктор отряда
     * @param solders солдаты отряда
     * @throws AlienRiseException если солдат в отряде не той расы
     */
    public ElfSquad(List<ISolder> solders) throws AlienRiseException {
        super(solders);
        if (solders.stream().map((x) -> (ASolder) x).anyMatch((x) -> x.getRise() != Rise.Elf)) {
            throw new AlienRiseException();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public List<ISolder> listSquad() {
        return null;
    }

    @Override
    public List<ISolder> preparationToRoundBattle() {
        return null;
    }
}
