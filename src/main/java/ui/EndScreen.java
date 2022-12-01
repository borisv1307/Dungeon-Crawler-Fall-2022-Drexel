package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends Screen implements ActionListener {
    JRadioButton[] end = new JRadioButton[1];
    String[] endStrings = {"EXIT!"};
    EndScreenPanel endButtonPanel;


    public EndScreen() {
        endButtonPanel = new EndScreenPanel();
        endButtonPanel.setBackground(Color.BLACK);

        for (int i = 0; i < end.length; i++) {
            end[i] = new JRadioButton(endStrings[i]);
            end[i].addActionListener(this);
            end[i].setBackground(Color.WHITE);
            end[i].setBounds(230, 200 + i * 50, 130, 30);
            endButtonPanel.add(end[i]);
        }

        endButtonPanel.setLayout(null);
        getContentPane().add(endButtonPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == end[0]) {
            System.exit(0);
        }

    }

    private static class EndScreenPanel extends Panel {
        EndScreenPanel() {
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D) g;

            graphics2D.setColor(Color.BLUE);
            graphics2D.setFont(new Font("Times New Roman", Font.BOLD, 25));
            graphics2D.drawString("CONGRATULATIONS YOU WIN!!", 135, 85);
        }

    }
}
