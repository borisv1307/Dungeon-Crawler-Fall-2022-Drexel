package wrappers;

import static java.lang.Math.random;

public class RandomWrapper {

    public int getRandomNumberOfObjects() {
        int min = 1;
        int max = 5;
        return (int) ((random() * (max - min)) + min);
    }

    public int getRandomIntInRange(int min, int max) {
        return (int) ((random() * (max - min)) + min);
    }
}
