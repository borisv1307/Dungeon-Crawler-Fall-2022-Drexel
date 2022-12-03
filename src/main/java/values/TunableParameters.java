package values;

public final class TunableParameters {
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 600;
	public static final int TARGET_FPS = 45;
	public static final String FILE_LOCATION_PREFIX = "src/main/resources/levels/";
	public static final String FILE_NAME_SUFFIX = ".txt";
	public static final String RIGHT = "right";
	public static final String LEFT = "left";
	public static final String UP = "up";
	public static final String DOWN = "down";
	public static final String[] DIRECTIONS = { LEFT, UP, DOWN, RIGHT };

	private TunableParameters() {
	}
}
