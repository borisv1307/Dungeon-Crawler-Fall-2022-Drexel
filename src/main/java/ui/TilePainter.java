package ui;

import java.awt.*;
import java.util.List;
import engine.GameEngine;
import main.EnemyHandler;
import main.LaserHandler;
import tiles.TileType;
import values.TileColorMap;

public class TilePainter {

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
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

	public void paintLasers(Graphics graphics, List<LaserHandler.Laser> lasers, int laserWidth, int laserHeight, int tileWidth) {
		handleTile(graphics, TileType.LASER);
		for(LaserHandler.Laser laser : lasers){
			int laserX = laser.getX() * laserWidth + (tileWidth/2) - (laserWidth/2);
			int laserY = laser.getY() * laserHeight;
			graphics.fillRect(laserX, laserY, laserWidth, laserHeight);
		}
	}

	public void paintEnemies(Graphics graphics, List<EnemyHandler.Enemy> enemies) {
		handleTile(graphics, TileType.ENEMY);
		for(EnemyHandler.Enemy enemy : enemies){
			graphics.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
		}
	}
}
