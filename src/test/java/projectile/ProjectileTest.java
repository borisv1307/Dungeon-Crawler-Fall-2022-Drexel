package projectile;

import engine.GameEngine;
import main.ObjectFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectileTest {

    GameEngine engine;

    @Before
    public void init() {
        engine = ObjectFactory.getDefaultGameEngine();
    }
    
    @Test
    public void player_collision() {
        Projectile projectile = new Projectile(10, 7, 1, 1);
        Assert.assertTrue(projectile.playerCollision(engine.getPlayerXCoordinate(), engine.getPlayerYCoordinate()));
    }
}
