package ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import org.junit.Test;
import org.mockito.Mockito;

import values.TunableParameters;

public class GameFrameUITest {

	GameFrame gameFrame;

	@SuppressWarnings("serial")
	@Test
	public void constructor() throws Exception {
		int width = TunableParameters.SCREEN_WIDTH;
		int height = TunableParameters.SCREEN_HEIGHT;
		final GamePanel gamePanel = Mockito.mock(GamePanel.class);
		WindowAdapterSystemExit windowAdapter = Mockito.mock(WindowAdapterSystemExit.class);
		gameFrame = new GameFrame(gamePanel, windowAdapter) {
			@Override
			public Component add(Component comp) {
				assertThat((GamePanel) comp, equalTo(gamePanel));
				return gamePanel;
			}

			@Override
			public void setVisible(boolean b) {
				assertThat(b, equalTo(true));
			}
		};
		Mockito.verify(gamePanel).setPreferredSize(new Dimension(width, height));
		Mockito.verify(gamePanel).init();
		assertThat(gameFrame.isResizable(), equalTo(false));
		assertThat(gameFrame.getWindowListeners(), arrayContaining((WindowListener) windowAdapter));
	}
}
