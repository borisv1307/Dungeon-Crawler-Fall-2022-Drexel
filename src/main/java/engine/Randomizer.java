package engine;

import entities.Enemy;
import entities.Kobold;
import entities.Orc;
import entities.Slime;
import wrappers.SystemWrapper;

public class Randomizer {
    static SystemWrapper systemWrapper = new SystemWrapper();

    private static int getNonRandomInt(int limit) {
        long nanoTime = systemWrapper.milliTime();
        int digit = (int) Math.abs(nanoTime % 100);

        return digit % -limit;
    }

    static Enemy getRandomEnemy(int x, int y) {
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

    static int getRandomNewCoordinate(int dimensionLimit) {
        return Randomizer.getNonRandomInt(dimensionLimit);
    }
}
