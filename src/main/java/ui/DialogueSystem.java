package ui;

import wrappers.XMLParserWrapper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogueSystem {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    private Dialogue currentDialogue;
    private List<Response> currentDialogueResponses;
    private Component[] components;

    public DialogueSystem() {
        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();
        currentDialogue = dialogues.get(0);
        currentDialogueResponses = currentDialogue.getResponses();
    }

    public DialogueFrame launchDialogueFrame() {
        DialogueFrame dialogueFrame = new DialogueFrame();
        addButtonListenersToButtons(dialogueFrame);
        updateEntireFrame(1, dialogueFrame);
        dialogueFrame.setVisible(true);
        return dialogueFrame;
    }

    public Dialogue getCurrentDialogue() {
        return this.currentDialogue;
    }

    public void updateEntireFrame(int nextDialogueID, DialogueFrame dialogueFrame) {
        resetButtons(dialogueFrame);
        setCurrentDialogue(nextDialogueID);
        updateJTextFieldContent(nextDialogueID, dialogueFrame);
        updateButtonsContent(dialogueFrame);
    }

    void updateJTextFieldContent(int targetDialogueID, DialogueFrame dialogueFrame) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                dialogueFrame.getJTextField().setText(dialogue.getDialogueContent());
            }
        }
    }

    private void resetButtons(DialogueFrame dialogueFrame) {
        components = dialogueFrame.getComponents();
        for (int index = 0; index < components.length - 1; index++) {
            JButton button = (JButton) components[index];
            button.setVisible(false);
        }
    }

    private void setCurrentDialogue(int dialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == dialogueID) {
                currentDialogue = dialogue;
            }
        }
    }

    private void addButtonListenersToButtons(DialogueFrame dialogueFrame) {
        components = dialogueFrame.getComponents();
        for (int index = 0; index < components.length - 1; index++) {
            JButton button = (JButton) components[index];
            button.setActionCommand("Click Event");
            button.addActionListener(e -> {
                JButton buttonClicked = (JButton) e.getSource();
                int nextDialogueID = readResponseToFindNextDialogue(buttonClicked.getText());
                if (nextDialogueID == -1) {
                    dialogueFrame.dispose();
                }
                updateEntireFrame(nextDialogueID, dialogueFrame);
            });
        }
    }

    private void updateButtonsContent(DialogueFrame dialogueFrame) {
        currentDialogueResponses = currentDialogue.getResponses();
        Component[] components = dialogueFrame.getComponents();

        for (int responseIndex = 0; responseIndex < currentDialogueResponses.size(); responseIndex++) {
            JButton currentButton = (JButton) components[responseIndex];
            Response currentResponse = currentDialogueResponses.get(responseIndex);
            currentButton.setText(currentResponse.getResponseText());
            currentButton.setVisible(true);
        }
    }

    private int readResponseToFindNextDialogue(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
    }

}
