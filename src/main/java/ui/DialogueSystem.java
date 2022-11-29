package ui;

import wrappers.XMLParserWrapper;

import javax.swing.*;
import java.util.List;

public class DialogueSystem {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    private final List<DialogueButton> dialogueButtons;
    private Dialogue currentDialogue;
    final DialogueFrame dialogueFrame;
    private List<Response> currentDialogueResponses;
    private boolean isDialogueActive;

    public DialogueSystem() {

        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();

        currentDialogue = dialogues.get(0);
        currentDialogueResponses = currentDialogue.getResponses();

        dialogueFrame = new DialogueFrame(new DialoguePanel(), this);
        dialogueFrame.setVisible(false);

        dialogueButtons = dialogueFrame.getButtons();

        isDialogueActive = false;
    }

    public DialogueFrame getDialogueFrame() {
        return dialogueFrame;
    }

    public DialogueButton getButton(int index) {
        return dialogueButtons.get(index);
    }

    public Dialogue getCurrentDialogue() {
        return currentDialogue;
    }

    public void initiateDialogueFrame() {
        if (!isDialogueActive) {
            updateDialogueFrame(1);
            setIsDialogueActive(true);
            dialogueFrame.setVisible(true);
        }
    }

    public void setIsDialogueActive(boolean isDialogueActive) {
        this.isDialogueActive = isDialogueActive;
    }

    public boolean isDialogueActive() {
        return isDialogueActive;
    }

    public void terminate() {
        dialogueFrame.dispose();
    }

    public void updateDialogueFrame(int nextDialogueID) {
        resetButtons();
        setCurrentDialogueToTargetDialogue(nextDialogueID);
        updateJTextFieldContent(nextDialogueID);
        updateButtonsContent();
    }

    public JTextArea getActiveDialoguePanelTextArea() {
        return dialogueFrame.getDialogueTextArea();
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    void resetCurrentDialogueToIDOne() {
        setCurrentDialogueToTargetDialogue(1);
    }

    int readPlayerResponseToFindNextDialogueID(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
    }

    void setCurrentDialogueToTargetDialogue(int dialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == dialogueID) {
                currentDialogue = dialogue;
            }
        }
    }

    private void updateJTextFieldContent(int targetDialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                dialogueFrame.getDialogueTextArea().setText(dialogue.getDialogueContent());
            }
        }
    }

    private void resetButtons() {
        for (int index = 0; index < dialogueButtons.size(); index++) {
            DialogueButton button = dialogueButtons.get(index);
            button.setVisible(false);
        }
    }

    private void updateButtonsContent() {
        currentDialogueResponses = currentDialogue.getResponses();
        for (int responseIndex = 0; responseIndex < currentDialogueResponses.size(); responseIndex++) {
            DialogueButton currentButton = dialogueButtons.get(responseIndex);
            Response currentResponse = currentDialogueResponses.get(responseIndex);

            currentButton.setText(currentResponse.getResponseText());
            currentButton.setVisible(true);
        }
    }
}
