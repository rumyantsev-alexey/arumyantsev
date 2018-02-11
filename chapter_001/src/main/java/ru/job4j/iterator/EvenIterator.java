package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс определяет итератор, который ходит по четным числам в массиве, заданном в конструкторе
 */
public class EvenIterator implements Iterator {
    // Внутреннее представление состоящее из четных чисел из исходного массива
    private int [] array;
    // Указатель на текущую позицию итератора
    private int pointer = 0;

    /**
     * Конструктор с входящим массивов
     * @param numbers входной массив
     */
    EvenIterator (final int[] numbers) {
        // определяем размер внутреннего массива
        int arraylen=0;
        for( int i=0; i < numbers.length; i++) {
            if (numbers[i]%2==0) {
                arraylen++;
            }
        }
        array = new int[arraylen];
        // перекидываем данные во внутренний массив
        int t = 0;
        for( int i=0; i < numbers.length; i++) {
            if (numbers[i]%2==0) {
                array[t++] = numbers[i];
            }
        }
    }

    @Override
    public boolean hasNext() {
        return pointer < array.length;
    }

    @Override
    public Integer next() {
        Integer result = null;
        if (pointer < array.length){
            result = array[pointer++];
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}
