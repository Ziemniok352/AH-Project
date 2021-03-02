package data;


public class EnemyA extends Enemy{

	public EnemyA(int tileX, int tileY, Grid grid) {
		super(tileX, tileY, grid);
		this.setTexture("EnemyA");
		this.setSpeed(30);
		this.setHealth(150);
	}

}
