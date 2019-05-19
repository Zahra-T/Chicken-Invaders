package game.enemy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import game.Animatable;
import game.Velocity;
import game.engine.weapon.Weapon;

public class Giant implements Enemy, Animatable{
	BufferedImage bufferedImage;
	private Point Location;
	private Velocity velocity;


	@Override
	public void paint(Graphics2D g2) {
		
	}

	@Override
	public void move() {
		
	}

	@Override
	public void decreasePower(Weapon tir) {
		
	}

	@Override
	public int getPower() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public Point getLocation() {
		return null;
	}

}
