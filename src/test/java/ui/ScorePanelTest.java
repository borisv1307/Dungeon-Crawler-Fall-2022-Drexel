package ui;

import static org.junit.Assert.assertEquals;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import values.TunableParameters;

public class ScorePanelTest {

	private ScorePanel scorePanel;
	private JPanel jPanel;

	@Before
	public void setUp() {
		jPanel = Mockito.mock(JPanel.class);
		scorePanel = new ScorePanel(jPanel);
	}

	@Test
	public void border() {
		Mockito.verify(jPanel).setBorder(Mockito.any());
	}

	@Test
	public void preferred_size() {
		Mockito.verify(jPanel).setPreferredSize(Mockito.any());
	}

	@Test
	public void layout() {
		Mockito.verify(jPanel).setLayout(Mockito.any());
	}

	@Test
	public void increment_score() {
		scorePanel.incrementScore();
		assertEquals(TunableParameters.STARTING_SCORE + 1, scorePanel.getPlayerScore());
	}

	@Test
	public void increment_lives() {
		scorePanel.incrementLives();
		assertEquals(TunableParameters.STARTING_LIVES + 1, scorePanel.getPlayerLives());
	}

	@Test
	public void decrement_lives() {
		scorePanel.decrementLives();
		assertEquals(TunableParameters.STARTING_LIVES - 1, scorePanel.getPlayerLives());
	}

	@Test
	public void increment_score_updates_text() {
		scorePanel.incrementScore();
		String newScore = "Score: " + (TunableParameters.STARTING_SCORE + 1);
		assertEquals(newScore, scorePanel.getScoreText().getText());
	}

	@Test
	public void increment_lives_updates_text() {
		scorePanel.incrementLives();
		String newLives = "Lives: " + (TunableParameters.STARTING_LIVES + 1);
		assertEquals(newLives, scorePanel.getLivesText().getText());
	}

	@Test
	public void decrement_lives_updates_text() {
		scorePanel.decrementLives();
		String newLives = "Lives: " + (TunableParameters.STARTING_LIVES - 1);
		assertEquals(newLives, scorePanel.getLivesText().getText());
	}
}
