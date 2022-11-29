package ui;

import values.TunableParameters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends Frame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final MenuItem newGameMenuItem;
    private final MenuItem exitGameMenuItem;


    public GameFrame(GamePanel gamePanel, WindowAdapterSystemExit windowAdapterSystemExit) {
        setTitle("Dungeon Crawler");
        setResizable(false);
        setVisible(true);
        addWindowListener(windowAdapterSystemExit);
        gamePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
        add(gamePanel);
        pack();
        gamePanel.init();
        setLocationRelativeTo(null);


        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        newGameMenuItem = new MenuItem("New Game");
        exitGameMenuItem = new MenuItem("Exit");

        fileMenu.add(newGameMenuItem);
        fileMenu.add(exitGameMenuItem);

        menuBar.add(fileMenu);
        setMenuBar(menuBar);

        newGameMenuItem.addActionListener(this);
        exitGameMenuItem.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newGameMenuItem) {
            setVisible(true);
            new MainScreen();
        }

        if (source == exitGameMenuItem) {
            System.exit(0);
        }

    }
}
