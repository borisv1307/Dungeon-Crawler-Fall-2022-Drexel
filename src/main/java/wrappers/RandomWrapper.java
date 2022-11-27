package wrappers;

import java.util.Random;

public class RandomWrapper {
	public int getRandomNumberInRange(int lower, int upper) {
		Random random = new Random();
		return random.nextInt(upper - lower + 1) + lower;
	}
}
