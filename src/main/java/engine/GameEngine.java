package engine;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Slime;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomizerWrapper;

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
    private RandomizerWrapper randomizerWrapper;
    private CombatEngine combatEngine;

    public GameEngine(LevelCreator levelCreator, RandomizerWrapper randomizerWrapper) {
        exit = false;
        level = 1;
        this.randomizerWrapper = randomizerWrapper;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        gameStatus = GameStatus.PLAYER_RESPAWNED;
        combatEngine = new CombatEngine(randomizerWrapper);
    }

    public void run(GameFrame gameFrame) {
        for (Component component : gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public void addTile(int x, int y, TileType tileType) {
        if (tileType.equals(TileType.PLAYER)) {
            setPlayer(x, y);
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
            int previousX = getPlayerXCoordinate();
            int previousY = getPlayerYCoordinate();
            player.move(x, y);
            removeTile(previousX, previousY);
        }
        tiles.put(player, TileType.PASSABLE);
    }

    private void setEnemy(int x, int y) {
        enemy = new Slime(x, y);
    }

    private void movement(int deltaX, int deltaY) {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        } else if (locationHasEnemy(attemptedLocation)) {
            doCombat(player, enemy);
        }
    }

    boolean locationHasEnemy(TileType attemptedLocation) {
        return enemy != null && isEnemyTile(attemptedLocation);
    }

    public String getGameStatus() {
        return this.gameStatus;
    }

    public void doCombat(Player player, Enemy enemy) {
        CombatObject combatObject = new CombatObject(player, enemy);
        CombatObject returnObject = combatEngine.doCombat(combatObject);
        unpackCombatObject(returnObject);
    }

    private void unpackCombatObject(CombatObject combatObject) {
        if (!ifEntityIsTheSame(player, combatObject.player)) {
            removeTile(getPlayerXCoordinate(), getPlayerYCoordinate());
            player = combatObject.player;
        }

        if (ifEntityIsTheSame(enemy, combatObject.enemy)) {
            enemy = combatObject.enemy;
        } else {
            removeTile(getEnemyXCoordinate(), getEnemyYCoordinate());
            enemy = combatObject.enemy;
            ArrayList<Integer> newCoordinates = getNewCoordinates();
            enemy.move(newCoordinates.get(0), newCoordinates.get(1));
            tiles.put(enemy, enemy.getTileType());
        }

        gameStatus = combatObject.gameStatusString;
    }

    private boolean ifEntityIsTheSame(Entity entityOriginal, Entity entityNew) {
        String originalUniqueId = entityOriginal.getUniqueId().toString();
        String newUniqueId = entityNew.getUniqueId().toString();

        return originalUniqueId.equals(newUniqueId);
    }

    boolean isEnemyTile(TileType tileType) {
        switch (tileType) {
            case KOBOLD:
            case SLIME:
            case ORC:
                return true;
            default:
                return false;
        }
    }

    ArrayList<Integer> getNewCoordinates() {
        int newXCoordinate = randomizerWrapper.getRandomNewCoordinate(getLevelHorizontalDimension());
        int newYCoordinate = randomizerWrapper.getRandomNewCoordinate(getLevelVerticalDimension());

        while (!newPointIsOpen(newXCoordinate, newYCoordinate)) {
            newXCoordinate = randomizerWrapper.getRandomNewCoordinate(getLevelHorizontalDimension());
            newYCoordinate = randomizerWrapper.getRandomNewCoordinate(getLevelVerticalDimension());
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    boolean newPointIsOpen(int x, int y) {
        TileType tileType = getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }
}
