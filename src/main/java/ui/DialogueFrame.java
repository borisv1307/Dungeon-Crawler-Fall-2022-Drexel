package ui;

import values.TunableParameters;

import java.awt.*;

public class DialogueFrame extends Frame {

    private static final long serialVersionUID = 1L;

    public DialogueFrame(DialoguePanel dialoguePanel, WindowAdapterSystemExit windowAdapterSystemExit) {
        setResizable(false);
        addWindowListener(windowAdapterSystemExit);
        dialoguePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
        add(dialoguePanel);

        pack();
        setVisible(true);
    }

}
