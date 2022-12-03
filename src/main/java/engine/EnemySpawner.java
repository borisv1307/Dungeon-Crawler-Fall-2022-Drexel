package engine;

import creatures.Enemy;
import creatures.Kobold;
import creatures.Orc;
import creatures.Slime;
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
        return isTilePassable(x, y) && isNotPlayerSpawn(x, y);
    }

    boolean isTilePassable(int x, int y) {
        return gameEngine.getTileFromCoordinates(x, y) == TileType.PASSABLE;
    }

    boolean isNotPlayerSpawn(int x, int y) {
        ArrayList<Integer> playerSpawnCoordinates = gameEngine.getPlayerSpawnPoint();
        if (playerSpawnCoordinates != null && playerSpawnCoordinates.size() == 2) {
            return (playerSpawnCoordinates.get(0) != x && playerSpawnCoordinates.get(1) != y);
        }
        return true;
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
