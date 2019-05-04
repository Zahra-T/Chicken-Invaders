package game.enemy;

import java.awt.Graphics2D;

import game.Animatable;
import game.engine.weapon.Weapon;

public interface Enemy extends Animatable{

	void decreasePower(Weapon tir);

	int getPower();

	int getX();

	int getY();

	int getWidth();
	
	int getHeight();


}
