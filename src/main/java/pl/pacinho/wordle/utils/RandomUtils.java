package pl.pacinho.wordle.utils;

import java.util.Random;

public class RandomUtils {
    public static int randomInt(long size) {
        return new Random()
                .nextInt((int) size);
    }
}
