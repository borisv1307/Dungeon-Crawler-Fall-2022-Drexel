package ui;

import java.awt.*;
import java.util.ArrayList;

public class DialoguePanel extends Panel {
    private final DialogueButton buttonOne;
    private final DialogueButton buttonTwo;
    private final DialogueButton buttonThree;
    ArrayList<DialogueButton> buttons;

    public DialoguePanel() {
        buttons = new ArrayList<>();

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

    DialogueButton createButton(int columnPosition) {
        GridBagConstraints constraints = new GridBagConstraints();
        DialogueButton button = new DialogueButton("default dialogue button");
        
        constraints.gridx = columnPosition;
        constraints.gridy = 0;

        return button;
    }
}
