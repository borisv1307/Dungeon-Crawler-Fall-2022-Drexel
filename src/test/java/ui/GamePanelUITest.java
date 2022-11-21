package ui;

import engine.GameEngine;
import org.junit.Test;
import org.mockito.Mockito;
import values.TunableParameters;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GamePanelUITest {

    GamePanel gamePanel;

    @Test
    public void constructor() throws Exception {
        int width = TunableParameters.DIALOGUE_PANEL_WIDTH;
        int height = TunableParameters.DIALOGUE_PANEL_HEIGHT;
        final DialoguePanel dialoguePanel = Mockito.mock(DialoguePanel.class);
        final GameEngine gameEngine = Mockito.mock(GameEngine.class);
        final TilePainter tilePainter = Mockito.mock(TilePainter.class);

        gamePanel = new GamePanel(gameEngine, dialoguePanel, tilePainter) {

            @Override
            public Component add(Component component) {
                assertThat((DialoguePanel) component, equalTo(dialoguePanel));
                return dialoguePanel;
            }

            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(true));
            }

        };
        Mockito.verify(dialoguePanel).setPreferredSize(new Dimension(width, height));
        Mockito.verify(dialoguePanel).setBackground(Color.YELLOW);
    }
}
