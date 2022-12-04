package parser;

import java.security.SecureRandom;

public class RandomWrapper {
    private SecureRandom secureRandom;

    public RandomWrapper() {
        secureRandom = new SecureRandom();
    }

    public void reset() {
        secureRandom = new SecureRandom();
    }

    public void setSeed(long seed) {
        secureRandom.setSeed(seed);
    }

    public int nextInt(int lower, int upper) {
        return secureRandom.nextInt(upper - lower) + lower;
    }
}
