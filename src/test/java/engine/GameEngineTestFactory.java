package engine;

import tiles.TileType;

public class GameEngineTestFactory {

    public static void initializeSimpleGameEngine(GameEngine gameEngine) {
        gameEngine.setLevelVerticalDimension(2);
        gameEngine.setLevelHorizontalDimension(2);
        gameEngine.addTile(0, 0, TileType.NOT_PASSABLE);
        gameEngine.addTile(0, 1, TileType.PASSABLE);
        gameEngine.addTile(1, 1, TileType.PASSABLE);
        gameEngine.addTile(1, 0, TileType.NOT_PASSABLE);
    }
}
