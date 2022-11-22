package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.Mockito.mock;

public class DialoguePanelTest {
    Frame testFrame = new Frame();
    DialoguePanel dialoguePanel;
    GameEngine gameEngine;

    int width = 50;
    int height = 60;


    @Before
    public void setUp() throws Exception {
        gameEngine = mock(GameEngine.class);
        dialoguePanel = new DialoguePanel(gameEngine);
        dialoguePanel.setSize(width, height);
    }

    @Test
    public void paint() {
        Graphics graphics = mock(Graphics.class);
        dialoguePanel = mock(DialoguePanel.class, Mockito.CALLS_REAL_METHODS);
        dialoguePanel.paint(graphics);
        //Mockito.verify(dialoguePanel, Mockito.times(1)).add(any(Component.class));
        //Mockito.verify(dialoguePanel).add(any(Component.class));
        

    }

    @Test
    public void update() {
        Graphics dbg = mock(Graphics.class);
        Image dbImage = mock(Image.class);
        Mockito.when(dbImage.getGraphics()).thenReturn(dbg);

        dialoguePanel = mock(DialoguePanel.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(dialoguePanel.getWidth()).thenReturn(width);
        Mockito.when(dialoguePanel.getHeight()).thenReturn(height);
        Mockito.when(dialoguePanel.createImage(width, height)).thenReturn(dbImage);
        Mockito.doNothing().when(dialoguePanel).paint(dbg);
        Graphics graphics = mock(Graphics.class);
        dialoguePanel.update(graphics);
        dialoguePanel.update(graphics);
        Mockito.verify(dialoguePanel, Mockito.times(1)).createImage(width, height);
        Mockito.verify(dialoguePanel, Mockito.times(3)).getWidth();
        Mockito.verify(dialoguePanel, Mockito.times(3)).getHeight();
        Mockito.verify(dialoguePanel, Mockito.times(2)).paint(dbg);
        Mockito.verify(dbImage, Mockito.times(2)).getGraphics();

        Mockito.verify(graphics, Mockito.times(2)).drawImage(dbImage, 0, 0, dialoguePanel);
    }



    /*
    @Test
    public void button_one_is_created_with_label_choice_one() {
        assertEquals("java.awt.Button[button0,0,0,0x0,invalid,label=Choice One]", dialoguePanel.getComponent(0).toString());
    }

    @Test
    public void button_two_is_created_with_label_choice_two() {
        assertEquals("java.awt.Button[button1,0,0,0x0,invalid,label=Choice Two]", dialoguePanel.getComponent(1).toString());
    }

    @Test
    public void button_three_is_created_with_label_choice_two() {
        assertEquals("java.awt.Button[button2,0,0,0x0,invalid,label=Choice Three]", dialoguePanel.getComponent(2).toString());
    }
     */


}
