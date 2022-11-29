package main;

import engine.GameEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.TilePainter;
import values.TunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

import java.awt.*;

import static org.junit.Assert.*;

public class EnemyHandlerTest {
    GameEngine game;
    EnemyHandler enemyHandler;
    private final double chanceOfSpawn = .2;
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
        EnemyHandler.getEnemies().clear();
    }

    @Test
    public void enemy_is_created(){
        enemyHandler.createEnemy(10, 10, ENEMY_WIDTH, ENEMY_HEIGHT);
        EnemyHandler.Enemy enemy = EnemyHandler.getEnemies().get(0);
        assertEquals(10, enemy.getX());
        assertEquals(10, enemy.getY());
    }

    @Test
    public void enemy_is_drawn_at_right_point(){
        Graphics graphics = Mockito.mock(Graphics.class);
        TilePainter tilePainter = new TilePainter();
        game.spawnEnemy(1, 1);
        tilePainter.paintEnemies(graphics, EnemyHandler.getEnemies());
        Mockito.verify(graphics).fillRect(TILE_WIDTH, TILE_HEIGHT, 45, 60);
    }

    @Test
    public void enemies_progress_at_own_speed(){
        Graphics graphics = Mockito.mock(Graphics.class);
        TilePainter tilePainter = new TilePainter();
        EnemyHandler.Enemy enemy = game.spawnEnemy(1, 1);
        int initialX = enemy.getX();
        int initialY = enemy.getY();
        enemyHandler.progressEnemies();
        tilePainter.paintEnemies(graphics, EnemyHandler.getEnemies());
        Mockito.verify(graphics).fillRect(initialX, initialY + enemy.getSpeed(), 45, 60);


    }

    @Test
    public void enemies_spawn_randomly_on_X_axis(){
        RandomHandler randomHandler = Mockito.mock(RandomHandler.class);
        Mockito.when(randomHandler.getRandomIntInRange(1, 19)).thenReturn(6);
        EnemyHandler.Enemy enemy = game.spawnEnemyAtRandomX(randomHandler);
        assertEquals(TILE_WIDTH * 6, enemy.getX());
    }

    @Test
    public void enemies_have_chance_to_spawn_each_fps(){
        RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
        Mockito.when(randomWrapper.getRandomDouble()).thenReturn(chanceOfSpawn - .01);
        enemyHandler.setChanceOfSpawn(chanceOfSpawn);
        assertTrue(enemyHandler.enemyWillSpawn(randomWrapper));
        Mockito.when(randomWrapper.getRandomDouble()).thenReturn(chanceOfSpawn + .01);
        assertFalse(enemyHandler.enemyWillSpawn(randomWrapper));
    }


}
