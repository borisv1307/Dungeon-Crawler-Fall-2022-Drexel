package ui;

import values.TunableParameters;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameCreator {

    public void createFrame() {
        Frame frame = new Frame("Dialogue Window");
        frame.setSize(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}
