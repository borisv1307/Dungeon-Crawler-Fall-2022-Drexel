package ui;

import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogueManager {

    public Frame createFrame(String label) {
        Frame frame = new Frame(label);
        frame.setResizable(false);
        frame.setSize(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());

        for (String buttonLabel : TunableParameters.CHOICE_BUTTONS_LABELS) {
            JButton button = new JButton(buttonLabel);
            button.setSize(200, 200);
            frame.add(button);
            button.setVisible(true);
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        return frame;
    }
}
