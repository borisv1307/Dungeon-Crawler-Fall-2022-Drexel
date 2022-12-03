package wrappers;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomWrapper {

    private static final int minNumberOfObjects = 1;
    private static final int maxNumberOfObjects = 5;
    ArrayList<Point> allPassableTiles;
    SecureRandom secureRandom;

    public RandomWrapper() {
        this.allPassableTiles = new ArrayList<>();
        this.secureRandom = new SecureRandom();
    }

    public int getRandomNumberOfObjects() {
        return secureRandom.nextInt(maxNumberOfObjects - minNumberOfObjects + 1) + minNumberOfObjects;
    }

    public int getRandomIntInRange(int max) {
        return secureRandom.nextInt(max);
    }

    public Point getRandomPassableTile() throws RuntimeException {
        ArrayList<Point> passableTiles = (ArrayList<Point>) getAllPassableTiles();
        int numberOfPassableTiles = passableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = getRandomIntInRange(numberOfPassableTiles);
            return passableTiles.get(randomIndex);
        } else {
            throw new RuntimeException("No passable tiles available");
        }
    }

    public List<Point> getAllPassableTiles() {
        return allPassableTiles;
    }

    public void setAllPassableTiles(List<Point> allPassableTiles) {
        this.allPassableTiles = (ArrayList<Point>) allPassableTiles;
    }
}
