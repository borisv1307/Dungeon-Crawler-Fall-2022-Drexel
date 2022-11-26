package ui;

import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogueFrame extends Frame {
    private final GridBagConstraints constraints;
    private final JButton buttonOne;
    private final JButton buttonTwo;
    private final JButton buttonThree;
    private final JTextArea dialogueDisplay;

    public DialogueFrame() {
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);

        applyGeneralFrameSettings();

        buttonOne = createButton(0, constraints);
        add(buttonOne, constraints);

        buttonTwo = createButton(1, constraints);
        add(buttonTwo, constraints);

        buttonThree = createButton(2, constraints);
        add(buttonThree, constraints);

        dialogueDisplay = createJTextArea();
        add(dialogueDisplay, constraints);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        pack();
    }

    public JTextArea getJTextField() {
        return dialogueDisplay;
    }

    void applyGeneralFrameSettings() {
        setResizable(false);
        setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
        setBackground(Color.GRAY);
        setVisible(true);
        setLayout(new GridBagLayout());
    }

    private JButton createButton(int columnPosition, GridBagConstraints constraints) {
        JButton button = new JButton("default");
        button.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH / 3, TunableParameters.SCREEN_HEIGHT / 3));
        constraints.gridx = columnPosition;
        constraints.gridy = 0;
        return button;
    }

    private JTextArea createJTextArea() {
        JTextArea dialogueDisplay = new JTextArea();
        dialogueDisplay.setText("default");
        dialogueDisplay.setEditable(false);

        Font displayFont = new Font("Text Area Font", Font.ITALIC, 16);

        dialogueDisplay.setFont(displayFont);
        dialogueDisplay.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH / 2, TunableParameters.SCREEN_WIDTH / 2));

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;

        return dialogueDisplay;
    }

}
