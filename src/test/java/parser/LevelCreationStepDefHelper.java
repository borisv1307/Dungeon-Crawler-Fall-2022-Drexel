package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import values.TestingTunableParameters;
import values.TunableParameters;

public class LevelCreationStepDefHelper {
	protected static final int ONE = 1;
	protected static final int COORDINATE_OFFSET = ONE;

	protected void writeLevelFile(List<String> levelStrings)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(getFilePath(), "UTF-8");
		for (String levelString : levelStrings) {
			writer.println(levelString);
		}
		writer.close();
	}

	private String getFilePath() {
		return TestingTunableParameters.FILE_LOCATION_PREFIX + ONE + TunableParameters.FILE_NAME_SUFFIX;
	}
}