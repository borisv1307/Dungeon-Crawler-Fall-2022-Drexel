package ui;

import wrappers.XMLParserWrapper;

import java.util.List;

public class DialogueSystem {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    private final List<DialogueButton> dialogueButtons;
    private Dialogue currentDialogue;
    final DialogueFrame dialogueFrame;
    List<Response> currentDialogueResponses;
    boolean isDialogueActive;
    private static final DialogueSystem instance = new DialogueSystem();

    private DialogueSystem() {
        dialogueFrame = new DialogueFrame(new DialoguePanel());
        dialogueFrame.setVisible(false);

        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();

        currentDialogue = dialogues.get(0);

        currentDialogueResponses = currentDialogue.getResponses();

        dialogueButtons = dialogueFrame.dialoguePanel.buttons;

        isDialogueActive = false;
    }

    public DialogueButton getButton(int index) {
        return dialogueButtons.get(index);
    }

    public static DialogueSystem getInstance() {
        return instance;
    }

    public Dialogue getCurrentDialogue() {
        return currentDialogue;
    }

    public void initiateDialogueFrame() {
        updateDialogueFrame(1);
        dialogueFrame.setVisible(true);
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

    public int readPlayerResponseToFindNextDialogueID(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
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
            DialogueButton currentButton = dialogueButtons.get(responseIndex);
            Response currentResponse = currentDialogueResponses.get(responseIndex);
            currentButton.setText(currentResponse.getResponseText());
            currentButton.setVisible(true);
        }
    }
}
