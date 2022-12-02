package projectile;

import engine.GameEngine;

public class RightProjectileShooter extends ProjectileShooter {
    public RightProjectileShooter(int x, int y, int interval, int projectileInterval, GameEngine engine) {
        super(x, y, 1, 0, interval, projectileInterval, engine);
    }
}
