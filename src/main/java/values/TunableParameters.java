package values;

public final class TunableParameters {
    private TunableParameters() {}
	
	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 600;

	public static final int TARGET_FPS = 45;

	public static final int TILE_TO_LASER_WIDTH = 5;
	public static final int TILE_TO_LASER_HEIGHT = 5;

	public static final String FILE_LOCATION_PREFIX = "src/main/resources/levels/";
	public static final String FILE_NAME_SUFFIX = ".txt";

	public static final double INITIAL_CHANCE_OF_SPAWN = .02;
}
