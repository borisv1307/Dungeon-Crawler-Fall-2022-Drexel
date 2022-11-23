package ui;

import values.TunableParameters;
import wrappers.XMLParserWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DialogueManager {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    private final JTextField textField;
    private Dialogue currentDialogue;

    public DialogueManager() {
        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();
        textField = new JTextField("Default");
        currentDialogue = dialogues.get(0);
    }

    public Frame createFrame(String label) {
        Frame frame = new Frame(label);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);

        applySettings(frame);

        addChoiceButtons(frame, constraints, currentDialogue);

        textField.setText(currentDialogue.getDialogueContent());
        textField.setEditable(false);
        Font textFieldFont = new Font(textField.getFont().getName(), textField.getFont().getStyle(), 16);
        textField.setFont(textFieldFont);
        textField.setSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;

        frame.add(textField, constraints);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void applySettings(Frame parentFrame) {
        parentFrame.setResizable(false);
        parentFrame.setSize(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT);
        parentFrame.setBackground(Color.LIGHT_GRAY);
        parentFrame.setVisible(true);
        parentFrame.setLayout(new GridBagLayout());
    }

    private void addChoiceButtons(Frame parentFrame, GridBagConstraints constraints, Dialogue currentDialogue) {
        int startColumn = 0;
        int responseIndex = 0;

        Response[] responses = currentDialogue.getResponses();
        for (String buttonLabel : TunableParameters.CHOICE_BUTTONS_LABELS) {

            JButton button = new JButton(responses[responseIndex].getResponseText());
            button.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH / 3, TunableParameters.SCREEN_HEIGHT / 3));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateJTextField(1);
                }
            });
            constraints.gridx = startColumn;
            constraints.gridy = 0;

            parentFrame.add(button, constraints);
            startColumn++;
            responseIndex++;
        }
    }

    public void updateJTextField(int targetDialogueID) {
        Dialogue targetDialogue = dialogues.get(targetDialogueID);
        textField.setText(targetDialogue.getDialogueContent());
    }


}
