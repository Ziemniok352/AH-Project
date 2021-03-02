package data;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;

import userInterface.Button;
import userInterface.UI;

import static additional.Quick.DrawQuadTex;
import static additional.Quick.QuickLoad;

import org.lwjgl.input.Mouse;

public class Game {

	private Grid grid;
	private User player;
	private WaveMain waveManager;
	private UI towerChoiceUI;
	private Texture menuBackground;
	private Enemy[] enemyTypes;
	
	public Game(int[][] map) {
		grid = new Grid(map);
		enemyTypes = new Enemy[2];
		enemyTypes[0] = new EnemyA(1, 0, grid);
		enemyTypes[1] = new EnemyB(1, 0, grid);
		waveManager = new WaveMain(enemyTypes, 3, 3);
		player = new User(grid, waveManager);
		player.setup();
		this.menuBackground = QuickLoad("TowerChoiceMenu");
		setupUI();
	}
	
	private void setupUI() {
		towerChoiceUI = new UI();
		towerChoiceUI.createMenu("TowerChoice", 1184, 100, 192, 960, 2, 0);
		towerChoiceUI.getMenu("TowerChoice").addButton(new Button("CannonB", QuickLoad("TowerBGun"), 0, 0));
		towerChoiceUI.getMenu("TowerChoice").addButton(new Button("CannonRed", QuickLoad("TowerRedGun"), 0, 0));
	}
	
	public void updateUI() {
		towerChoiceUI.draw();
		towerChoiceUI.drawString(1214, 550, "Lives: " + User.Lives, Color.black);
		towerChoiceUI.drawString(1214, 610, "Cash: " + User.Money, Color.black);
		towerChoiceUI.drawString(1214, 670, "Wave: " + waveManager.getWaveNumber(), Color.black);
		towerChoiceUI.drawString(1214, 720, "", Color.white);
		
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (towerChoiceUI.getMenu("TowerChoice").isButtonClicked("CannonB"))
					player.pickTower(new TowerB(Towers.CannonB, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
				if (towerChoiceUI.getMenu("TowerChoice").isButtonClicked("CannonRed"))
					player.pickTower(new TowerRed(Towers.CannonRed, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
			}
		}
	}
	
	public void update() {
		DrawQuadTex(menuBackground, 1152, 0 ,192, 960);
		grid.draw();
		waveManager.update();
		player.update();
		updateUI();
		
	}

}


