package engine;

import creatures.Creature;
import creatures.Enemy;
import creatures.Player;
import creatures.Slime;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private CombatEngine combatEngine;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        gameStatus = GameStatus.PLAYER_RESPAWNED;
    }

    public void setCombatEngine(CombatEngine combatEngine) {
        this.combatEngine = combatEngine;
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
            createFirstEnemy(x, y);
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

    public List<Integer> getPlayerSpawnPoint() {
        return Arrays.asList(player.getSpawnCoordinateX(), player.getSpawnCoordinateY());
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

    public String getGameStatus() {
        return this.gameStatus;
    }

    public void doCombat(Player player, Enemy enemy) {
        CombatObject combatObject = new CombatObject(player, enemy);
        CombatObject returnObject = combatEngine.doCombat(combatObject);
        unpackCombatObject(returnObject);
    }

    boolean locationHasEnemy(TileType attemptedLocation) {
        return enemy != null && isEnemyTile(attemptedLocation);
    }

    private void setPlayer(int x, int y) {
        if (player == null) {
            player = new Player(x, y);
            tiles.put(player, TileType.PASSABLE);
        } else {
            updatePlayersLocation(x, y);
            tiles.put(player, TileType.PLAYER);
        }

    }

    private void updatePlayersLocation(int x, int y) {
        int previousX = getPlayerXCoordinate();
        int previousY = getPlayerYCoordinate();
        player.move(x, y);
        removeTile(previousX, previousY);
    }

    private void createFirstEnemy(int x, int y) {
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

    private void unpackCombatObject(CombatObject combatObject) {
        if (ifEntityIsNew(player, combatObject.player)) {
            removeTile(getPlayerXCoordinate(), getPlayerYCoordinate());
            player = combatObject.player;
        }

        if (ifEntityIsNew(enemy, combatObject.enemy)) {
            tiles.remove(enemy);
            removeTile(getEnemyXCoordinate(), getEnemyYCoordinate());
        }

        enemy = combatObject.enemy;
        tiles.put(enemy, enemy.getTileType());
        gameStatus = combatObject.gameStatusString;
    }

    private boolean ifEntityIsNew(Creature creatureOriginal, Creature creatureNew) {
        String originalUniqueId = creatureOriginal.getUniqueId().toString();
        String newUniqueId = creatureNew.getUniqueId().toString();

        return !originalUniqueId.equals(newUniqueId);
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
}
