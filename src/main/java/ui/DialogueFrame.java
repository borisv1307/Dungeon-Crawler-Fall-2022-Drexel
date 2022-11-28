package ui;

import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogueFrame extends Frame {
    private final List<DialogueButton> buttons;
    private final DialoguePanel dialoguePanel;
    private final transient DialogueSystem dialogueSystem;

    public DialogueFrame(DialoguePanel dialoguePanel, DialogueSystem dialogueSystem) {
        this.dialoguePanel = dialoguePanel;
        this.dialogueSystem = dialogueSystem;

        buttons = dialoguePanel.getDialoguePanelButtons();

        setResizable(false);
        setLayout(new GridBagLayout());

        dialoguePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
        dialoguePanel.setBackground(Color.GRAY);
        dialoguePanel.setVisible(true);

        addButtonListenerToButtons();

        add(dialoguePanel);

        addWindowListener(new WindowAdapterDialogueFrameExit(dialogueSystem));

        setVisible(false);
        pack();
    }

    public JTextArea getDialogueTextArea() {
        return dialoguePanel.getDialogueTextArea();
    }

    public List<DialogueButton> getButtons() {
        return dialoguePanel.getDialoguePanelButtons();
    }

    private void addButtonListenerToButtons() {
        for (DialogueButton button : buttons) {
            button.addActionListener(new ButtonClickActionListener(dialogueSystem));
        }
    }
}
