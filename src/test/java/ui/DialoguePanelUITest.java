package ui;

import engine.GameEngine;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DialoguePanelUITest {
    DialoguePanel dialoguePanel;
    
    @Test
    public void constructor() throws Exception {
        final GameEngine gameEngine = Mockito.mock(GameEngine.class);

        dialoguePanel = new DialoguePanel(gameEngine) {
            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(true));
            }
        };
    }


}
