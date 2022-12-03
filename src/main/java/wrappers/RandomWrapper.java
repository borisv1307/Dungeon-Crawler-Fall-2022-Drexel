package wrappers;

import exceptions.PassableTileException;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomWrapper {

    private static final int MIN_NUMBER_OF_OBJECTS = 1;
    private static final int MAX_NUMBER_OF_OBJECTS = 5;
    ArrayList<Point> allPassableTiles;
    SecureRandom secureRandom;

    public RandomWrapper() {
        this.allPassableTiles = new ArrayList<>();
        this.secureRandom = new SecureRandom();
    }

    public int getRandomNumberOfObjects() {
        return secureRandom.nextInt(MAX_NUMBER_OF_OBJECTS - MIN_NUMBER_OF_OBJECTS + 1) + MIN_NUMBER_OF_OBJECTS;
    }

    public int getRandomIntInRange(int max) {
        return secureRandom.nextInt(max);
    }

    public Point getRandomPassableTile() throws PassableTileException {
        ArrayList<Point> passableTiles = (ArrayList<Point>) getAllPassableTiles();
        int numberOfPassableTiles = passableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = getRandomIntInRange(numberOfPassableTiles);
            return passableTiles.get(randomIndex);
        } else {
            throw new PassableTileException("No passable tiles available");
        }
    }

    public List<Point> getAllPassableTiles() {
        return allPassableTiles;
    }

    public void setAllPassableTiles(List<Point> allPassableTiles) {
        this.allPassableTiles = (ArrayList<Point>) allPassableTiles;
    }
}
