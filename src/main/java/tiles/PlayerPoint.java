package tiles;

import java.awt.*;

public class PlayerPoint extends Point {
    private int hitpoints;

    public PlayerPoint(int x, int y, int hitpoints) {
        super(x, y);
        this.hitpoints = hitpoints;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void damage() {
        hitpoints--;
        if (hitpoints < 0) {
            hitpoints = 0;
        }
    }

    public void heal() {
        hitpoints++;
        if (hitpoints > 10) {
            hitpoints = 10;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerPoint) {
            PlayerPoint player = (PlayerPoint) obj;
            return (this.x == player.x && this.y == player.y && this.hitpoints == player.hitpoints);
        }
        return false;
    }
}
