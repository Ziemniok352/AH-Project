package data;

import static additional.Quick.*;

import org.newdawn.slick.opengl.Texture;

public enum Projectiles {
	
	BulletA(QuickLoad("Bullet1"), 25, 1050), //red tower 
	BulletB(QuickLoad("Bullet1"), 105, 1050); //blue tower
	
	
	Texture texture;
	int damage;
	float speed;
	
	Projectiles(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}
