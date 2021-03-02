package data;

import static additional.Quick.BeginSession;

import org.lwjgl.opengl.Display;

import additional.Clock;
import additional.Mode;

public class Boot {
	
	public Boot() {
		
		BeginSession();
		
		while(!Display.isCloseRequested()) {
			Clock.update();
			Mode.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Boot();
	}
}
