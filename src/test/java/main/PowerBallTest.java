package main;

import engine.GameEngine;
import org.junit.Test;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class PowerBallTest {
    @Test
    public void the_player_generates_powerball(){
        GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
        gameEngine.keyZ();
    }
}
