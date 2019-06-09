package game.enemy.asset;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Location;
import game.swing.GamePictures;

public class Coin implements Asset {
	private transient BufferedImage bufferedImage;
	private int velocity;
	private float probability;
	private Location location;

	public Coin() {
		setImage();
	}
	
	public Coin(Location location) { // bayad barash new location befresti na location.
		this.location = location;
		initialize();
	}
	
	private void initialize() {
		this.velocity = 15;
		this.probability = 0.06f;
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
			bufferedImage = (BufferedImage) GamePictures.getInstance().get("coin");
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
