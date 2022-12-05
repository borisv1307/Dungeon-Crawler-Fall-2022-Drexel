package wrappers;

import java.security.SecureRandom;

public class RandomWrapper {
    SecureRandom r = new SecureRandom();

    public int generateCoordinate(int bound) {
        return r.nextInt(bound - 1) + 1;
    }
}
