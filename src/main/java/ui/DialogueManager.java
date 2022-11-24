package ui;

import values.TunableParameters;
import wrappers.XMLParserWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DialogueManager {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    private Frame dialogueFrame;
    private final JTextField textField;
    private Dialogue currentDialogue;
    private Response[] currentDialogueResponses;

    public DialogueManager() {
        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();
        dialogueFrame = new Frame();
        textField = new JTextField("Default");
        currentDialogue = dialogues.get(0);
        currentDialogueResponses = currentDialogue.getResponses();
    }

    public Frame createFrame() {
        String label = "Dialogue Frame";
        dialogueFrame = new Frame(label);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);

        applySettings(dialogueFrame);

        addChoiceButtons(dialogueFrame, constraints);

        textField.setText(currentDialogue.getDialogueContent());
        textField.setEditable(false);
        Font textFieldFont = new Font(textField.getFont().getName(), textField.getFont().getStyle(), 16);
        textField.setFont(textFieldFont);
        textField.setSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;

        dialogueFrame.add(textField, constraints);

        dialogueFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogueFrame.dispose();
            }
        });

        dialogueFrame.pack();
        dialogueFrame.setVisible(true);

        return dialogueFrame;
    }

    public Dialogue getCurrentDialogue() {
        return this.currentDialogue;
    }

    void updateJTextField(int targetDialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                Dialogue targetDialogue = dialogue;
                textField.setText(targetDialogue.getDialogueContent());
            }
        }
    }

    private void updateButtons() {
        currentDialogueResponses = currentDialogue.getResponses();
        Component[] components = dialogueFrame.getComponents();

        for (int responseIndex = 0; responseIndex < 3; responseIndex++) {
            JButton currentButton = (JButton) components[responseIndex];
            Response currentResponse = currentDialogueResponses[responseIndex];
            currentButton.setText(currentResponse.getResponseText());
        }
    }

    private void applySettings(Frame parentFrame) {
        parentFrame.setResizable(false);
        parentFrame.setSize(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT);
        parentFrame.setBackground(Color.LIGHT_GRAY);
        parentFrame.setVisible(true);
        parentFrame.setLayout(new GridBagLayout());
    }

    private void addChoiceButtons(Frame parentFrame, GridBagConstraints constraints) {
        int startColumn = 0;
        int responseIndex = 0;

        for (String buttonLabel : TunableParameters.CHOICE_BUTTONS_LABELS) {
            JButton button = createButton(responseIndex, startColumn, constraints);
            parentFrame.add(button, constraints);

            startColumn++;
            responseIndex++;
        }
    }

    private JButton createButton(int responseIndex, int column, GridBagConstraints constraints) {
        Response currentResponse = currentDialogueResponses[responseIndex];
        JButton button = new JButton(currentResponse.getResponseText());

        button.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH / 3, TunableParameters.SCREEN_HEIGHT / 3));
        button.addActionListener(e -> {
            JButton buttonClicked = (JButton) e.getSource();
            int nextDialogueID = readResponseToFindNextDialogue(buttonClicked.getText());
            if (nextDialogueID == -1) {
                dialogueFrame.dispose();
            }
            updateFrame(nextDialogueID);
        });
        constraints.gridx = column;
        constraints.gridy = 0;
        return button;
    }

    private void updateFrame(int nextDialogueID) {
        updateCurrentDialogueToTarget(nextDialogueID);
        updateJTextField(nextDialogueID);
        updateButtons();
    }

    private int readResponseToFindNextDialogue(String currentResponse) {
        int targetID = 0;
        for (Response response : currentDialogueResponses) {
            if (response.getResponseText().equals(currentResponse)) {
                targetID = response.getTarget();
            }
        }
        return targetID;
    }

    private void updateCurrentDialogueToTarget(int targetDialogueID) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetDialogueID) {
                currentDialogue = dialogue;
            }
        }
    }


}
