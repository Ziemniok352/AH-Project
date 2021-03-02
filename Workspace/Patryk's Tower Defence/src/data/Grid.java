package data;

import static additional.Quick.*;

public class Grid {
	
	public Tile[][] map;
	private int tilesWide, tilesHigh;
	
	public Grid() {
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, Tiles.Blue);
			}
		}
	}
	
	public Grid(int[][] newMap) {
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ( newMap[j][i] == 0)
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, Tiles.Blue);
				else
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, Tiles.Yellow);
					
			}
		}
	}
	
	public Tile getTile(int xPlace, int yPlace) {
		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
			return map[xPlace][yPlace];
		else
			return new Tile(0, 0, 0, 0, Tiles.NULL);
	}
	
	public void draw() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j].draw();
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
	
	
}
