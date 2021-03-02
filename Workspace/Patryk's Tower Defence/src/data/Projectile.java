package data;

import static additional.Clock.*;
import static additional.Quick.*;

import org.newdawn.slick.opengl.Texture;

public abstract class Projectile implements Entity {
	
	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;
	
	public Projectile(Projectiles type, Enemy target, float x, float y, int width, int height) {
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		targeting();
	}
	
	 
	private void targeting() {
		
		float xDistanceFromTarget = target.getX()-x + TILE_SIZE / 2;
		float yDistanceFromTarget = target.getY()-y + TILE_SIZE / 2;
		float totalDistanceFromTarget = Math.abs(xDistanceFromTarget) + Math.abs(yDistanceFromTarget);
		xVelocity = xDistanceFromTarget/totalDistanceFromTarget;
		yVelocity = yDistanceFromTarget/totalDistanceFromTarget;
	}
	
	public void damage() {
		target.damage(damage);	
		alive = false;
	}
	
	public void update() {
		if (alive) {
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			if (CheckCollision(x, y, width, height, target.getX(),
					target.getY(), target.getWidth(), target.getHeight())) 
				damage();
			draw();
		}
	}
	
	public void draw() {
		DrawQuadTex(texture, x, y, 32, 32);
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
		
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Enemy getTarget() {
		return target;
	}

	public void setAlive(boolean status) {
		alive = status;
	}
}
