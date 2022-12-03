package engine;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import main.EnemyHandler;
import main.LaserHandler;
import main.RandomHandler;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class GameEngine {

    public final LaserHandler laserHandler;
    public final EnemyHandler enemyHandler;
    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    public boolean playerHasLost = false;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private int tileWidth;
    private int tileHeight;
    private RandomHandler randomHandler;
    private RandomWrapper randomWrapper = new RandomWrapper();


    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        this.laserHandler = new LaserHandler();
        this.enemyHandler = new EnemyHandler();
        this.randomHandler = new RandomHandler(randomWrapper);
    }

    public void run(GameFrame gameFrame) {
        boolean laserExists = !laserHandler.getLasers().isEmpty();
        boolean enemyExists = !EnemyHandler.getEnemies().isEmpty();

        if (!playerHasLost) {
            if (enemyExists && laserExists) {
                destroyHitEnemies();
            }
            if (laserExists) {
                laserHandler.progressLasers();
            }
            if (enemyHandler.enemyWillSpawn(randomWrapper)) {
                spawnEnemyAtRandomX(randomHandler);
            }
            if (enemyExists) {
                enemyHandler.progressEnemies();
                hasPlayerLost();
            }
        }
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
        this.tileWidth = TunableParameters.SCREEN_WIDTH / levelHorizontalDimension;
    }

    public int getLevelVerticalDimension() {
        return levelVerticalDimension;
    }

    public void setLevelVerticalDimension(int levelVerticalDimension) {
        this.levelVerticalDimension = levelVerticalDimension;
        this.tileHeight = TunableParameters.SCREEN_HEIGHT / levelVerticalDimension;
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
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void keySpace() {
        laserHandler.laserFactory(getPlayerXCoordinate() * TunableParameters.TILE_TO_LASER_WIDTH,
                getPlayerYCoordinate() * TunableParameters.TILE_TO_LASER_HEIGHT);
    }

    public EnemyHandler.Enemy spawnEnemy(int x, int y) {
        return enemyHandler.createEnemy(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    public EnemyHandler.Enemy spawnEnemyAtRandomX(RandomHandler randomHandler) {
        int randomNum = randomHandler.getRandomIntInRange(1, levelHorizontalDimension - 1);
        return spawnEnemy(randomNum, 1);
    }

    public List<LaserHandler.Laser> getLasers() {
        return laserHandler.getLasers();
    }

    public void destroyHitEnemies() {
        List<LaserHandler.Laser> lasers = laserHandler.getLasers();
        List<EnemyHandler.Enemy> enemies = EnemyHandler.getEnemies();
        for (int i = 0; i < enemies.size(); i++) {
            int xBlockEnemy = enemies.get(i).getX() / tileWidth;
            int yPixelEnemyTop = enemies.get(i).getY();
            int yPixelEnemyBottom = yPixelEnemyTop + enemies.get(i).getHeight();
            for (int j = 0; j < lasers.size(); j++) {
                int xBlockLaser = lasers.get(j).getX() / TunableParameters.TILE_TO_LASER_WIDTH;
                if (xBlockLaser == xBlockEnemy) {
                    int yPixelLaser = (lasers.get(j).getY() / TunableParameters.TILE_TO_LASER_HEIGHT) * tileHeight;
                    if (yPixelEnemyBottom >= yPixelLaser && yPixelEnemyTop <= yPixelLaser) {
                        laserHandler.destroy(lasers.get(j));
                        enemyHandler.destroy(enemies.get(i));
                        EnemyHandler.incrementKillCount();
                        break;
                    }
                }
            }
        }
    }

    public void hasPlayerLost() {
        List<EnemyHandler.Enemy> enemies = EnemyHandler.getEnemies();
        for (EnemyHandler.Enemy enemy : enemies) {
            int xBlockEnemy = enemy.getX() / tileWidth;
            int yPixelEnemyTop = enemy.getY();
            int yPixelEnemyBottom = yPixelEnemyTop + enemy.getHeight();
            if (getPlayerXCoordinate() == xBlockEnemy) {
                int yPixelPlayerTop = getPlayerYCoordinate() * tileHeight;
                int yPixelPlayerBottom = yPixelPlayerTop + tileHeight;
                if (yPixelEnemyBottom >= yPixelPlayerTop && yPixelEnemyBottom <= yPixelPlayerBottom) {
                    playerHasLost = true;
                } else if (yPixelEnemyTop <= yPixelPlayerBottom && yPixelEnemyTop >= yPixelPlayerTop) {
                    playerHasLost = true;
                }
            }
        }
    }
}
