package game.enemy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Animatable;
import game.Velocity;
import game.engine.weapon.Weapon;
import game.swing.GamePictures;

public class Chicken implements Enemy{
	private transient BufferedImage bufferedImage;
	private Point location;
	private Velocity velocity;
	private int chickenLevel;
	private int power;
	private Double angle;
	//	private String groupType;

	boolean b =false;
	public Chicken()
	{
		initialize();
	}
	public Chicken(Point location, Velocity velocity,int chickenLevel) // velocity ro bardar badan.
	{
		this.location = location;
		this.velocity = velocity;
		this.chickenLevel = chickenLevel;
		initialize();
		//		this.groupType = groupType;

	}
	public Chicken(Point center, double angle, double radius, int chickenLevel)
	{
		this.angle = angle;
		this.chickenLevel = chickenLevel;
		location = new Point((int)(center.getX() + radius*Math.sin(angle)), (int)(center.getY() + radius*Math.cos(angle)));
		initialize();
		
	}

	private void initialize()
	{
		switch(chickenLevel)
		{
		case 1:
		{
			power = 2; break;
		}
		case 2:
		{
			power = 3;  break;
		}
		case 3:
		{
			power = 5; break;
		}
		case 4:
		{
			power = 8; break;
		}
		}
		try {
			bufferedImage = (BufferedImage) GamePictures.getInstance().get("chicken"+chickenLevel);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void paint(Graphics2D g2) {
		// TODO Auto-generated method stub
		System.out.println("paint");
		g2.drawImage(bufferedImage, location.x - bufferedImage.getWidth()/2, location.y - bufferedImage.getHeight()/2 , null);
	}

	public void move() {
		//		synchronized(location)
		//		{
		//			synchronized(velocity)
		//			{
		//			location.x += velocity.vx;
		//			location.y += velocity.vy;
		//			}
		//		}
		//		handleVelocity(groupType);
		//		if(x >= 1700)
		//		{
		//			vx *= -1;
		//			b = true;
		//
		//		}
		//		if(x <= 200 && b)
		//		{
		//			vx *= -1;
		//		}

	}
	public void move(Velocity v)
	{
		synchronized(location)
		{
			this.location.x += v.vx;
			this.location.y += v.vy;
		}
	}




	//	private void handleVelocity(String groupType)
	//	{
	//		switch(groupType)
	//		{
	//		case "rectangular":
	//		{
	//			
	//			break;
	//		}
	//		case "circular":
	//		{
	//			
	//			break;
	//		}
	//		case "rotary":
	//		{
	//			
	//			break;
	//		}
	//		case "suicide":
	//		{
	//			
	//			break;
	//		}
	//		
	//		}
	//		
	//	}
	//	
	//	public void rotationalMotion()
	//	{
	//		
	//	}
	//	
	//	public void translationalMotion()
	//	{
	//		
	//	}



	public void decreasePower(Weapon tir) {
		this.power -= tir.getPower();
	}


	//	public int getWidth() {
	//		return width;
	//	}
	//	public void setWidth(int width) {
	//		this.width = width;
	//	}
	//	public int getHeight() {
	//		return height;
	//	}
	//	public void setHeight(int height) {
	//		this.height = height;
	//	}

	public int getPower() {
		// TODO Auto-generated method stub
		return this.power;
	}

	public Velocity getVelocity()
	{
		return velocity;

	}

	public void rotationalMotion(double angularFrequency, double radius, Point center)
	{
//		synchronized(angle) {
			synchronized(center) {
				synchronized(location) {

					angle = angle + angularFrequency;
					location = new Point((int)(center.getX() + radius*Math.sin(angle)), (int)(center.getY() + radius*Math.cos(angle)));

				}
			}
//		}

	}


	@Override
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public int getChickenLevel() {
		return chickenLevel;
	}
	public void setChickenLevel(int chickenLevel) {
		this.chickenLevel = chickenLevel;
	}
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public void setPower(int power) {
		this.power = power;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAngle(double d) {
		// TODO Auto-generated method stub
		this.angle = d;
		
	}
	public Double getAngle() {
		// TODO Auto-generated method stub
		return this.angle;
	}


}
