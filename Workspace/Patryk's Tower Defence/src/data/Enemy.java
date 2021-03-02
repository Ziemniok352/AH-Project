package data;
import static additional.Clock.*;
import static additional.Quick.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Enemy implements Entity {
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, startHealth, hiddenHealth;
	private Texture texture, healthBackground, healthForeground, healthBorder;
	private Tile startTile;
	private boolean first = true, alive = true;
	private Grid grid;
	
	private ArrayList<Navigation> checkpoints;
	private int[] directions;
	
	public Enemy(int tileX, int tileY, Grid grid) {
		this.texture = QuickLoad("EnemyB");
		this.healthBackground = QuickLoad("HealthBackground");
		this.healthForeground = QuickLoad("HealthForeground");
		this.healthBorder = QuickLoad("HealthBorder");
		this.startTile = grid.getTile(tileX, tileY);
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.speed = 50;
		this.health = 50;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.checkpoints = new ArrayList<Navigation>();
		this.directions = new int[2];
		//x direction = 0
		this.directions[0] = 0;
		//y direction = 1
		this.directions[1] = 0;
		directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}
	
	public Enemy(Texture texture, Tile startTile, Grid grid, int width,
			int height, float speed, float health) {
		this.texture = texture;
		this.healthBackground = QuickLoad("HealthBackground");
		this.healthForeground = QuickLoad("HealthForeground");
		this.healthBorder = QuickLoad("HealthBorder");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.checkpoints = new ArrayList<Navigation>();
		this.directions = new int[2];
		//x direction = 0
		this.directions[0] = 0;
		//y direction = 1
		this.directions[1] = 0;
		directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}

	public void update() {
		if (first)
			first = false;
		else {
				if (checkpointReached()) {
					if (currentCheckpoint + 1 == checkpoints.size())
						endOfMazeReached();
					else
						currentCheckpoint++;
			} else {
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
				
			}
			
		}
			
	}
	
	private void endOfMazeReached() {
		User.modifyLives(-1);
		die();
	}
	
	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		if (x > t.getX() - 3 &&
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3) {
			
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}
	
	private void populateCheckpointList() {
		checkpoints.add(findNextC(startTile, directions = findNextD(startTile)));
		
		int counter = 0;
		boolean cont = true;
		while(cont){
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			//Checks if next checkpoint.direction exists and will end after 20 checkpoints
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(),
						directions = findNextD(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}
	
	private Navigation findNextC(Tile s, int[] dir) {
		Tile next = null;
		Navigation c = null;
		
		//Boolean to decide if next checkpoint is found
		boolean found = false;
		
		//Integer to increment each loop
		int counter = 1;
		
		while (!found) {
			if (s.getXPlace() + dir[0] * counter == grid.getTilesWide() ||
					s.getYPlace() + dir[1] * counter == grid.getTilesHigh() || 
					s.getType() != 
					grid.getTile(s.getXPlace() + dir[0] * counter, 
							s.getYPlace() + dir[1] * counter).getType()) {
			
				
				found = true;
				//Move counter back by to in order to find tile before new tile 
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, 
						s.getYPlace() + dir[1] * counter);
			}
			
			counter++;
		}
		
		c = new Navigation(next, dir[0], dir[1]);
		return c;
	}
	
	private int[] findNextD(Tile s) {
		int[] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());
		
		if (s.getType() == u.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == r.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == d.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == l.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
		}
		
		
		return dir;
		
	}
	
	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			die();
			User.modifyMoney(15); //amount of money awarded per kill
		}
	}
	
	public void die() {
		alive = false;
	}
	
	public void draw() {
		float healthPercentage = health / startHealth;
		DrawQuadTex(texture, x, y, width, height);
		DrawQuadTex(healthBackground, x, y - 16, width, 8);
		DrawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		DrawQuadTex(healthBorder, x, y - 16, width, 8);
		
	}

	public void reduceHiddenHealth(float amount) {
		hiddenHealth -= amount;
	}
	
	public float getHiddenHealth() {
		return hiddenHealth;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public void setTexture(String textureName) {
		this.texture = QuickLoad(textureName);
	}
	
	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	public Grid getTileGrid() {
		return grid;
	}
	
	public boolean isAlive() {
		return alive;
	}

}

