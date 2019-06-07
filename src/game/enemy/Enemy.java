package game.enemy;

import java.awt.Graphics2D;
import java.awt.Point;

import game.Animatable;
import game.Location;
import game.engine.weapon.Weapon;

public interface Enemy extends Animatable{

	void decreaseHealth(int n);

	int getHealth();
	
	int getWidth();
	
	int getHeight();
	
	double getX();
	
	double getY();

	Location getLocation();




}
