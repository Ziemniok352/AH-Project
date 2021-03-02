package data;

public class EnemyB extends Enemy{

	public EnemyB(int tileX, int tileY, Grid grid) {
		super(tileX, tileY, grid);
		this.setTexture("EnemyB");
		this.setSpeed(25);
		this.setHealth(200);
	}

}
