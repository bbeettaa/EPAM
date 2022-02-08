package nau.university.HW_Task_7.mvc;

import java.util.Random;

public class Randomizer {
    final int RAND_MAX = 100;
    final int RAND_MIN = 0;

    public int rand(int min, int max) {
        if (min < 0 || max > 100) throw new IllegalArgumentException();

        Random rand = new Random();

        return rand.nextInt(max - min) + min;
    }

    public int rand() {
        Random rand = new Random();
        return rand.nextInt(0, RAND_MAX);
    }
}
