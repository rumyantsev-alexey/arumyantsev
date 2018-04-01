package ru.job4j.words;

import java.util.HashMap;

/**
 * Класс сравнивает два слова между собой, раскидав первое слово в hashmap, где ключ это символ, а значение -
 * количество этих символов в слове
 */
public class HashWords {

    public static boolean compareTwoWords(String word, String otherword) {
        char [] w = word.toCharArray();
        char [] ow = otherword.toCharArray();
        HashMap<Character, Integer> array = new HashMap<>();
        int result = 0;
        for (int i = 0; i < w.length; i++) {
            array.put((Character) w[i], array.containsKey((Character) w[i]) ? array.get((Character) w[i])+1 : 1);
        }
        for (int i = 0; i < ow.length; i++) {
            array.put((Character) ow[i], array.get((Character) ow[i])-1);
        }
        for (Integer v: array.values()) {
            if (v != 0) {
                result = v;
                break;
            }
        }
    return result == 0;
    }

    }
