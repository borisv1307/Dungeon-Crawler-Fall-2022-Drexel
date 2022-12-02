package wrappers;

import java.security.SecureRandom;
import java.util.Random;

public class RandomWrapper {
	Random random;

	public RandomWrapper() {
		random = new SecureRandom();
	}

	public int nextInt(int maxValue) {
		return random.nextInt(maxValue);
	}
}
