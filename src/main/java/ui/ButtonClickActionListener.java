package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickActionListener implements ActionListener {
    private final DialogueFrame dialogueFrame;

    public ButtonClickActionListener(DialogueFrame dialogueFrame) {
        this.dialogueFrame = dialogueFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        int nextDialogueID = dialogueFrame.readPlayerResponseToFindNextDialogueID(buttonClicked.getText());
        if (nextDialogueID == -1) {
            dialogueFrame.dispose();
        } else {
            dialogueFrame.updateDialogueFrame(nextDialogueID);
        }
    }


}
