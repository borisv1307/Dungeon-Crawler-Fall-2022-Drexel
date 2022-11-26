package wrappers;

public class RandomWrapper {

    public int getRandomIntInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
