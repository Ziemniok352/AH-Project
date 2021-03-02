package data;

import static additional.Quick.*;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import additional.Clock;

public class User {
	
	private Grid grid;
	private Tiles[] types;
	private WaveMain waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, holdingTower;
	private Tower tempTower;
	public static int Money, Lives;
	
	public User(Grid grid, WaveMain waveManager) {
		this.grid = grid;
		this.types = new Tiles[2];
		this.types[0] = Tiles.Blue;
		this.types[1] = Tiles.Yellow;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		Money = 0;
		Lives = 0;
	}
	
	public void setup() {
		Money = 200;
		Lives = 10;
	}
	
	public static boolean modifyMoney(int amount) {
		if (Money + amount >= 0) {
			Money += amount;
			System.out.println(Money);
			return true;
		}
		System.out.println(Money);
		return false;
	}
	
	public static void modifyLives(int amount) {
		Lives += amount;
	}
	
	public void update() {
		
		if (holdingTower) {
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		
		for (Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}
	
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeTower();
		}
		
		if (Lives <= 0) {
			System.out.println("Game Over");
			Clock.multiplier = 0;
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(-0.2f);
			}
		}
	}

	private void placeTower() {
		Tile currentTile = getMouseTile();
		if (holdingTower)
			if (!currentTile.getOccupied() && modifyMoney(-tempTower.getCost())) {
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
	}
	
	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}
	
	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
}

   
