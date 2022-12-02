package wrappers;

import entities.Enemy;
import entities.Kobold;
import entities.Orc;
import entities.Slime;

public class RandomizerWrapper {
    SystemWrapper systemWrapper;

    public RandomizerWrapper(SystemWrapper systemWrapper) {
        this.systemWrapper = systemWrapper;
    }

    public int getNonRandomInt(int limit) {
        long nanoTime = systemWrapper.milliTime();
        int digit = (int) Math.abs(nanoTime % 100);

        return digit % -limit;
    }

    public Enemy getRandomEnemy(int x, int y) {
        int numberOfEnemyTypes = 3;
        int nextMonsterInt = getNonRandomInt(numberOfEnemyTypes);

        switch (nextMonsterInt) {
            case 1:
                return new Kobold(x, y);
            case 2:
                return new Orc(x, y);
            default:
                return new Slime(x, y);
        }
    }

    public int getRandomNewCoordinate(int dimensionLimit) {
        return getNonRandomInt(dimensionLimit);
    }
}
