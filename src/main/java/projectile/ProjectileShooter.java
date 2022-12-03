package projectile;

import engine.GameEngine;

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
        run();
    }

    private void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                engine.addProjectile(new Projectile(x + deltaX, y + deltaY, deltaX, deltaY, projectileInterval, engine));
            }
        }, 0, interval);
    }

    public void stop() {
        timer.cancel();
    }
}
