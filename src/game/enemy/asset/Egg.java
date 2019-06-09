package game.enemy.asset;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Logger.Logger;
import game.Location;
import game.swing.GamePictures;

public class Egg implements Asset{
	private transient BufferedImage bufferedImage;
	private int chickenLevel;
	private int velocity;
	private float probability;
	private Location location;
	private transient Logger logger = Logger.getLogger();
	public Egg() {
		setImage();
	}
	
	public Egg(int chickenLevel, Location location) {
		this.chickenLevel = chickenLevel;
		this.location = location;
		initialize();
	}
	
	private void initialize() {
		switch (chickenLevel) {
		case 1:{
			probability = 0.05f; 
			velocity = 20;
			break;
		}
		case 2:{
			probability = 0.05f;
			velocity = 20;
			break;
		}
		case 3:{
			probability = 0.1f;
			velocity = 40;
			break;
		}
		case 4:{
			probability = 0.2f;
			velocity = 40;
			break;
		}
		}
		
		setImage();
		
	}

	@Override
	public void move() {
		this.location.y += velocity;
	}

	@Override
	public void paint(Graphics g2) {
		g2.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
	}
	
	@Override
	public void setImage() {
		try {
			bufferedImage = (BufferedImage) GamePictures.getInstance().get("egg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getWidth() {
		return bufferedImage.getWidth();
	}
	@Override
	public int getHeight() {
		return bufferedImage.getHeight();
	}

}
