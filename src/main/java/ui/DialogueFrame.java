package ui;

import engine.GameEngine;
import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogueFrame extends Frame {
    private final ArrayList<DialogueButton> buttons;
    private final transient List<Dialogue> dialogues;
    private transient List<Response> currentDialogueResponses;
    private transient Dialogue currentDialogue;
    private final JTextArea dialogueTextArea;
    private final GridBagConstraints constraints;
    private final DialoguePanel dialoguePanel;
    private final GameEngine gameEngine;
    private transient final DialogueSystem dialogueSystem;

    public DialogueFrame(DialoguePanel dialoguePanel, GameEngine gameEngine) {
        this.dialoguePanel = dialoguePanel;
        this.gameEngine = gameEngine;

        buttons = dialoguePanel.getDialoguePanelButtons();

        dialogueSystem = new DialogueSystem();
        dialogues = dialogueSystem.getDialogues();

        currentDialogue = dialogues.get(0);
        currentDialogueResponses = currentDialogue.getResponses();

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);

        setResizable(false);
        setLayout(new GridBagLayout());

        dialoguePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
        dialoguePanel.setBackground(Color.GRAY);
        dialoguePanel.setVisible(true);

        addButtonListenerToButtons();

        dialogueTextArea = createJTextArea(constraints);

        dialoguePanel.add(dialogueTextArea, constraints);

        add(dialoguePanel);

        addWindowListener(new WindowAdapterDialogueFrameExit(gameEngine, this));

        setVisible(true);
        pack();
    }

    public Dialogue getCurrentDialogue() {
        return currentDialogue;
    }

    public JTextArea getDialogueTextArea() {
        return dialogueTextArea;
    }

    public void updateDialogueFrame(int nextDialogueID) {
        resetButtons();
        setCurrentDialogueToTargetDialogue(nextDialogueID);
        updateJTextFieldContent(nextDialogueID);
        updateButtonsContent();
    }

    public void updateJTextFieldContent(int targetDialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                dialogueTextArea.setText(dialogue.getDialogueContent());
            }
        }
    }
    
    public int readPlayerResponseToFindNextDialogueID(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
    }

    private JTextArea createJTextArea(GridBagConstraints constraints) {
        JTextArea jtextArea = new JTextArea();
        jtextArea.setText("default text area");
        jtextArea.setEditable(false);
        Font displayFont = new Font("Text Area Font", Font.ITALIC, 16);
        jtextArea.setFont(displayFont);
        jtextArea.setPreferredSize(new Dimension(400, 600));
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        return jtextArea;
    }

    private void addButtonListenerToButtons() {
        for (JButton button : buttons) {
            button.addActionListener(new ButtonClickActionListener(this));
        }
    }

    private void resetButtons() {
        int index = 0;
        for (DialogueButton ignored : buttons) {
            DialogueButton button = buttons.get(index);
            button.setVisible(false);
            index++;
        }
    }

    private void setCurrentDialogueToTargetDialogue(int dialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == dialogueID) {
                currentDialogue = dialogue;
            }
        }
    }

    private void updateButtonsContent() {
        currentDialogueResponses = currentDialogue.getResponses();
        for (int responseIndex = 0; responseIndex < currentDialogueResponses.size(); responseIndex++) {
            DialogueButton currentButton = buttons.get(responseIndex);
            Response currentResponse = currentDialogueResponses.get(responseIndex);
            currentButton.setText(currentResponse.getResponseText());
            currentButton.setVisible(true);
        }
    }
}
