package engine;

import entities.Enemy;
import entities.Kobold;
import entities.Orc;
import entities.Slime;
import tiles.TileType;
import wrappers.RandomizerWrapper;

import java.util.ArrayList;
import java.util.Arrays;

public class EnemySpawner {
    private RandomizerWrapper randomizerWrapper;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private GameEngine gameEngine;

    public EnemySpawner(RandomizerWrapper randomizerWrapper, GameEngine gameEngine) {
        this.randomizerWrapper = randomizerWrapper;
        this.gameEngine = gameEngine;
        this.levelVerticalDimension = gameEngine.getLevelVerticalDimension();
        this.levelHorizontalDimension = gameEngine.getLevelHorizontalDimension();
    }

    public Enemy spawnNewEnemy() {
        ArrayList<Integer> newCoordinates = getNewCoordinates();

        while (!newPointIsOpen(newCoordinates.get(0), newCoordinates.get(1))) {
            newCoordinates = getNewCoordinates();
        }

        return getRandomEnemy(newCoordinates.get(0), newCoordinates.get(1));
    }

    boolean newPointIsOpen(int x, int y) {
        TileType tileType = gameEngine.getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }

    Enemy getRandomEnemy(int x, int y) {
        int numberOfEnemyTypes = 3;
        int nextMonsterInt = randomizerWrapper.getNonRandomInt(numberOfEnemyTypes);

        switch (nextMonsterInt) {
            case 1:
                return new Kobold(x, y);
            case 2:
                return new Orc(x, y);
            default:
                return new Slime(x, y);
        }
    }

    ArrayList<Integer> getNewCoordinates() {
        int newXCoordinate = getRandomNewCoordinate(levelHorizontalDimension);
        int newYCoordinate = getRandomNewCoordinate(levelVerticalDimension);

        while (!newPointIsOpen(newXCoordinate, newYCoordinate)) {
            newXCoordinate = getRandomNewCoordinate(levelHorizontalDimension);
            newYCoordinate = getRandomNewCoordinate(levelVerticalDimension);
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    int getRandomNewCoordinate(int dimensionLimit) {
        return randomizerWrapper.getNonRandomInt(dimensionLimit);
    }
}
