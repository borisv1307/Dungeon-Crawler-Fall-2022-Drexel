package wrappers;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class RandomWrapper {

    ArrayList<Point> allPassableTiles;
    SecureRandom secureRandom;

    public int getRandomNumberOfObjects() {
        secureRandom = new SecureRandom();
        int min = 1;
        int max = 5;
        return secureRandom.nextInt(max - min + 1) + min;
    }

    public int getRandomIntInRange(int max) {
        secureRandom = new SecureRandom();
        return secureRandom.nextInt(max);
    }

    public Point getRandomPassableTile() {
        ArrayList<Point> passableTiles = getAllPassableTiles();
        int numberOfPassableTiles = passableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = getRandomIntInRange(numberOfPassableTiles);
            return passableTiles.get(randomIndex);
        } else {
            return null;
        }
    }

    public ArrayList<Point> getAllPassableTiles() {
        return allPassableTiles;
    }

    public void setAllPassableTiles(ArrayList<Point> allPassableTiles) {
        this.allPassableTiles = allPassableTiles;
    }
}
