package projectile;

import engine.GameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Projectile {
    private int x, y, deltaX, deltaY, interval, totalIterations, iterations;
    private GameEngine engine;
    private Timer timer;

    public Projectile(int startX, int startY, int deltaX, int deltaY, int interval, GameEngine engine) {
        this.x = startX;
        this.y = startY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.interval = interval;
        this.iterations = 0;
        this.engine = engine;
        this.totalIterations = calculateIterations();
        this.timer = new Timer();
        start(this);
    }

    private void start(Projectile selfForNotify) {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (iterations == totalIterations) {
                    stop();
                    return;
                }
                x += deltaX;
                y += deltaY;
                engine.notifyProjectileMovement(selfForNotify);
                iterations++;
            }
        }, interval, interval);
    }

    private int calculateIterations() {
        int xIterations = 0;
        int yIterations = 0;
        if (deltaX > 0) {
            xIterations = engine.getLevelHorizontalDimension() - x - 1;
        } else if (deltaX < 0) {
            xIterations = x;
        }
        if (deltaY > 0) {
            yIterations = engine.getLevelVerticalDimension() - y - 1;
        } else if (deltaY < 0) {
            yIterations = y;
        }

        if (xIterations >= 0 && yIterations == 0) {
            return xIterations;
        }
        return yIterations;
    }

    public void stop() {
        timer.cancel();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
