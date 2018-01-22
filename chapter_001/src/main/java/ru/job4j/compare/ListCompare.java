package ru.job4j.compare;

import java.util.Comparator;
import java.util.List;

/**
 * Класс сравнения двух списков
 */
public class ListCompare implements Comparator<List<Integer>> {

    /**
     * Метод сравнения двух списков
     * @param left первый список
     * @param right второй список
     * @return результат сравнения
     */
    @Override
    public int compare(List<Integer> left, List<Integer> right) {
        Integer lz = left.size();
        Integer rz = right.size();
        if (lz!=rz) {
            return lz.compareTo(rz);
        }
        for (int i=0; i<lz; i++) {
            if (left.get(i)!=right.get(i)) {
                return left.get(i).compareTo(right.get(i));
            }
        }
        return 0;
    }
}
