package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickActionListener implements ActionListener {

    int nextDialogueID;

    public ButtonClickActionListener() {
        nextDialogueID = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogueSystem dialogueSystem = DialogueSystem.getInstance();
        DialogueButton buttonClicked = (DialogueButton) e.getSource();

        nextDialogueID = dialogueSystem.readPlayerResponseToFindNextDialogueID(buttonClicked.getButtonContent());
        if (nextDialogueID == -1) {
            dialogueSystem.terminate();
            dialogueSystem.setIsDialogueActive(false);
        }
        dialogueSystem.updateDialogueFrame(nextDialogueID);
    }
}
