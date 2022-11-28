package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.awt.*;

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
		assertEquals(TunableParameters.STARTING_AND_MAXIMUM_LIVES + 1, scorePanel.getPlayerLives());
	}

	@Test
	public void decrement_lives() {
		scorePanel.decrementLives();
		assertEquals(TunableParameters.STARTING_AND_MAXIMUM_LIVES - 1, scorePanel.getPlayerLives());
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
		String newLives = "Lives: " + (TunableParameters.STARTING_AND_MAXIMUM_LIVES + 1);
		assertEquals(newLives, scorePanel.getLivesText().getText());
	}

	@Test
	public void decrement_lives_updates_text() {
		scorePanel.decrementLives();
		String newLives = "Lives: " + (TunableParameters.STARTING_AND_MAXIMUM_LIVES - 1);
		assertEquals(newLives, scorePanel.getLivesText().getText());
	}

	@Test
	public void gaining_score_increments_live_increment_count_down() {
		scorePanel.incrementScore();
		assertEquals(1, scorePanel.getLivesIncrementCountDown());
	}

	@Test
	public void when_count_down_reaches_n_lives_it_should_reset() {
		scorePanel.decrementLives();
		incrementScore();
		assertEquals(0, scorePanel.getLivesIncrementCountDown());
	}

	@Test
	public void increment_lives_after_gaining_score_of_n() {
		decrement_lives();
		incrementScore();
		assertEquals(TunableParameters.STARTING_AND_MAXIMUM_LIVES, scorePanel.getPlayerLives());
	}

	@Test
	public void cannot_increment_score_beyond_maximum_score() {
		incrementScore();
		assertEquals(TunableParameters.STARTING_AND_MAXIMUM_LIVES, scorePanel.getPlayerLives());
	}

	@Test
	public void when_player_life_hits_zero_display_loss_message() {
		decrementLivesToZero();

		assertEquals(TunableParameters.LOSS_MESSAGE, scorePanel.getLivesText().getText());
	}

	@Test
	public void when_player_loses_loss_message_has_red_text() {
		decrementLivesToZero();

		assertSame(Color.RED, scorePanel.getLivesText().getForeground());
	}

	private void decrementLivesToZero() {
		for (int i = 0; i < TunableParameters.STARTING_AND_MAXIMUM_LIVES; i++) {
			scorePanel.decrementLives();
		}
	}

	private void incrementScore() {
		for (int i = 0; i < TunableParameters.GAIN_LIVES_AFTER_N_KILLS; i++) {
			scorePanel.incrementScore();
		}
	}
}
