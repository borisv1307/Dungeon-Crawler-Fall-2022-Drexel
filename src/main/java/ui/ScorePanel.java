package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import values.TunableParameters;

public class ScorePanel {

	private static final String SCORE = "Score: ";
	private static final String LIVES = "Lives: ";
	private final JPanel panel;
	private int playerScore = TunableParameters.STARTING_SCORE;
	private final JLabel scoreText = new JLabel(SCORE + playerScore);
	private int playerLives = TunableParameters.STARTING_AND_MAXIMUM_LIVES;
	private final JLabel livesText = new JLabel(LIVES + playerLives);
	private int livesIncrementCountDown = 0;

	public ScorePanel(JPanel panel) {
		this.panel = panel;

		this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.panel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCORE_HEIGHT));
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));

		scoreText.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreText.setFont(new Font(TunableParameters.FONT, Font.PLAIN, TunableParameters.FONT_SIZE));

		livesText.setHorizontalAlignment(SwingConstants.LEFT);
		livesText.setFont(new Font(TunableParameters.FONT, Font.PLAIN, TunableParameters.FONT_SIZE));

		panel.add(livesText);
		panel.add(Box.createHorizontalGlue());
		panel.add(scoreText);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getScoreText() {
		return scoreText;
	}

	public JLabel getLivesText() {
		return livesText;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
		scoreText.setText(SCORE + playerScore);
	}

	public int getPlayerLives() {
		return playerLives;
	}

	public void setPlayerLives(int playerLives) {
		this.playerLives = playerLives;
		livesText.setText(LIVES + playerLives);
		livesText.setForeground(Color.BLACK);
	}

	public int getLivesIncrementCountDown() {
		return livesIncrementCountDown;
	}

	public void setLivesIncrementCountDown(int livesIncrementCountDown) {
		this.livesIncrementCountDown = livesIncrementCountDown;
	}

	public void incrementScore() {
		playerScore++;
		scoreText.setText(SCORE + playerScore);

		if (livesIncrementCountDown == TunableParameters.GAIN_LIVES_AFTER_N_KILLS - 1
				&& playerLives < TunableParameters.STARTING_AND_MAXIMUM_LIVES) {
			incrementLives();
			livesIncrementCountDown = 0;
		} else {
			livesIncrementCountDown++;
		}
	}

	public void incrementLives() {
		playerLives++;
		livesText.setText(LIVES + playerLives);
	}

	public void decrementLives() {
		playerLives--;
		livesText.setText(LIVES + playerLives);

		if (playerLives <= 0) {
			livesText.setText(TunableParameters.LOSS_MESSAGE);
			livesText.setForeground(Color.RED);
		}
	}
}
