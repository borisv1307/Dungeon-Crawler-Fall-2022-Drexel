package wrappers;

import java.util.Random;

public class RandomWrapper {
    Random r = new Random();

    public int generateCoordinate(int bound) {
        return r.nextInt(bound - 1) + 1;
    }
}
