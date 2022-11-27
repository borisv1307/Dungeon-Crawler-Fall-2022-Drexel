package ui;

import javax.swing.*;

public class DialogueButton extends JButton {
    public DialogueButton(String label) {
        setText("default dialogue button");
        setName(label);
    }

    public String getButtonContent() {
        return this.getText();
    }

}
