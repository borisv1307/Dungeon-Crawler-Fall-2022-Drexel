package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;

public class InputListenerTest {
    InputListener inputListener;
    GameEngine gameEngine;

    @Before
    public void setUp() {
        gameEngine = Mockito.mock(GameEngine.class);
        inputListener = new InputListener(gameEngine);
    }

    @Test
    public void key_left() {
        inputListener.performAction(KeyEvent.VK_LEFT);
        Mockito.verify(gameEngine, Mockito.times(1)).keyLeft();
    }

    @Test
    public void key_right() {
        inputListener.performAction(KeyEvent.VK_RIGHT);
        Mockito.verify(gameEngine, Mockito.times(1)).keyRight();
    }

    @Test
    public void key_up() {
        inputListener.performAction(KeyEvent.VK_UP);
        Mockito.verify(gameEngine, Mockito.times(1)).keyUp();
    }

    @Test
    public void key_space() {
        inputListener.performAction(KeyEvent.VK_SPACE);
        Mockito.verify(gameEngine, Mockito.times(1)).keySpace();
    }

}
