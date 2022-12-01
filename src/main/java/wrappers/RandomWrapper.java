package wrappers;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.random;

public class RandomWrapper {

    ArrayList<Point> allPassableTiles;

    public int getRandomNumberOfObjects() {
        int min = 1;
        int max = 5;
        return (int) ((random() * (max - min)) + min);
    }

    public int getRandomIntInRange(int min, int max) {
        return (int) ((random() * (max - min)) + min);
    }

    public Point getRandomPassableTile() {
        ArrayList<Point> passableTiles = getAllPassableTiles();
        int numberOfPassableTiles = passableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = getRandomIntInRange(0, numberOfPassableTiles);
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
