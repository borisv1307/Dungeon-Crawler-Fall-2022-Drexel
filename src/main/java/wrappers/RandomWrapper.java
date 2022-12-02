package wrappers;

import java.util.Random;

public class RandomWrapper {
	Random random;

	public RandomWrapper() {
		random = new Random();
	}

	public int nextInt(int maxValue) {
		return random.nextInt(maxValue);
	}
}
