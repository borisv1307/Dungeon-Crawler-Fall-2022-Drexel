package parser;

import engine.GameEngine;

public interface LevelCreator {
    void createLevel(GameEngine gameEngine, int level);
}
