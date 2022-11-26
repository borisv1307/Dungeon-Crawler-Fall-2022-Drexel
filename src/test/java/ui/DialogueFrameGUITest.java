package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DialogueFrameGUITest {
    DialogueFrame dialogueFrame;
    JButton responseButtonOne;
    JButton responseButtonTwo;
    JButton responseButtonThree;
    JTextArea textFieldActual;
    GameEngine gameEngine;


    @Before
    public void setUp() {
        dialogueFrame = new DialogueFrame();
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);

        responseButtonOne = (JButton) dialogueFrame.getComponent(0);
        responseButtonTwo = (JButton) dialogueFrame.getComponent(1);
        responseButtonThree = (JButton) dialogueFrame.getComponent(2);
        textFieldActual = (JTextArea) dialogueFrame.getComponent(3);


    }

    @Test
    public void dialogue_frame_has_four_components() {
        final int actual = dialogueFrame.getComponents().length;
        assertEquals(4, actual);
    }

    @Test
    public void dialogue_frame_components_are_visible_when_created() {
        dialogueFrame = new DialogueFrame() {
            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(true));
            }
        };
    }

    @Test
    public void dialogue_frame_is_not_resizable() {
        assertThat(dialogueFrame.isResizable(), equalTo(false));
    }

    @Test
    public void dialogue_frame_is_size_900_x_600() {
        final Dimension frameSize = dialogueFrame.getSize();
        final double delta = .0001;
        assertEquals(900, frameSize.getWidth(), delta);
        assertEquals(600, frameSize.getHeight(), delta);
    }

    @Test
    public void close_dialogue_frame_when_user_clicks_exit_dialogue_frame_exit() {
        dialogueFrame.dispatchEvent(new WindowEvent(dialogueFrame, WindowEvent.WINDOW_CLOSING));
        assertFalse(dialogueFrame.isShowing());
    }

    @Test
    public void dialogue_frame_layout_is_grid_bag() {
        assertEquals(dialogueFrame.getLayout().toString(), new GridBagLayout().toString());
    }

    @Test
    public void dialogue_frame_text_area_read_only() {
        assertFalse(textFieldActual.isEditable());
    }

    @Test
    public void dialogue_frame_background_is_grey() {
        assertEquals(Color.GRAY, dialogueFrame.getBackground());
    }

    @Test
    public void text_area_has_correct_font_settings() {
        Font actual = textFieldActual.getFont();
        assertEquals(Font.ITALIC, actual.getStyle());
        assertEquals(16, actual.getSize());
        assertEquals("Text Area Font", actual.getName());
    }


}
