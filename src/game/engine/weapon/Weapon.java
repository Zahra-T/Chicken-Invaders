package game.engine.weapon;

import game.Animatable;

public interface Weapon extends Animatable{
//	int power = 20;
	
    public double getX();

    public double getY();
    
	public int getWidth();

	public int getHeight();

	public int getPower();
}
