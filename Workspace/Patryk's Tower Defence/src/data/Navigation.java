package data;

public class Navigation {
	
	private Tile tile;
	private int xDirection, yDirection;
	
	public Navigation(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
		
	}

	public Tile getTile() {
		return tile;
	}

	public int getxDirection() {
		return xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}
}
