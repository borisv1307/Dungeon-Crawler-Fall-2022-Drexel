package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;

    private Point powerball;

    private boolean hasPowerBall = false;

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
            tiles.put(new Point(x, y), TileType.PASSABLE);
        } else if(tileType.equals(TileType.POWERBALL)){
            setPowerball(x, y);
            tiles.put(new Point(x, y), TileType.POWERBALL);
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

    private void setPlayer(int x, int y) {
        player = new Point(x, y);
    }

    private void setPowerball(int x, int y){
        powerball = new Point(x,y);
        hasPowerBall = true;
    }



    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public void keyLeft() {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() - 1, getPlayerYCoordinate());
        if(attemptedLocation.equals(TileType.PASSABLE)){
            setPlayer(getPlayerXCoordinate() - 1, getPlayerYCoordinate());
        }

    }

    public void keyRight() {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + 1, getPlayerYCoordinate());
        if(attemptedLocation.equals(TileType.PASSABLE)){
            setPlayer(getPlayerXCoordinate() + 1, getPlayerYCoordinate());
        }
    }

    public void keyUp() {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() , getPlayerYCoordinate() - 1);
        if(attemptedLocation.equals(TileType.PASSABLE)){
            setPlayer(getPlayerXCoordinate() , getPlayerYCoordinate() - 1);
        }
    }

    public void keyDown() {
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() , getPlayerYCoordinate() + 1);
        if(attemptedLocation.equals(TileType.PASSABLE)){
            setPlayer(getPlayerXCoordinate() , getPlayerYCoordinate() + 1);
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }


    public int getPowerBallXCoordinate() {
        return (int) powerball.getX();
    }

    public int getPowerBallYCoordinate() {
        return (int) powerball.getY();
    }

    public void keyZ() {
        setPowerball(getPlayerXCoordinate(), getPlayerYCoordinate());
    }

    public boolean hasPowerBall() {
        return hasPowerBall;
    }

    public void keyX() {

        TileType attemptedLocation;

        attemptedLocation = getTileFromCoordinates(getPowerBallXCoordinate() , getPowerBallYCoordinate());

        setPowerball(getPowerBallXCoordinate(), getPowerBallYCoordinate()-1);
        //}while(!attemptedLocation.equals(TileType.PASSABLE));
    }
}
