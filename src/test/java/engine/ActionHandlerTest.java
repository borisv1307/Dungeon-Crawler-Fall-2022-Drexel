package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;
    @Before
    public void setUp(){
        gameEngine = ObjectFactory.getDefaultGameEngine();
        actionHandler = new ActionHandler(gameEngine);
    }

    @Test
    public void tile_is_next_level(){
        boolean actual = actionHandler.tileIsNextLevel(-1,-8);
        assertThat(actual, equalTo(true));
    }

}
