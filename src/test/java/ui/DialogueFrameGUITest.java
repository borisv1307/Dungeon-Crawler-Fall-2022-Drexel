package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static values.TunableParameters.*;

public class DialogueFrameGUITest {
    DialogueFrame dialogueFrame;
    int height;
    int width;
    DialoguePanel dialoguePanel;
    DialogueSystem dialogueSystem;
    DialogueButton dialogueButton;
    DialogueButton buttonOne;
    DialogueButton buttonTwo;
    DialogueButton buttonThree;
    JTextArea jTextArea;
    DialoguePanel mockDialoguePanel;
    List<DialogueButton> buttons;
    Dimension dimension;
    Point actualComponentPosition;

    private static final Dimension EXPECTED_BUTTON_DIMENSIONS = new Dimension(DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT);

    private static final Dimension EXPECTED_TEXT_AREA_DIMENSIONS = new Dimension(DIALOGUE_TEXT_AREA_WIDTH, DIALOGUE_TEXT_AREA_HEIGHT);

    @Before

    public void setUp() {
        dialoguePanel = new DialoguePanel();

        mockDialoguePanel = Mockito.mock(DialoguePanel.class);
        dialogueSystem = Mockito.mock(DialogueSystem.class);

        dialogueButton = new DialogueButton();

        width = TunableParameters.SCREEN_WIDTH;
        height = TunableParameters.SCREEN_HEIGHT;

        dialogueFrame = new DialogueFrame(new DialoguePanel(), dialogueSystem);
        buttons = dialogueFrame.getButtons();

        buttonOne = buttons.get(0);
        buttonTwo = buttons.get(1);
        buttonThree = buttons.get(2);

        jTextArea = dialogueFrame.getDialogueTextArea();

        actualComponentPosition = new Point();
    }

    @Test
    public void constructor() {
        dialogueFrame = new DialogueFrame(mockDialoguePanel, dialogueSystem) {
            @Override
            public Component add(Component comp) {
                assertThat((Panel) comp, equalTo(mockDialoguePanel));
                return mockDialoguePanel;
            }

            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(false));
            }
        };

        WindowAdapterDialogueFrameExit windowAdapter = new WindowAdapterDialogueFrameExit(dialogueSystem);

        assertThat(dialogueFrame.isResizable(), equalTo(false));
        assertThat(dialogueFrame.getWindowListeners(), arrayContaining((WindowListener) windowAdapter));

        Mockito.verify(mockDialoguePanel).setPreferredSize(new Dimension(width, height));
        Mockito.verify(mockDialoguePanel).setBackground(Color.GRAY);
    }

    @Test
    public void dialogue_panel_layout_manager_is_null() {
        dialoguePanel = new DialoguePanel();
        final LayoutManager actual = dialoguePanel.getLayout();
        assertNull(actual);
    }

    @Test
    public void dialogue_panel_has_has_three_button_components_on_creation() {
        assertEquals(3, dialoguePanel.getDialoguePanelButtons().size());
    }

    @Test
    public void dialogue_panel_components_should_either_equal_dialogue_button_or_j_text_field() {
        dialoguePanel = new DialoguePanel() {
            @Override
            public Component add(Component comp) {

                if (comp.getWidth() <= 300) {
                    DialogueButton currentButton = (DialogueButton) comp;
                    assertEquals(currentButton, dialogueButton);
                } else {
                    JTextArea currentJTextArea = (JTextArea) comp;
                    assertThat(currentJTextArea.getClass(), equalTo(JTextArea.class));
                }
                return comp;
            }
        };
    }

    @Test
    public void dialogue_button_font_applied_to_button_one_creation() {
        final Font dialogueButtonFont = buttonOne.getFont();

        assertEquals(RESPONSE_FONT_STYLE, dialogueButtonFont.getStyle());
        assertEquals(RESPONSE_FONT_SIZE, dialogueButtonFont.getSize());
        assertEquals("Response Font", dialogueButtonFont.getName());
    }

    @Test
    public void dialogue_panel_button_one_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonOne.getWidth(), buttonOne.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_button_one_is_created_with_correct_X_Y_position() {
        actualComponentPosition = buttonOne.getLocation();
        assertEquals(new Point(0, 10), actualComponentPosition.getLocation());
    }

    @Test
    public void dialogue_panel_button_two_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonTwo.getWidth(), buttonTwo.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_button_two_is_created_with_correct_X_Y_position() {
        actualComponentPosition = buttonTwo.getLocation();
        assertEquals(new Point(300, 10), actualComponentPosition.getLocation());
    }

    @Test
    public void dialogue_panel_button_three_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonThree.getWidth(), buttonThree.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_button_three_is_created_with_correct_X_Y_position() {
        actualComponentPosition = buttonThree.getLocation();
        assertEquals(new Point(600, 10), actualComponentPosition.getLocation());
    }

    @Test
    public void dialogue_panel_text_area_has_correct_font() {
        final Font textAreaFont = jTextArea.getFont();

        assertEquals(DIALOGUE_FONT_STYLE, textAreaFont.getStyle());
        assertEquals(DIALOGUE_FONT_SIZE, textAreaFont.getSize());
        assertEquals("Text Area Font", textAreaFont.getName());
    }

    @Test
    public void dialogue_panel_is_created_with_proper_settings() {
        assertFalse(jTextArea.isEditable());
        assertTrue(jTextArea.getLineWrap());
    }

    @Test
    public void dialogue_panel_text_area_is_created_with_correct_X_Y_position() {
        actualComponentPosition = jTextArea.getLocation();
        assertEquals(new Point(50, 200), actualComponentPosition);
    }

    @Test
    public void dialogue_panel_text_area_is_created_with_correct_dimensions() {
        dimension = createDimensionFromWidthAndHeight(jTextArea.getWidth(), jTextArea.getHeight());
        assertEquals(EXPECTED_TEXT_AREA_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_frame_closes_when_user_clicks_dialogue_frame_system_exit() {
        final DialogueFrame frameActual = new DialogueFrame(new DialoguePanel(), dialogueSystem);
        frameActual.dispatchEvent(new WindowEvent(frameActual, WindowEvent.WINDOW_CLOSING));
        assertFalse(frameActual.isShowing());
    }

    private Dimension createDimensionFromWidthAndHeight(int width, int height) {
        return new Dimension(width, height);
    }
}
