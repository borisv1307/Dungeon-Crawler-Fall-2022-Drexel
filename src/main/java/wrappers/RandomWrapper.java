package wrappers;

import java.security.SecureRandom;

public class RandomWrapper {
	private final SecureRandom random = new SecureRandom();

	public int getRandomNumberInRange(int lower, int upper) {
		return random.nextInt(upper - lower + 1) + lower;
	}
}
