package player;

import java.awt.*;

public class Player {
    private int health;
    private int maxHealth;
    private boolean regenOn;
    private int regenRemaining;
    private boolean drainOn;
    private int drainRemaining;
    private Point playerPoint;

    public Player() {
        health = 10;
        maxHealth = 50;
        regenOn = false;
        regenRemaining = 0;
        drainOn = false;
        drainRemaining = 0;
        playerPoint = new Point(0, 0);
    }

    public void move(int deltaX, int deltaY) {
        setPoint(getX() + deltaX, getY() + deltaY);
        updateRegen();
        updateDrain();
    }

    public void setPoint(int x, int y) {
        playerPoint = new Point(x, y);
    }

    public int getY() {
        return (int) playerPoint.getY();
    }

    public int getX() {
        return (int) playerPoint.getX();
    }

    public boolean isRegenOn() {
        return regenOn;
    }

    private void updateDrain() {
        if (drainOn) {
            changeHealth(-2);
            drainRemaining--;
        }
        if (drainRemaining == 0) {
            setDrain(false, 0);
        }
    }

    private void updateRegen() {
        if (regenOn) {
            changeHealth(2);
            regenRemaining--;
        }
        if (regenRemaining == 0) {
            setRegen(false, 0);
        }
    }

    public void changeHealth(int deltaHealth) {
        int targetHealth = health + deltaHealth;
        if (targetHealth >= 0 && targetHealth <= maxHealth) {
            health = targetHealth;
        } else if (targetHealth < 0) {
            health = 0;
        } else {
            health = maxHealth;
        }
    }

    public void setDrain(boolean drainStatus, int drainCounter) {
        drainOn = drainStatus;
        drainRemaining = drainCounter;
    }

    public void setRegen(boolean regenStatus, int regenCounter) {
        regenOn = regenStatus;
        regenRemaining = regenCounter;
    }

    public int getRegenRemaining() {
        return regenRemaining;
    }


    public boolean isDrainOn() {
        return drainOn;
    }

    public int getDrainRemaining() {
        return drainRemaining;
    }
}
