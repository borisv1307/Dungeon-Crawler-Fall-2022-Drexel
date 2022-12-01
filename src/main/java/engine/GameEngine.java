package engine;

import parser.LevelCreator;
import player.Player;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private Player player;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;

    public GameEngine(LevelCreator levelCreator, Player player) {
        exit = false;
        level = 1;
        this.player = player;
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
        player.setPoint(x, y);
    }

    public int getPlayerXCoordinate() {
        return player.getX();
    }

    public int getPlayerYCoordinate() {
        return player.getY();
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
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX,
                getPlayerYCoordinate() + deltaY);
        if (!(attemptedLocation.equals(TileType.NOT_PASSABLE))) {
            player.move(deltaX, deltaY);
            checkForSpecialTile(getPlayerXCoordinate(), getPlayerYCoordinate());
        }
    }


    private void checkForSpecialTile(int playerXCoordinate, int playerYCoordinate) {
        TileType currentLocation = getTileFromCoordinates(playerXCoordinate,
                playerYCoordinate);
        switch (currentLocation) {
            case HEALING:
                player.changeHealth(5);
                break;
            case DAMAGE:
                player.changeHealth(-5);
                break;
            case TRANSIENT_HEALING:
                player.changeHealth(5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case TRANSIENT_DAMAGE:
                player.changeHealth(-5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case REGEN:
                player.setRegen(true, 5);
                break;
            case DRAIN:
                player.setDrain(true, 5);
                break;
            case TRANSIENT_REGEN:
                player.setRegen(true, 5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case TRANSIENT_DRAIN:
                player.setDrain(true, 5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            default:
                break;
        }

    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getPlayerHP() {
        return player.getHP();
    }

    public boolean getPlayerRegenStatus() {
        return player.isRegenOn();
    }

    public int getPlayerRegenCounter() {
        return player.getRegenRemaining();
    }

    public boolean getPlayerDrainStatus() {
        return player.isDrainOn();
    }

    public int getPlayerDrainCounter() {
        return player.getDrainRemaining();
    }
}
