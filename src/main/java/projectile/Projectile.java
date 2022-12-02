package projectile;

import engine.GameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Projectile {
    private int x, y, deltaX, deltaY, interval;
    private GameEngine engine;

    public Projectile(int startX, int startY, int deltaX, int deltaY, int interval, GameEngine engine) {
        this.x = startX;
        this.y = startY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.interval = interval;
        this.engine = engine;
        move(this);
    }

    private void move(Projectile selfForNotify) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int totalIterations = calculateIterations();
            int iterations = 0;

            @Override
            public void run() {
                if (iterations == totalIterations) {
                    timer.cancel();
                    return;
                }
                x += deltaX;
                y += deltaY;
                engine.notifyProjectileMovement(selfForNotify);
                iterations++;
                System.out.println("Moved.");
            }
        }, interval, interval);
    }

    private int calculateIterations() {
        int xIterations = 0;
        int yIterations = 0;
        if (deltaX > 0) {
            xIterations = engine.getLevelHorizontalDimension() - x;
        } else if (deltaX < 0) {
            xIterations = x;
        }
        if (deltaY > 0) {
            yIterations = engine.getLevelVerticalDimension() - y;
        } else if (deltaY < 0) {
            yIterations = y;
        }

        if (xIterations > yIterations) {
            return xIterations;
        }
        return yIterations;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
