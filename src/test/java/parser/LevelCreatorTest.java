package parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;
import values.TunableParameters;
import wrappers.ReaderWrapper;

public class LevelCreatorTest {

	private GameEngine gameEngine;
	private LevelCreator levelCreator;
	private final String FILE_LOCATION_PREFIX = "FILE_LOCATION_PREFIX";
	private ReaderWrapper readerWrapper;
	private final int LEVEL = 1;

	@Before
	public void setUp() {
		gameEngine = Mockito.mock(GameEngine.class);
		readerWrapper = Mockito.mock(ReaderWrapper.class);
		levelCreator = new LevelCreator(FILE_LOCATION_PREFIX, readerWrapper);
	}

	@Test
	public void file_not_found_exception() throws Exception {
		FileNotFoundException exception = Mockito.mock(FileNotFoundException.class);
		Mockito.when(readerWrapper.createBufferedReader(levelCreator.getFilePath(LEVEL))).thenThrow(exception);

		levelCreator.createLevel(gameEngine, LEVEL);
		Mockito.verify(gameEngine).setExit(true);
	}

	@Test
	public void io_exception_on_read() throws Exception {
		IOException exception = Mockito.mock(IOException.class);
		BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
		Mockito.when(readerWrapper.createBufferedReader(levelCreator.getFilePath(LEVEL))).thenReturn(bufferedReader);
		Mockito.when(bufferedReader.readLine()).thenThrow(exception);

		levelCreator.createLevel(gameEngine, LEVEL);
		Mockito.verify(gameEngine).setExit(true);
	}

	@Test
	public void get_file_path() {
		String filePath = FILE_LOCATION_PREFIX + LEVEL + TunableParameters.FILE_NAME_SUFFIX;
		assertThat(levelCreator.getFilePath(LEVEL), equalTo(filePath));
	}

	@Test
	public void io_exception_on_close() throws Exception {
		IOException exception = Mockito.mock(IOException.class);
		BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
		Mockito.when(readerWrapper.createBufferedReader(levelCreator.getFilePath(LEVEL))).thenReturn(bufferedReader);
		Mockito.doThrow(exception).when(bufferedReader).close();
		levelCreator.createLevel(gameEngine, LEVEL);
		Mockito.verify(gameEngine).setExit(true);
	}
}
