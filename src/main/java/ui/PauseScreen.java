package ui;

import main.DungeonCrawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends Screen implements ActionListener {
    JRadioButton[] resume = new JRadioButton[1];
    String[] resumeStrings = {"CONTINUE!"};
    ResumeScreenPanel resumeButtonPanel;


    public PauseScreen() {
        resumeButtonPanel = new ResumeScreenPanel();
        resumeButtonPanel.setBackground(Color.BLACK);

        for (int i = 0; i < resume.length; i++) {
            resume[i] = new JRadioButton(resumeStrings[i]);
            resume[i].addActionListener(this);
            resume[i].setBackground(Color.WHITE);
            resume[i].setBounds(230, 200 + i * 50, 130, 30);
            resumeButtonPanel.add(resume[i]);
        }

        resumeButtonPanel.setLayout(null);
        getContentPane().add(resumeButtonPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == resume[0]) {
            new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
            dispose();
        }

    }

    private static class ResumeScreenPanel extends Panel {
        ResumeScreenPanel() {
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D) g;

            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("Times New Roman", Font.BOLD, 45));
            graphics2D.drawString("RESUME GAME", 135, 85);
        }

    }
}
