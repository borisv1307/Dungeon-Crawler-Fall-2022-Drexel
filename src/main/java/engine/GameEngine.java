package engine;

import entities.*;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Player player;
    private Enemy enemy;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
    }

    public void run(GameFrame gameFrame) {
        for (Component component : gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public void addTile(int x, int y, TileType tileType) {
        if (tileType.equals(TileType.PLAYER)) {
            setPlayer(x, y);
            tiles.put(player, TileType.PASSABLE);
        } else if (isEnemyTile(tileType)) {
            setEnemy(x, y);
            tiles.put(enemy, enemy.getTileType());
        } else {
            tiles.put(new Point(x, y), tileType);
        }
    }

    public int getLevelHorizontalDimension() {
        return levelHorizontalDimension;
    }

    public void setLevelHorizontalDimension(int levelHorizontalDimension) {
        this.levelHorizontalDimension = levelHorizontalDimension;
    }

    public int getLevelVerticalDimension() {
        return levelVerticalDimension;
    }

    public void setLevelVerticalDimension(int levelVerticalDimension) {
        this.levelVerticalDimension = levelVerticalDimension;
    }

    public TileType getTileFromCoordinates(int x, int y) {
        return tiles.get(new Point(x, y));
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public int getEnemyXCoordinate() {
        return (int) enemy.getX();
    }

    public int getEnemyYCoordinate() {
        return (int) enemy.getY();
    }

    public void keyLeft() {
        movement(-1, 0);
    }

    public void keyRight() {
        movement(1, 0);
    }

    public void keyUp() {
        movement(0, -1);
    }

    public void keyDown() {
        movement(0, 1);
    }

    public void enemyKilled(int x, int y) {
        ArrayList<Integer> newCoordinates = getNewCoordinates();
        removeTile(x, y);
        createNewEnemy(newCoordinates.get(0), newCoordinates.get(1));
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void removeTile(int x, int y) {
        tiles.put(new Point(x, y), TileType.PASSABLE);
    }

    private void setPlayer(int x, int y) {
        if (player == null) {
            player = new Player(x, y);
        } else {
            player = player.copyPlayerToNewLocation(x, y);
        }
    }

    private void setEnemy(int x, int y) {
        enemy = new Slime(x, y);
    }

    private void movement(int deltaX, int deltaY) {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        } else if (locationHasEnemy(attemptedLocation)) {
            doCombat();
        }
    }

    private void doCombat() {
        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());

        if (playerHP <= 0) {
            playerKilled(getPlayerXCoordinate(), getPlayerYCoordinate());
        }
        if (enemyHP <= 0) {
            enemyKilled(getEnemyXCoordinate(), getEnemyYCoordinate());
        }
    }

    private boolean isEnemyTile(TileType tileType) {
        switch (tileType) {
            case KOBOLD:
            case SLIME:
            case ORC:
                return true;
            default:
                return false;
        }
    }

    private boolean locationHasEnemy(TileType attemptedLocation) {
        return enemy != null && isEnemyTile(attemptedLocation);
    }

    private ArrayList<Integer> getNewCoordinates() {
        int newXCoordinate = getRandomNewCoordinate(getLevelHorizontalDimension());
        int newYCoordinate = getRandomNewCoordinate(getLevelVerticalDimension());

        while (!newPointIsValid(newXCoordinate, newYCoordinate)) {
            newXCoordinate = getRandomNewCoordinate(getLevelHorizontalDimension());
            newYCoordinate = getRandomNewCoordinate(getLevelVerticalDimension());
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    private boolean newPointIsValid(int x, int y) {
        TileType tileType = getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }

    private int getRandomNewCoordinate(int dimensionLimit) {
        return getNonRandomInt(dimensionLimit);
    }

    private void createNewEnemy(int x, int y) {
        enemy = getRandomEnemy(x, y);
        tiles.put(enemy, enemy.getTileType());
    }

    private Enemy getRandomEnemy(int x, int y) {
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

    private int getNonRandomInt(int limit) {
        long milliseconds = System.currentTimeMillis();
        int digit = (int) Math.abs(milliseconds % 100);
        
        return digit % -limit;
    }

    public void playerKilled(int x, int y) {
        removeTile(x, y);
        player = new Player(player.getOriginX(), player.getOriginY());
    }
}
