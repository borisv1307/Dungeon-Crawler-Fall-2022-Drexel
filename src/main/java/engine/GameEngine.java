package engine;

import entities.*;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.*;

public class GameEngine {

    private Random random = new Random();
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
        } else if (isEnemyTile(tileType)){
            setEnemy(x, y);
            tiles.put(enemy, enemy.getTileType());
        }
        else {
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

    private void setPlayer(int x, int y) {
        player = new Player(x, y);
    }

    private boolean isEnemyTile(TileType tileType){
        switch (tileType) {
            case KOBOLD:
            case SLIME:
            case ORC:
                return true;
            default:
                return false;
        }
    }
    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    private void setEnemy(int x, int y) {enemy = new Kobold(x, y);}

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

    private void movement(int deltaX, int deltaY) {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }
        else if (locationHasEnemy(attemptedLocation)) {
            int newHP = enemy.receiveDamage(player.getAttackValue());
            if (newHP <= 0 ){
                enemyKilled(getEnemyXCoordinate(), getEnemyYCoordinate());
            }
        }
    }

    private boolean locationHasEnemy(TileType attemptedLocation) {
        return enemy != null && isEnemyTile(attemptedLocation);
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

    public void enemyKilled(int x, int y) {
        ArrayList<Integer> newCoordinates = getNewCoordinates();
        removeTile(x, y);
        createNewEnemy(newCoordinates.get(0), newCoordinates.get(1));
    }

    private ArrayList<Integer> getNewCoordinates(){
        int newXCoordinate = getRandomNewCoordinate(getLevelHorizontalDimension());
        int newYCoordinate = getRandomNewCoordinate(getLevelVerticalDimension());

        while (!newPointIsValid(newXCoordinate, newYCoordinate)){
            newXCoordinate = getRandomNewCoordinate(getLevelHorizontalDimension());
            newYCoordinate = getRandomNewCoordinate(getLevelVerticalDimension());
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    private boolean newPointIsValid(int x, int y){
        TileType tileType = getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }

    private int getRandomNewCoordinate(int dimensionLimit){
        return random.nextInt(dimensionLimit);
    }

    private void createNewEnemy(int x, int y){
        enemy = getRandomEnemy(x, y);
        tiles.put(enemy, enemy.getTileType());
    }

    private Enemy getRandomEnemy(int x, int y){
        int numberOfEnemyTypes = 3;
        int randomInt = random.nextInt(numberOfEnemyTypes);

        switch (randomInt){
            case 0:
                return new Kobold(x, y);
            case 1:
                return new Slime(x, y);
            case 2:
                return new Orc(x, y);
        }

        return new Slime(x, y);
    }
}
