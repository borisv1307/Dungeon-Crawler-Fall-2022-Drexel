package ui;

import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogueFrame extends Frame {
    private final ArrayList<DialogueButton> buttons;
    private final JTextArea dialogueTextArea;
    GridBagConstraints constraints;
    DialoguePanel dialoguePanel;

    public DialogueFrame(DialoguePanel dialoguePanel) {
        this.dialoguePanel = dialoguePanel;

        buttons = dialoguePanel.getDialoguePanelButtons();

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

        addWindowListener(new WindowAdapterDialogueFrameExit());

        setVisible(false);
        pack();
    }

    public JTextArea getDialogueTextArea() {
        return dialogueTextArea;
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
        for (DialogueButton button : buttons) {
            button.addActionListener(new ButtonClickActionListener());
        }
    }
}
