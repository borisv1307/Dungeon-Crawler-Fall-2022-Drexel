package wrappers;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Point> passableTiles = (ArrayList<Point>) getAllPassableTiles();
        int numberOfPassableTiles = passableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = getRandomIntInRange(numberOfPassableTiles);
            return passableTiles.get(randomIndex);
        } else {
            return null;
        }
    }

    public List<Point> getAllPassableTiles() {
        return allPassableTiles;
    }

    public void setAllPassableTiles(List<Point> allPassableTiles) {
        this.allPassableTiles = (ArrayList<Point>) allPassableTiles;
    }
}
