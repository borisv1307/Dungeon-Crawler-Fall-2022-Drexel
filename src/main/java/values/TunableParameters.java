package values;

public final class TunableParameters {
	private TunableParameters() {
	}

	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 600;

	public static final int TARGET_FPS = 45;
	public static final int PLAYER_SPEED = 1;
	public static final int BUTTON_WIDTH = 80;
	public static final int BUTTON_HEIGHT = 30;
	public static final String FILE_LOCATION_PREFIX = "src/main/resources/levels/";

	public static final String XML_LOCATION_PREFIX = "src/main/resources/dialogues/";

	public static final String XML_NAME_SUFFIX = ".xml";

	public static final String FILE_NAME_SUFFIX = ".txt";

	public static final String[] CHOICE_BUTTONS_LABELS = {
			"Choice One",
			"Choice Two",
			"Choice Three"
	};

}
