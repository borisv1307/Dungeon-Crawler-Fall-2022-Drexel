package projectile;

import engine.GameEngine;
import tiles.TileType;

public class ProjectileShooter {
    private int xDiff, yDiff;
    private GameEngine engine;

    public ProjectileShooter(int xDiff, int yDiff, GameEngine engine) {
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.engine = engine;

        engine.addTile(xDiff, yDiff, TileType.NOT_PASSABLE);
    }
}
