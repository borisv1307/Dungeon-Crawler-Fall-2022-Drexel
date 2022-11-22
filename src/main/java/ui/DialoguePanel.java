package ui;

import engine.GameEngine;

import java.awt.*;

public class DialoguePanel extends Panel {
    private GameEngine gameEngine;

    public DialoguePanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        add(new Button("Choice One"));
        add(new Button("Choice Two"));
        add(new Button("Choice Three"));
    }
}
