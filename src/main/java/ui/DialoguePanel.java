package ui;

import engine.GameEngine;

import java.awt.*;

public class DialoguePanel extends Panel {
    private final GameEngine gameEngine;
    private final Button buttonOne;
    private final Button buttonTwo;
    private final Button buttonThree;

    private Component[] buttons;

    public DialoguePanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //ToDo refactor this into a helper method.
        buttonOne = new Button("Choice One");
        buttonOne.setBounds(100, 100, 80, 30);
        this.add(buttonOne);

        buttonTwo = new Button("Choice Two");
        buttonTwo.setBounds(150, 100, 80, 30);
        this.add(buttonTwo);

        buttonThree = new Button("Choice Three");
        buttonThree.setBounds(200, 100, 80, 30);
        this.add(buttonThree);
    }

    public Component[] getButtons() {
        buttons = this.getComponents();
        return buttons;
    }
}

