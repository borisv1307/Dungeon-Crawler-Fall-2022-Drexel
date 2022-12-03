package engine;

import parser.LevelCreator;
import projectile.Projectile;
import projectile.ProjectileShooter;
import projectile.ProjectileShooterFactory;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.ArrayList;
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
    private int startX, startY;
    private Point player;
    private List<Projectile> projectiles;
    private List<ProjectileShooter> shooters;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.projectiles = new ArrayList<>();
        this.shooters = new ArrayList<>();
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
            startX = x;
            startY = y;
            tiles.put(new Point(x, y), TileType.PASSABLE);
        } else if (TileType.isShooter(tileType)) {
            ProjectileShooter shooter = ProjectileShooterFactory.getInstance().getProjectileShooter(x, y, 1500, 500, this, tileType);
            shooters.add(shooter);
            tiles.put(new Point(x, y), tileType);
        } else {
            tiles.put(new Point(x, y), tileType);
        }
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
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
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
            checkCollision();
        }
    }

    private void checkCollision() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectileCollision(projectiles.get(i))) {
                resetPlayer();
            }
        }
    }

    public void notifyProjectileMovement(Projectile projectile) {
        if (projectileCollision(projectile)) {
            resetPlayer();
        } else if (getTileFromCoordinates(projectile.getX(), projectile.getY()).equals(TileType.PASSABLE) == false) {
            projectile.stop();
            projectiles.remove(projectile);
        }
    }

    private boolean projectileCollision(Projectile projectile) {
        return projectile.getX() == getPlayerXCoordinate() && projectile.getY() == getPlayerYCoordinate();
    }

    public void resetPlayer() {
        setPlayer(startX, startY);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

}
