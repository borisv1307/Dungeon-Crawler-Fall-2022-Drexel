package values;

public final class TunableParameters {
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCORE_HEIGHT = 35;
	public static final int TARGET_FPS = 10;
	public static final int ENEMY_SPAWN_EVERY_N_FRAMES = 15;
	public static final int ENEMY_FIRE_EVERY_N_FRAMES = 10;
	public static final int GAIN_LIVES_AFTER_N_KILLS = 10;
	public static final int LOSS_LEVEL = 2;
	public static final String FILE_LOCATION_PREFIX = "src/main/resources/levels/";
	public static final String FILE_NAME_SUFFIX = ".txt";
	public static final int RANDOM_ENEMY_SPAWN_LOWER_BOUND = 2;
	public static final int HORIZONTAL_RANDOM_ENEMY_SPAWN_OFFSET = 3;
	public static final int VERTICAL_RANDOM_ENEMY_SPAWN_OFFSET = 3;
	public static final int DISTANCE_ALLOWED_FROM_PLAYER = 2;
	public static final int STARTING_SCORE = 0;
	public static final int STARTING_AND_MAXIMUM_LIVES = 5;
	public static final int FONT_SIZE = 18;
	public static final String FONT = "Calibri";
	public static final String LOSS_MESSAGE = "YOU LOST. Press Enter to Restart.";

	private TunableParameters() {
	}
}
