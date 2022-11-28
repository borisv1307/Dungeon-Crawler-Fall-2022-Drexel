package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickActionListener implements ActionListener {

    private int nextDialogueID;
    private final DialogueSystem dialogueSystem;

    public ButtonClickActionListener(DialogueSystem dialogueSystem) {
        this.dialogueSystem = dialogueSystem;
        nextDialogueID = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogueButton buttonClicked = (DialogueButton) e.getSource();

        nextDialogueID = dialogueSystem.readPlayerResponseToFindNextDialogueID(buttonClicked.getButtonContent());
        if (nextDialogueID == -1) {
            dialogueSystem.terminate();
            dialogueSystem.setIsDialogueActive(false);
            dialogueSystem.updateDialogueFrame(1);
        }
        dialogueSystem.updateDialogueFrame(nextDialogueID);
    }

}
