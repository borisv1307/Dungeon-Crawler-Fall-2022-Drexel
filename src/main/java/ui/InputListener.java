package ui;

import engine.GameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {

    private GameEngine gameEngine;

    public InputListener(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        performAction(key);

    }

    @Override
    public void keyReleased(KeyEvent event) {
        throw new UnsupportedOperationException();

    }

    void performAction(int key) {
        switch (key) {
            case KeyEvent.VK_UP:
                gameEngine.keyUp();
                break;
            case KeyEvent.VK_DOWN:
                gameEngine.keyDown();
                break;
            case KeyEvent.VK_LEFT:
                gameEngine.keyLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameEngine.keyRight();
                break;
            case KeyEvent.VK_SPACE:
                gameEngine.keySpace();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

}
