package engine;

import entities.Enemy;
import entities.Player;
import entities.Slime;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomizerWrapper;
import wrappers.SystemWrapper;

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
    private RandomizerWrapper randomizerWrapper = new RandomizerWrapper(new SystemWrapper());

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
            setNewPlayer(x, y);
            tiles.put(player, TileType.PASSABLE);
        } else if (isEnemyTile(tileType)) {
            setNewEnemy(x, y);
            tiles.put(enemy, enemy.getTileType());
        } else {
            tiles.put(new Point(x, y), tileType);
        }
    }

    boolean newPointIsOpen(int x, int y) {
        TileType tileType = getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
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

    private void setNewPlayer(int x, int y) {
        if (player == null) {
            player = new Player(x, y);
        } else {
            player = player.copyPlayerToNewLocation(x, y);
        }
    }

    private void setNewEnemy(int x, int y) {
        enemy = new Slime(x, y);
    }

    private void movement(int deltaX, int deltaY) {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setNewPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
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

    public void setGameStatus(String string) {
        this.gameStatus = string;
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    public void setNewEnemy(Enemy enemy) {
        this.enemy = enemy;
        tiles.put(enemy, enemy.getTileType());
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setNewPlayer(Player player) {
        this.player = player;
    }

    void killPlayer(Player player) {
        int x = getPlayerXCoordinate();
        int y = getPlayerYCoordinate();
        removeTile(x, y);
        setNewPlayer(new Player(player.getOriginX(), player.getOriginY()));
        setGameStatus(GameStatus.PLAYER_DEFEATED);
    }


    public void doCombat(Player player, Enemy enemy) {
        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());
        setGameStatus(getCombatStatus(player, enemy));

        if (enemyHP <= 0) {
            killEnemy(enemy);
        }

        if (playerHP <= 0) {
            killPlayer(player);
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


    void killEnemy(Enemy enemy) {
        ArrayList<Integer> newCoordinates = getNewCoordinates();
        int x = getEnemyXCoordinate();
        int y = getEnemyYCoordinate();
        removeTile(x, y);
        setGameStatus(String.format(GameStatus.ENEMY_DEFEATED, enemy.getName()));
        createNewEnemy(newCoordinates.get(0), newCoordinates.get(1));
    }

    private void createNewEnemy(int x, int y) {
        setNewEnemy(randomizerWrapper.getRandomEnemy(x, y));
    }

    private String getCombatStatus(Player player, Enemy enemy) {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
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
}
