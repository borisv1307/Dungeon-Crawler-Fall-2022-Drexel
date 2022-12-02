package projectile;

import engine.GameEngine;
import tiles.TileType;

public class ProjectileShooter {
    private int xDiff, yDiff, interval;
    private GameEngine engine;

    public ProjectileShooter(int xDiff, int yDiff, int interval, GameEngine engine) {
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.interval = interval;
        this.engine = engine;

        engine.addTile(xDiff, yDiff, TileType.NOT_PASSABLE);
    }
}
