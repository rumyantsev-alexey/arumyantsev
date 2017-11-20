package ru.job4j.condition;

public class DummyBot {
    /**
     * Отвечает на вопросы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        switch (question){
            case "Привет, Бот.":
                return "Привет, умник.";
            case "Пока.":
                return "До скорой встречи.";
            case "Сколько будет 2 + 2?":
                return "Это ставит меня в тупик. Спросите другой вопрос.";
        }
        return "";
    }
}