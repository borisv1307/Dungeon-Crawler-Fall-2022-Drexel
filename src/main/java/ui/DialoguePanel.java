package ui;

import java.awt.*;
import java.util.ArrayList;

public class DialoguePanel extends Panel {
    private final DialogueButton buttonOne;
    private final DialogueButton buttonTwo;
    private final DialogueButton buttonThree;
    ArrayList<DialogueButton> buttons;
    GridBagConstraints constraints;

    public DialoguePanel() {
        setName("Dialogue Panel");

        buttons = new ArrayList<>();
        constraints = new GridBagConstraints();
        
        buttonOne = createButton(0);
        buttonOne.setPreferredSize(new Dimension(275, 150));
        buttons.add(buttonOne);
        add(buttonOne);

        buttonTwo = createButton(1);
        buttonTwo.setPreferredSize(new Dimension(275, 150));
        buttons.add(buttonTwo);
        add(buttonTwo);

        buttonThree = createButton(2);
        buttonThree.setPreferredSize(new Dimension(275, 150));
        buttons.add(buttonThree);
        add(buttonThree);
        setVisible(true);
    }

    public ArrayList<DialogueButton> getDialoguePanelButtons() {
        return buttons;
    }

    public DialogueButton getDialogueButton(int index) {
        return buttons.get(index);
    }

    DialogueButton createButton(int columnPosition) {
        DialogueButton button = new DialogueButton("default dialogue button");
        button.setName("Dialogue Button " + columnPosition);
        button.setActionCommand("Click Event");

        constraints.gridx = columnPosition;
        constraints.gridy = 0;

        return button;
    }
}
