package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private final int levelenemycount;
    private final List<Point> enemies = new ArrayList<>();
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        levelenemycount = 3;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        setEnemyCount(levelenemycount);
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

    private void resetGame() {
        tiles.clear();
        exit = false;
        this.levelCreator.createLevel(this, level);
        setEnemyCount(levelenemycount);
    }

    public boolean is_meet_enemy(int x, int y) {
        boolean meet = false;

        for (Point pt : enemies) {
            if (pt.x == x && pt.y == y) {
                meet = true;
                break;
            }
        }

        return meet;
    }

    private void movement(int deltaX, int deltaY) {

        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }

        if (is_meet_enemy(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY)) {
            int reply = JOptionPane.showConfirmDialog(null, "You are captured to enemy!\nDo you want to play again?", "Dungeon-Crawler-Fall-2022", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                resetGame();
                return;
            } else {
                setExit(true);
                System.exit(0);
            }
        }

        setRandomEnemyPosition();
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean setEnemyCount(int count) {

        boolean created = false;

        int width = getLevelHorizontalDimension();
        int height = getLevelVerticalDimension();

        int enemycount = 0;
        int x;
        int y;
        TileType type;

        enemies.clear();

        for (int i = 0; i < count; i++) {
            while (!created) {
                x = (int) (Math.random() * width);
                y = (int) (Math.random() * height);
                type = tiles.get(new Point(x, y));

                if (type == TileType.PASSABLE && !((x == 0 || x == 1) && (y == 0 || y == 1))) {
                    Point pt = new Point(x, y);
                    tiles.put(pt, TileType.ENEMY);
                    created = true;
                    enemies.add(pt);
                    enemycount++;
                }
            }

            created = false;
        }

        return enemycount > 0;
    }

    public int setRandomEnemyPosition() {
        int enemy_count = enemies.size();
        int xmove = 0;
        int ymove = 0;
        int width = getLevelHorizontalDimension();
        int height = getLevelVerticalDimension();

        boolean enemy_moved = false;

        for (int i = 0; i < enemy_count; i++) {
            while (!enemy_moved) {
                int odd = (int) Math.round(Math.random());
                if (odd == 1) {
                    odd = (int) Math.round(Math.random());
                    xmove = (odd == 1 ? -1 : 1);
                } else {
                    odd = (int) Math.round(Math.random());
                    ymove = (odd == 1 ? -1 : 1);
                }

                Point pt = enemies.get(i);
                TileType type = tiles.get(new Point(pt.x + xmove, pt.y + ymove));

                if ((pt.x + xmove >= 0 && pt.x + xmove < width) && (pt.y + ymove >= 0 && pt.y + ymove < height) && (type == TileType.PASSABLE || type == TileType.PLAYER)) {

                    tiles.put(new Point(pt.x, pt.y), TileType.PASSABLE);

                    pt.x += xmove;
                    pt.y += ymove;
                    tiles.put(new Point(pt.x, pt.y), TileType.ENEMY);
                    enemies.set(i, pt);
                    xmove = 0;
                    ymove = 0;
                    enemy_moved = true;
                } else {
                    xmove = 0;
                    ymove = 0;
                }
            }

            enemy_moved = false;
        }

        return 0;
    }

    public void setEnemyPos(int x, int y) {
        int enemy_count = enemies.size();
        Point pt = new Point(x, y);
        if (enemy_count == 0) {
            enemies.add(pt);
            tiles.put(pt, TileType.ENEMY);
            return;
        }

        enemies.set(0, pt);
        tiles.put(pt, TileType.ENEMY);
    }
}
