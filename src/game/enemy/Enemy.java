package game.enemy;

import java.awt.Graphics2D;
import java.awt.Point;

import game.Animatable;
import game.engine.weapon.Weapon;

public interface Enemy extends Animatable{

	void decreasePower(Weapon tir);

	int getPower();
	
	int getWidth();
	
	int getHeight();

	Point getLocation();




}
