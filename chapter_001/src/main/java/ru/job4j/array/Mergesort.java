package ru.job4j.array;

import java.util.Arrays;

public class Mergesort {


    /**
     * Слияние двух отсортированных массивов в третий отсортированный
     * @param arr1 первый массив
     * @param arr2 второй массив
     * @return результирующий массив
     *
     */

    public int[] msort(int[] arr1, int[] arr2) {

        // определяем результирующий массив суммарной длины 2х массивов
        int[] resarr = new int[arr1.length + arr2.length];

        // инициализируем счетчики 3х массивов
        int i= 0;
        int j=0;
        int k=0;

        //сравниваем элементы массива попарно, пока не кончиться один из массивов
        while (i < arr1.length && j < arr2.length)
            resarr[k++]=arr1[i]<=arr2[j]?arr1[i++]:arr2[j++];

        // смотрим какой из массивов первым закончился и копируем остатки второго массива в результирующий
        if (i == arr1.length )
            for (int m=j;m<=arr2.length-1;m++)
                resarr[k++]=arr2[m];
        else
            for (int m=i;m<=arr1.length-1;m++)
                resarr[k++]=arr1[m];

        return resarr;
    }
}
