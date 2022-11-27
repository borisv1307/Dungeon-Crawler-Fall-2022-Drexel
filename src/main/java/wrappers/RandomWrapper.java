package wrappers;

import java.util.concurrent.ThreadLocalRandom;

public class RandomWrapper {
	public int getRandomNumberInRange(int lower, int upper) {
		return ThreadLocalRandom.current().nextInt(lower, upper + 1);
	}
}
