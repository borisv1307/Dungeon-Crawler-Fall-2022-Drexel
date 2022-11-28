package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static values.TunableParameters.*;

public class DialoguePanel extends Panel {
    private final DialogueButton buttonOne;
    private final DialogueButton buttonTwo;
    private final DialogueButton buttonThree;
    private final ArrayList<DialogueButton> buttons;
    private final GridBagConstraints constraints;
    private final JTextArea dialogueTextArea;

    private final Font dialogueFont;

    public DialoguePanel() {
        setName("Dialogue Panel");

        dialogueFont = new Font("Text Area Font", Font.ITALIC, 16);

        buttons = new ArrayList<>();

        constraints = new GridBagConstraints();

        buttonOne = createButton(0);
        buttonOne.setPreferredSize(new Dimension(DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT));
        buttons.add(buttonOne);
        add(buttonOne);

        buttonTwo = createButton(1);
        buttonTwo.setPreferredSize(new Dimension(DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT));
        buttons.add(buttonTwo);
        add(buttonTwo);

        buttonThree = createButton(2);
        buttonThree.setPreferredSize(new Dimension(DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT));
        buttons.add(buttonThree);
        add(buttonThree);

        dialogueTextArea = createJTextArea(constraints);
        add(dialogueTextArea, constraints);
        setVisible(true);
    }

    public ArrayList<DialogueButton> getDialoguePanelButtons() {
        return buttons;
    }

    public JTextArea getDialogueTextArea() {
        return dialogueTextArea;
    }

    DialogueButton createButton(int columnPosition) {
        DialogueButton button = new DialogueButton();
        button.setName("Dialogue Button " + columnPosition);
        return button;
    }

    JTextArea createJTextArea(GridBagConstraints constraints) {
        JTextArea jtextArea = new JTextArea();
        jtextArea.setText("default text area");
        jtextArea.setEditable(false);
        jtextArea.setFont(dialogueFont);

        jtextArea.setPreferredSize(new Dimension(DIALOGUE_TEXT_AREA_WIDTH, DIALOGUE_TEXT_AREA_HEIGHT));
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        return jtextArea;
    }
}
