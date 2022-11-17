package Dialogue;

import javax.swing.*;
import java.awt.*;

public class DialoguePrinter extends JPanel {
    //TODO spike an implementation of DialoguePrinter
    public void paintDialogueText(Graphics graphics, String dialogue) {
        Graphics twoDimensionalGraphics = graphics;
        ((Graphics2D) twoDimensionalGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font("Serif", Font.PLAIN, 40);
        graphics.setColor(Color.BLACK);

        twoDimensionalGraphics.setFont(font);
        twoDimensionalGraphics.drawString(dialogue, 50, 120);
    }

}
