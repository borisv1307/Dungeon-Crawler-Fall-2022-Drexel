package main;

import engine.GameEngine;
import org.junit.Test;
import tiles.ProjectileShooter;

public class ProjectileTest {

    GameEngine engine;

    @Test
    void shoot_left() {
        ProjectileShooter shooter = new ProjectileShooter(-1, 0, engine);
    }

    @Test
    void shoot_right() {
        ProjectileShooter shooter = new ProjectileShooter(1, 0, engine);
    }

    @Test
    void shoot_up() {
        ProjectileShooter shooter = new ProjectileShooter(0, 1, engine);
    }

    @Test
    void shoot_down() {
        ProjectileShooter shooter = new ProjectileShooter(0, -1, engine);
    }

}
