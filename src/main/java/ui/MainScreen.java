package ui;

import main.DungeonCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends Screen implements ActionListener {

    JRadioButton[] choose = new JRadioButton[2];
    String[] chooseStrings = {"START GAME!", "EXIT!"};
    MainScreenPanel buttonPanel;

    public MainScreen() {
        buttonPanel = new MainScreenPanel();
        buttonPanel.setBackground(Color.BLACK);

        for (int i = 0; i < choose.length; i++) {
            choose[i] = new JRadioButton(chooseStrings[i]);
            choose[i].addActionListener(this);
            choose[i].setBackground(Color.WHITE);
            choose[i].setBounds(210, 200 + i * 50, 130, 30);
            buttonPanel.add(choose[i]);
        }

        buttonPanel.setLayout(null);
        getContentPane().add(buttonPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == choose[0]) {
            new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
            setVisible(true);
            dispose();
        }

        if (obj == choose[1]) {
            System.exit(0);
        }

    }

    private static class MainScreenPanel extends Panel {
        MainScreenPanel() {
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D) g;

            graphics2D.setColor(Color.RED);
            graphics2D.setFont(new Font("Times New Roman", Font.BOLD, 45));
            graphics2D.drawString("Dungeon Crawler", 135, 85);
            graphics2D.setColor(Color.ORANGE);
            graphics2D.drawString("player", 210, 150);
        }
    }
}
