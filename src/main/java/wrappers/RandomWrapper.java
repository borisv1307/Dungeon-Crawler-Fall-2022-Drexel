package wrappers;

import java.security.SecureRandom;

public class RandomWrapper {
    SecureRandom random = new SecureRandom();

    public double mathRandom() {
        return random.nextDouble();
    }
}
