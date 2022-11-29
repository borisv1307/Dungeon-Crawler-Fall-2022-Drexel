package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JRadioButton[] choose = new JRadioButton[1];
    String[] chooseStrings = {"EXIT!"};
    EndScreenPanel buttonPanel;


    public EndScreen() {
        buttonPanel = new EndScreenPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(320, 127, 600, 400);
        setResizable(false);

        buttonPanel.setBackground(Color.WHITE);

        for (int i = 0; i < choose.length; i++) {
            choose[i] = new JRadioButton(chooseStrings[i]);
            choose[i].addActionListener(this);
            choose[i].setBackground(Color.WHITE);
            choose[i].setBounds(230, 200 + i * 50, 200, 30);
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
            graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            graphics2D.drawString("CONGRATULATIONS YOU WIN!!", 135, 85);
        }

    }
}
