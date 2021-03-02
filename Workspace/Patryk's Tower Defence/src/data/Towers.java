package data;

import static additional.Quick.*;

import org.newdawn.slick.opengl.Texture;

public enum Towers {
	//last variable to adjust cost
	CannonB(new Texture[]{QuickLoad("TowerBBase"), QuickLoad("TowerBGun")}, Projectiles.BulletB, 30, 500, 5, 125),
	CannonRed(new Texture[]{QuickLoad("TowerRedBase"), QuickLoad("TowerRedGun")}, Projectiles.BulletA, 30, 500, 1, 150);
	
	Texture[] textures;
	Projectiles projectileType;
	int damage, range, cost;
	float firingSpeed;
	
	Towers(Texture [] textures, Projectiles projectileType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}
