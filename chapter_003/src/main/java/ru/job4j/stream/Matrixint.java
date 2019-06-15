package ru.job4j.stream;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class Matrixint {

    private final int sizeX;
    private final int sizeY;

    public Integer[][] generate() {
        Integer[][] result = new Integer[sizeX][sizeY];
        Random rnd = new Random();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                result[x][y] = rnd.nextInt(1000);
            }
        }
        return result;
    }
}
