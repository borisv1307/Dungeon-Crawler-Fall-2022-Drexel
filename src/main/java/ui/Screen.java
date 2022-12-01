package ui;

import engine.GameEngine;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import wrappers.ThreadWrapper;

import javax.swing.*;

public abstract class Screen extends JFrame {
    private static final long serialVersionUID = 1L;
    ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
    GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
    GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
    FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();

    protected Screen() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(320, 127, 600, 400);
        setResizable(false);

    }
}
