package engine;

import main.ObjectFactory;
import org.junit.Before;

public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;
    @Before
    public void setUp(){
        gameEngine = ObjectFactory.getDefaultGameEngine();
        actionHandler = new ActionHandler(gameEngine);
    }

}
