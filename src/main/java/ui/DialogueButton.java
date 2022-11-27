package ui;

import javax.swing.*;
import java.awt.*;

public class DialogueButton extends JButton {

    public DialogueButton(String label) {
        setPreferredSize(new Dimension(275, 150));
        setText("default");
        setName(label);
    }
}
