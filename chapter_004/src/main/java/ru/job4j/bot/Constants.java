package ru.job4j.bot;

import java.util.List;
import java.util.Map;

public final class Constants {
    protected static final byte[] IP = {127, 0, 0, 1};
    protected static final int PORT = 5555;
    protected static final Map<String, List<String>> ANSW = Map.of("привет", List.of("Доброго дня суток!", "Великий Оракул тебя слушает"),
                                                                "как дела", List.of("Мои дела хорошо"),
                                                                "какая моя судьба", List.of("Звезды в тумане", "Все зависит от тебя", "Дерзай"),
                                                                    "выход", List.of("Великий Оракул прощается с тобой"));
}
