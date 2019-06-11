package ru.job4j.array;

public class InString {

    /**
     * Отвечает на вопрос: Входит ли вторая строка в первую
     * @param origin первая строка
     * @param sub вторая строка
     * @return Ответ.
     *
     */

    boolean contains(String origin, String sub) {
        char[] chorigin = origin.toCharArray();
        char[] chsub = sub.toCharArray();
        boolean subs = false;
        if (origin != null && sub != null && origin.length() >= sub.length()) {
            for (int i = 0; !subs && i < (origin.length() - sub.length());  i++) {
                if (chsub[0] == chorigin[i]) {
                    subs = true;
                    for (int j = 1; subs && j < sub.length(); j++) {
                        subs = chsub[j] == chorigin[i + j];
                    }
                }
            }
        }
        return subs;
    }
}
