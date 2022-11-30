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
    private int playerHP;
    private int maxPlayerHP;
    private boolean playerRegenOn;
    private int playerRegenRemaining;
    private boolean playerDrainOn;
    private int playerDrainRemaining;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        playerHP = 10;
        maxPlayerHP = 50;
        playerRegenOn = false;
        playerRegenRemaining = 0;
        playerDrainOn = false;
        playerDrainRemaining = 0;
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
        player = new Point(x, y);
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
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
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
            updateRegen();
            updateDrain();
            checkForSpecialTile(getPlayerXCoordinate(), getPlayerYCoordinate());
        }
    }

    private void updateDrain() {
        if (playerDrainOn) {
            damagePlayer(2);
            playerDrainRemaining--;
        }
        if (playerDrainRemaining == 0) {
            setDrain(false, 0);
        }
    }

    private void updateRegen() {
        if (playerRegenOn) {
            healPlayer(2);
            playerRegenRemaining--;
        }
        if (playerRegenRemaining == 0) {
            setRegen(false, 0);
        }
    }

    private void checkForSpecialTile(int playerXCoordinate, int playerYCoordinate) {
        TileType currentLocation = getTileFromCoordinates(playerXCoordinate,
                playerYCoordinate);
        switch (currentLocation) {
            case HEALING:
                healPlayer(5);
                break;
            case DAMAGE:
                damagePlayer(5);
                break;
            case TRANSIENT_HEALING:
                healPlayer(5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case TRANSIENT_DAMAGE:
                damagePlayer(5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case REGEN:
                setRegen(true, 5);
                break;
            case DRAIN:
                setDrain(true, 5);
                break;
            case TRANSIENT_REGEN:
                setRegen(true, 5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            case TRANSIENT_DRAIN:
                setDrain(true, 5);
                addTile(playerXCoordinate, playerYCoordinate, TileType.PASSABLE);
                break;
            default:
                break;
        }

    }

    private void setDrain(boolean drainStatus, int drainCounter) {
        playerDrainOn = drainStatus;
        playerDrainRemaining = drainCounter;
    }

    private void setRegen(boolean regenStatus, int regenCounter) {
        playerRegenOn = regenStatus;
        playerRegenRemaining = regenCounter;
    }

    private void healPlayer(int healAmount) {
        if (playerHP + healAmount <= maxPlayerHP) {
            playerHP += healAmount;
        } else {
            playerHP = maxPlayerHP;
        }
    }

    private void damagePlayer(int damageAmount) {
        if (playerHP - damageAmount >= 0) {
            playerHP -= damageAmount;
        } else {
            playerHP = 0;
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public boolean getPlayerRegenStatus() {
        return playerRegenOn;
    }

    public int getRegenCounter() {
        return playerRegenRemaining;
    }

    public boolean getPlayerDrainStatus() {
        return playerDrainOn;
    }

    public int getDrainCounter() {
        return playerDrainRemaining;
    }
}
