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

    private Response[] currentDialogueResponses;

    public DialogueManager() {
        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();
        textField = new JTextField("Default");
        currentDialogue = dialogues.get(0);
        currentDialogueResponses = currentDialogue.getResponses();
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

        for (String buttonLabel : TunableParameters.CHOICE_BUTTONS_LABELS) {

            Response currentResponse = currentDialogueResponses[responseIndex];
            JButton button = new JButton(currentResponse.getResponseText());

            button.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH / 3, TunableParameters.SCREEN_HEIGHT / 3));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    updateJTextField(findNextDialogueID(buttonClicked.getText()));
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
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                Dialogue targetDialogue = dialogue;
                textField.setText(targetDialogue.getDialogueContent());
            }
        }
    }

    public void updateButtons(Dialogue Dialogue) {
        
    }

    public int findNextDialogueID(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
    }


}
