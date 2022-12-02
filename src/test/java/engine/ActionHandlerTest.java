package engine;

import entity.Player;
import org.junit.Before;
import org.junit.Test;
import parser.LevelCreator;

import values.TunableParameters;
import wrappers.ReaderWrapper;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;

    @Before
    public void setUp() {
        LevelCreator levelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
        gameEngine = new GameEngine(levelCreator);
        actionHandler = new ActionHandler(gameEngine, levelCreator, new Player());
    }

    @Test
    public void tile_is_passable() {
        boolean actual = actionHandler.tileIsPassable(1, 0);
        assertEquals(true, actual);

    }

    @Test
    public void tile_is_not_passable() {
        boolean actual = actionHandler.tileIsPassable(0, 1);
        assertEquals(false, actual);
    }

    @Test
    public void tile_is_next_level() {
        boolean actual = actionHandler.tileIsNextLevel(-1, -8);
        assertEquals(true, actual);
    }

    @Test
    public void tile_is_not_next_level() {
        boolean actual = actionHandler.tileIsNextLevel(-1, -1);
        assertEquals(false, actual);
    }

    @Test
    public void current_level_one_is_not_next_to_enemy(){
        assertFalse(actionHandler.playerIsNextToEnemy());
    }

}