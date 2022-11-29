package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static values.TunableParameters.*;

public class DialoguePanel extends Panel {
    private final DialogueButton buttonOne;
    private final DialogueButton buttonTwo;
    private final DialogueButton buttonThree;
    private final List<DialogueButton> buttons;
    private final JTextArea dialogueTextArea;
    private final Font responseButtonFont;
    private final Font dialogueFont;

    public DialoguePanel() {
        setName("Dialogue Panel");

        dialogueFont = new Font("Text Area Font", DIALOGUE_FONT_STYLE, DIALOGUE_FONT_SIZE);
        responseButtonFont = new Font("Response Font", RESPONSE_FONT_STYLE, RESPONSE_FONT_SIZE);

        buttons = new ArrayList<>();
        
        setLayout(null);

        buttonOne = createButton(0);
        buttons.add(buttonOne);

        buttonTwo = createButton(1);
        buttons.add(buttonTwo);

        buttonThree = createButton(2);
        buttons.add(buttonThree);

        applyButtonSettings();

        add(buttonOne);
        add(buttonTwo);
        add(buttonThree);

        dialogueTextArea = createJTextArea();
        dialogueTextArea.setBounds(50, 200, DIALOGUE_TEXT_AREA_WIDTH, DIALOGUE_TEXT_AREA_HEIGHT);

        add(dialogueTextArea);
        setVisible(true);
        repaint();
    }

    public List<DialogueButton> getDialoguePanelButtons() {
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

    JTextArea createJTextArea() {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("default text area");
        jTextArea.setEditable(false);
        jTextArea.setFont(dialogueFont);
        jTextArea.setLineWrap(true);

        jTextArea.setBounds(0, 200, DIALOGUE_TEXT_AREA_WIDTH, DIALOGUE_TEXT_AREA_HEIGHT);

        return jTextArea;
    }

    private void applyButtonSettings() {
        int xPosition = 0;
        for (DialogueButton dialogueButton : buttons) {
            dialogueButton.setFont(responseButtonFont);
            dialogueButton.setBounds(xPosition, 10, DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT);
            xPosition += 300;
        }
    }
}
