package ui;

import java.awt.*;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

public class TilePainter {

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				if(game.checkTileVisited(x,y)){
					if(tileType.equals(TileType.PASSABLE)){
						paintTile(graphics, tileWidth, tileHeight, x, y, TileType.VISITED);
					}else {
						paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
					}

				}else{
					if(tileType.equals(TileType.NOT_PASSABLE)){
						paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
					} else {
						paintTile(graphics, tileWidth, tileHeight, x, y, TileType.PASSABLE);
					}
				}
			}
		}
	}

	void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType) {
		paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
	}

	private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
		handleTile(graphics, tileType);
		graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}

	private void handleTile(Graphics graphics, TileType tileType) {
		graphics.setColor(TileColorMap.get(tileType));
	}

}
