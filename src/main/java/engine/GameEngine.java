package engine;

import entities.Enemy;
import entities.Player;
import entities.Slime;
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
    private String gameStatus;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        gameStatus = GameStatus.PLAYER_RESPAWNED;
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
        gameStatus = String.format(GameStatus.ENEMY_DEFEATED, enemy.getName());
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
        gameStatus = getCombatStatus();

        if (enemyHP <= 0) {
            enemyKilled(getEnemyXCoordinate(), getEnemyYCoordinate());
        }

        if (playerHP <= 0) {
            playerKilled(getPlayerXCoordinate(), getPlayerYCoordinate());
        }
    }

    private String getCombatStatus() {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
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
        int newXCoordinate = Randomizer.getRandomNewCoordinate(getLevelHorizontalDimension());
        int newYCoordinate = Randomizer.getRandomNewCoordinate(getLevelVerticalDimension());

        while (!newPointIsValid(newXCoordinate, newYCoordinate)) {
            newXCoordinate = Randomizer.getRandomNewCoordinate(getLevelHorizontalDimension());
            newYCoordinate = Randomizer.getRandomNewCoordinate(getLevelVerticalDimension());
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    private boolean newPointIsValid(int x, int y) {
        TileType tileType = getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }

    private void createNewEnemy(int x, int y) {
        enemy = Randomizer.getRandomEnemy(x, y);
        tiles.put(enemy, enemy.getTileType());
    }

    public void playerKilled(int x, int y) {
        removeTile(x, y);
        player = new Player(player.getOriginX(), player.getOriginY());
        gameStatus = GameStatus.PLAYER_DEFEATED;
    }

    public String getGameStatus() {
        return this.gameStatus;
    }
}
