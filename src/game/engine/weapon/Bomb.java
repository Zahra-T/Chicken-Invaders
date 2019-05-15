package game.engine.weapon;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Animatable;
import game.swing.GamePictures;
import game.swing.MainPanel;

public class Bomb implements Animatable{
	private transient BufferedImage bufferedImage;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private transient Point center;
	public Bomb() throws IOException, InterruptedException
	{
		initialize();
	}

	private void initialize() throws IOException, InterruptedException
	{
		//		bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\BombItem.png"));
		bufferedImage = (BufferedImage) GamePictures.getInstance().get("bombItem");
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		this.x = (int) b.getX();
		this.y = (int) b.getY();

		center = new Point(MainPanel.getPanel().getWidth()/2-50, MainPanel.getPanel().getHeight()/2-50);
		if(vx == 0 && vy == 0) { //agar ghablan sorat dehi shode dobare nashe.
			setVelocity();
		}

	}
	private void setVelocity()
	{

		if(x == center.getX())
		{
			vy = 20;
		}
		else if(x == center.getY())
		{
			vx = 20;
		}
		else
		{
			//			double m = (y - center.getY())/(x - center.getX());
			vx = (center.getX()-x)/50;
			vy = (center.getY()-y)/50;
		}
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.drawImage(bufferedImage, (int)x, (int)y, null);
	}

	@Override
	public void move() {

		x += vx;
		y += vy;



	}

	public double getDistance() //distance from center
	{
		Point p1 = center;
		double distance = Math.sqrt((p1.getX()-this.x)*(p1.getX()-this.x)+(p1.getY()-this.y)*(p1.getY()-this.y));
		return distance;
	}

}
