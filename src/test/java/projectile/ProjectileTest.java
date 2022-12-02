package projectile;

import engine.GameEngine;
import main.ObjectFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ProjectileTest {

    GameEngine gameEngine;
    int startX, startY;

    @Before
    public void init() {
        gameEngine = ObjectFactory.getDefaultGameEngine();
        startX = gameEngine.getPlayerXCoordinate();
        startY = gameEngine.getPlayerYCoordinate();
    }

    @Test
    public void add_projectile() {
        addProjectileToEngine(startX + 1, startY);
        addProjectileToEngine(startX + 1, startY);
        addProjectileToEngine(startX + 1, startY);
        Assert.assertTrue(gameEngine.getProjectiles().size() == 3);
    }

    @Test
    public void player_reset_on_projectile_move() {
        gameEngine.keyDown();
        addProjectileToEngine(startX - 1, startY);
        Mockito.timeout(200);
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_up() {
        addProjectileToEngine(startX, startY - 1);
        gameEngine.keyUp();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_down() {
        addProjectileToEngine(startX, startY + 1);
        gameEngine.keyDown();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_right() {
        addProjectileToEngine(startX + 1, startY);
        gameEngine.keyRight();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_left() {
        addProjectileToEngine(startX - 1, startY);
        gameEngine.keyLeft();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void projectile_shooter_test() {
        new LeftProjectileShooter(7, 0, 2000, 1000, gameEngine);
        Mockito.timeout(2001);
        Assert.assertTrue(gameEngine.getProjectiles().size() == 1);
    }


    private void addProjectileToEngine(int x, int y) {
        Projectile projectile = new Projectile(x, y, 1, 1, 100, gameEngine);
        gameEngine.addProjectile(projectile);
    }

    private boolean playerBackAtStart() {
        return gameEngine.getPlayerXCoordinate() == startX && gameEngine.getPlayerYCoordinate() == startY;
    }
}
