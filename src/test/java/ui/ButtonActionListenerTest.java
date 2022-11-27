package ui;

import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;

public class ButtonActionListenerTest {
    @Test
    public void button_listener_updates_dialogue_frame_when_user_clicks_a_response_button() {

        DialogueFrame dialogueFrame = Mockito.mock(DialogueFrame.class);
        ButtonClickActionListener buttonClickActionListener = new ButtonClickActionListener(dialogueFrame);
        JButton jButton = Mockito.mock(JButton.class);
        ActionEvent actionEvent = Mockito.mock(ActionEvent.class);
        Mockito.when(actionEvent.getSource()).thenReturn(jButton);

        buttonClickActionListener.actionPerformed(actionEvent);

        Mockito.verify(dialogueFrame).readPlayerResponseToFindNextDialogueID(anyString());
        Mockito.verify(dialogueFrame).updateDialogueFrame(anyInt());
    }

    @Test
    public void button_listener_closes_dialogue_frame_if_user_chooses_a_response_with_ID_negative_one() {

        DialogueFrame dialogueFrame = Mockito.mock(DialogueFrame.class);
        ButtonClickActionListener buttonClickActionListener = new ButtonClickActionListener(dialogueFrame);

        JButton jButton = Mockito.mock(JButton.class);

        ActionEvent actionEvent = Mockito.mock(ActionEvent.class);

        Mockito.when(actionEvent.getSource()).thenReturn(jButton);
        Mockito.when(dialogueFrame.readPlayerResponseToFindNextDialogueID(anyString())).thenReturn(-1);

        buttonClickActionListener.actionPerformed(actionEvent);

        Mockito.verify(dialogueFrame).dispose();
        Mockito.verify(dialogueFrame, never()).updateDialogueFrame(anyInt());
    }

}
