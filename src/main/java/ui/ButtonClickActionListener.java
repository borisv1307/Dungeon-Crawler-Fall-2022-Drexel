package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickActionListener implements ActionListener {
    private final DialogueFrame dialogueFrame;

    public ButtonClickActionListener(DialogueFrame dialogueFrame) {
        this.dialogueFrame = dialogueFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DialogueButton buttonClicked = (DialogueButton) e.getSource();

        int nextDialogueID = dialogueFrame.readPlayerResponseToFindNextDialogueID(buttonClicked.getText());
        if (nextDialogueID != -1) {
            dialogueFrame.updateDialogueFrame(nextDialogueID);
        } else {
            dialogueFrame.dispose();
        }
    }


}
