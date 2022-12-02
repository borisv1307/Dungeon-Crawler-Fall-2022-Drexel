package projectile;

import engine.GameEngine;
import tiles.TileType;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ProjectileShooter {
    private int x, y, deltaX, deltaY, interval, projectileInterval;
    private GameEngine engine;
    private Timer timer;

    public ProjectileShooter(int x, int y, int deltaX, int deltaY, int interval, int projectileInterval, GameEngine engine) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.interval = interval;
        this.projectileInterval = projectileInterval;
        this.engine = engine;
        this.timer = new Timer();
        engine.addTile(x, y, TileType.NOT_PASSABLE);
        run();
    }

    private void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                engine.addProjectile(new Projectile(x, y, deltaX, deltaY, projectileInterval, engine));
            }
        }, 0, interval);
    }

    public void stop() {
        timer.cancel();
    }
}
