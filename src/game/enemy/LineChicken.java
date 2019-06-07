package game.enemy;

import java.awt.Graphics2D;

public class LineChicken {
	private double x;
	private double y;
	final private double speed = 2;
	final private double radius = 24;
	
	public boolean makeEgg()
	{
		return Math.random() < 0.1;
	}
	
	public void paint(Graphics2D g)
	{
		g.drawOval((int)(x- radius/2),  (int)(y-radius/2), (int)(2*radius), (int)(2*radius));
	}
	
	public void move()
	{
		x += speed;
	}

}
