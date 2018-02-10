package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Класс определяет итератор, который ходит по 2х мерному массиву, заданному в конструкторе
 */
public class MatrixIterator implements Iterator<Integer> {
    // Внутреннее представление 2х мерного массива
    private int [] array;
    // Указатель на текущую позицию итератора
    private int pointer = 0;

    /**
     * Конструктор с входящим массивов
     * @param input входной массив
     */
    MatrixIterator( int [][] input) {
        // определяем размер внутреннего массива
        int arraylen=0;
        for( int i=0; i < input.length; i++) {
            arraylen+=input[i].length;
        }
        array = new int[arraylen];
        // перекидываем данные во внутренний массив
        int t = 0;
        for( int i=0; i < input.length; i++) {
            for (int j=0; j < input[i].length; j++)
                array[t++]=input[i][j];
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
        }
        return result;
    }
}
