package ui;

import javax.swing.*;
import java.util.Objects;

public class DialogueButton extends JButton {
    private final String type;

    public DialogueButton() {
        setText("default dialogue button text");
        setActionCommand("Dialogue Button Click Event");
        type = "Dialogue Button";
    }

    public String getButtonContent() {
        return this.getText();
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        DialogueButton dialogueButton = (DialogueButton) object;
        return type.equals(dialogueButton.getType()) && this.getActionCommand().equals(dialogueButton.getActionCommand());
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

}
