package ru.job4j.availability;

import java.util.Arrays;

/**
 * Класс возможных состояний сервера
 */
enum Status {
    GOOD("200"),
    NO_GOOD("300"),
    BAD("400"),
    VERY_BAD("500"),
    UNKNOW("0");

    String state;

    Status(String str) {
        this.state = str;
    }

    public static Status getEnumStatus(String str) {
        return Arrays.stream(Status.values()).filter(x -> x.state.equals(str)).findFirst().orElse(UNKNOW);
    }
}