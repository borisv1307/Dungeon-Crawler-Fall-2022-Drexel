package main;

import engine.GameEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.TilePainter;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class EnemyHandlerTest {
    GameEngine game;
    EnemyHandler enemyHandler;
    private static final int ENEMY_WIDTH = 10;
    private static final int ENEMY_HEIGHT = 20;
    private static final int TILE_WIDTH = TunableParameters.SCREEN_WIDTH / 20;
    private static final int TILE_HEIGHT = TunableParameters.SCREEN_HEIGHT / 10;


    @Before
    public void setup(){
        game = new GameEngine(
                new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
        enemyHandler = game.enemyHandler;
    }

    @After
    public void teardown(){
        EnemyHandler.enemies.clear();
    }

    @Test
    public void enemy_is_created(){
        enemyHandler.createEnemy(10, 10, ENEMY_WIDTH, ENEMY_HEIGHT);
        EnemyHandler.Enemy enemy = EnemyHandler.enemies.get(0);
        assertEquals(enemy.getX(), 10);
        assertEquals(enemy.getY(), 10);
    }

    @Test
    public void enemy_is_drawn_at_right_point(){
        Graphics graphics = Mockito.mock(Graphics.class);
        TilePainter tilePainter = new TilePainter();
        game.spawnEnemy(1, 1);
        tilePainter.paintEnemies(graphics, EnemyHandler.enemies);
        Mockito.verify(graphics).fillRect(TILE_WIDTH, TILE_HEIGHT, 45, 60);
    }

    @Test
    public void enemies_spawn_randomly(){

    }


}
