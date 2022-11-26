package wrappers;

import static java.lang.Math.random;

public class RandomWrapper {

    public int getRandomIntInRange(int min, int max) {
        return (int) ((random() * (max - min)) + min);
    }
}
