package parser;

import values.TestingTunableParameters;
import values.TunableParameters;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class LevelCreationStepDefHelper {
    protected static final int ONE = 1;
    protected static final int COORDINATE_OFFSET = ONE;

    protected void writeLevelFile(final int level, final List<String> levelStrings)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(getFilePath(level), "UTF-8");
        for (String levelString : levelStrings) {
            writer.println(levelString);
        }
        writer.close();
    }

    private String getFilePath(final int level) {
        return TestingTunableParameters.FILE_LOCATION_PREFIX + level + TunableParameters.FILE_NAME_SUFFIX;
    }
}