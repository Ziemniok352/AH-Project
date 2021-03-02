package data;

import userInterface.UI;

import static additional.Quick.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import additional.Mode;
import additional.Mode.GameState;

public class MainMenu {
	
	private Texture background;
	private UI menuUI;
	
	public MainMenu() {
		background = QuickLoad("menu");
		menuUI = new UI();
		menuUI.addButton("Play", "ButtonPlay", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Quit", "ButtonQuit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
	}

	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Play"))
				Mode.setState(GameState.GAME);
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}
	
	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
		updateButtons();
	}
}
