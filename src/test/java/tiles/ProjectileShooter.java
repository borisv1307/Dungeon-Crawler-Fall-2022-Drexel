package tiles;

import engine.GameEngine;

public class ProjectileShooter {
    private int xDiff, yDiff;
    private GameEngine engine;

    public ProjectileShooter(int xDiff, int yDiff, GameEngine engine) {
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.engine = engine;
    }
}
