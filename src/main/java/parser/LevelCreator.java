package parser;

import engine.GameEngine;

public interface LevelCreator {
    void createLevel(final GameEngine gameEngine, final int level);
}
