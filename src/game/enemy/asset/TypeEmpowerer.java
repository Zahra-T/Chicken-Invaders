package game.enemy.asset;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Location;
import game.swing.GamePictures;

public class TypeEmpowerer implements Asset {
	private transient BufferedImage bufferedImage;
	private int velocity;
	private float probability;
	private Location location;
	private String type;
	public TypeEmpowerer() {
		setImage();
	}
	
	public TypeEmpowerer(Location location) {
		this.location = location;
		initialize();
	}
	
	private void initialize() {
		this.velocity = 10;
		this.probability = 0.03f;
		if(Math.random() < 0.33) {
			this.type = "redGift";
		}
		else if(Math.random() < 0.66) {
			this.type = "yellowGift";
		}
		else {
			this.type = "grayGift";
		}
		setImage();
	}
	@Override
	public void move() {
		this.location.y += this.velocity;
	}

	@Override
	public void paint(Graphics g2) {
		g2.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
	}

	@Override
	public void setImage() {
		try {
			
			bufferedImage = (BufferedImage) GamePictures.getInstance().get(type);
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
