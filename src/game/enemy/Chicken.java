package game.enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Logger.Logger;
import game.Animatable;
import game.Location;
import game.Velocity;
import game.engine.weapon.Weapon;
import game.swing.GamePictures;

public class Chicken implements Enemy{
	private transient BufferedImage bufferedImage;
	private Location location;
	private Location beginning;
	private Location destination;
	private int step;
	private Velocity velocity;
	private double normalVelocity;
	private int chickenLevel;
	private int power;
	private Double angle;
	private transient Random random;
	transient Logger logger = Logger.getLogger();
	boolean b = false;

	public Chicken()
	{
		initialize();
	}
	public Chicken (Location beginning, int chickenLevel)
	{
		this.location = new Location(beginning.x, beginning.y);
		this.beginning = new Location(beginning.x, beginning.y);
		random = new Random();
		this.destination = new Location(random.nextInt(1920), random.nextInt(1030));
		this.chickenLevel = chickenLevel;
		this.step = 50;
		this.remainingStep = step;
		if(velocity == null)
		{
			velocity = new Velocity(10, 10); //it may cause problems.
		}
		setNewStep(normalVelocity);
		initialize();
	}

	public Chicken(Location location, Velocity velocity,int chickenLevel) // velocity ro bardar badan.
	{
		this.location = location;
		this.velocity = velocity;
		this.chickenLevel = chickenLevel;
		initialize();
	}

	public Chicken(Location center, double angle, double radius, int chickenLevel)
	{
		this.angle = angle;
		this.chickenLevel = chickenLevel;
		location = new Location(center.getX() + radius*Math.sin(angle), center.getY() + radius*Math.cos(angle));
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
		//		try {
		//			bufferedImage = (BufferedImage) GamePictures.getInstance().get("chicken"+chickenLevel);
		//		} catch (IOException ex) {
		//			ex.printStackTrace();
		//		}
		this.normalVelocity = 20;
		random = new Random();
		//		this.destination = new Location(random.nextInt(1920), random.nextInt(1030));
		this.lastThrowEgg = System.currentTimeMillis();


	}

	public void paint(Graphics2D g2) {
		if(bufferedImage == null) {
			setImage();
		}
		g2.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
	}

	public void move() {

	}

	public void move(Velocity v)
	{
		synchronized(location)
		{
			//			location = new Location((center.getX() + radius*Math.sin(angle)), (center.getY() + radius*Math.cos(angle)));
			this.location.x += v.vx;
			this.location.y += v.vy;
		}
	}

	private transient long lastThrowEgg;
	public void throwEgg() {  //enum
		if(System.currentTimeMillis() - lastThrowEgg >= 1) {
			
		}
	}
	//	public void move




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



	public void decreaseHealth(int n) {
		this.power -= n;
	}

	@Override
	public double getX() {
		return this.location.getX();
	}

	@Override 
	public double getY() {
		return this.location.getY();
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

	public int getHealth() {
		// TODO Auto-generated method stub
		return this.power;
	}

	public Velocity getVelocity()
	{
		return velocity;
	}

	public void rotationalMotion(double angularFrequency, double radius, Location center)
	{
		synchronized(center) {
			synchronized(location) {

				angle = angle + angularFrequency;
				location = new Location((center.getX() + radius*Math.sin(angle)), (center.getY() + radius*Math.cos(angle)));

			}
		}
	}

	public void radialMotion(double radius, Location center)
	{
		location = new Location((center.getX() + radius*Math.sin(angle)), (center.getY() + radius*Math.cos(angle)));
	}

	public void suicideMotion(int rocketX, int rocketY)
	{
		setSuicideDestination(rocketX, rocketY);
		setNewStep(2*normalVelocity);
	}
	public void setSuicideDestination(int x, int y)
	{
		synchronized(beginning) {
			synchronized(destination) {
				beginning = destination;
				destination = new Location(x, y);
			}
		}
	}

	int remainingStep;
	public void translationalMotion()
	{
		location.x += velocity.vx;
		location.y += velocity.vy;
		remainingStep--;
		if(remainingStep <= 0)
		{
			setNewDestination();
			setNewStep(normalVelocity);
			remainingStep = step;

		}
	}

	private void setNewDestination()
	{
		synchronized(beginning) {
			synchronized(destination) {
				beginning = destination;
				destination = new Location(random.nextInt(1900)+10, random.nextInt(1000)+10);


			}
		}
	}

	private void setNewStep(double v)
	{
		synchronized(velocity) {

			double d = Math.sqrt((destination.x - beginning.x)*(destination.x - beginning.x)
					+(destination.y - beginning.y)*(destination.y - beginning.y));
			double cosx = (destination.x - beginning.x)/d;
			double sinx = (destination.y - beginning.y)/d;
			velocity = new Velocity(v * cosx, v * sinx);
			step = (int) (d/v);

		}

	}

	@Override
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
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
		if(bufferedImage == null) {
			setImage();
		}
		return bufferedImage.getWidth();
	}
	@Override
	public int getHeight() {
		if(bufferedImage == null) {
			setImage();
		}
		return bufferedImage.getHeight();
	}
	public void setAngle(double d) {
		this.angle = d;

	}
	public Double getAngle() {
		return this.angle;
	}

	private void setImage() {
		try {

			bufferedImage = (BufferedImage) GamePictures.getInstance().get("chicken"+chickenLevel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
