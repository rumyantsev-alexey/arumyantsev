package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс определяет итератор, который ходит по простым числам в массиве, заданном в конструкторе
 */
public class PrimeIterator implements Iterator {
    // Внутреннее представление состоящее из четных чисел из исходного массива
    private int [] array;
    // Указатель на текущую позицию итератора
    private int pointer = 0;

    /**
     * Конструктор с входящим массивом
     * @param numbers входной массив
     */
    PrimeIterator (final int[] numbers) {
        // определяем размер внутреннего массива
        int arraylen=0;
        for( int i=0; i < numbers.length; i++) {
            if ( primenumber(numbers[i])){
                arraylen++;
            }
        }
        array = new int[arraylen];
        // перекидываем данные во внутренний массив
        int t = 0;
        for( int i=0; i < numbers.length; i++) {
            if (primenumber(numbers[i])) {
                array[t++] = numbers[i];
            }
        }
    }

    private boolean primenumber (int number) {
        boolean result = number > 1;
        for (int i=2; i < number; i++) {
            if (number%i==0) {
                result = false;
                break;
            }
        }
        return result;
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
