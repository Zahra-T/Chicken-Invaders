package game.engine.rocket;

import javax.imageio.ImageIO;

import game.Animatable;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.gamer.Gamer;
import game.gamer.Shelik;
import game.swing.MainFrame;
import game.swing.SoundPlayer;
import game.swing.TemperaturePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rocket implements Animatable {
	private Gamer gamer;
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage bufferedImage;
	private boolean moving;
	private Integer decreaseCoolDown = 0;
	//	private Integer lockMillies = 0;
	//	private TemperaturePanel temperature;
	private Integer temperature = 0;
	private int maxTemp = 100;
	private ArrayList<Weapon> tirs = new ArrayList<>();
	private ArrayList<Bomb> bombs = new ArrayList();
	//	here
	//	private boolean isOverHeat = false; 
	private Shelik shelik;

	public Rocket(int x, int y, Gamer gamer) {
		this.gamer = gamer;
		this.x = x;
		this.y = y;
		initialize();

	}

	private void initialize()
	{
		shelik = new Shelik();
		
		
		try {
			bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\Rocket.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.width = bufferedImage.getWidth();
		this.height = bufferedImage.getHeight();

		try {
			(new TemperatureCoolDown(this)).start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.moving = true;
	}

	public void shelik()
	{
		synchronized (tirs) {
			int r = 100;
			for (int i = 0; i < 5; i++) {
				double degree = (68 + i * 10) / 180.0 * Math.PI;
				tirs.add(new RedBullet(getX() - 90 + r * Math.cos(degree),
						getY() + - getHeight()/2 -r * Math.sin(degree),
						10 * Math.cos(degree),
						-10 * Math.sin(degree), 20, gamer.getEnemies()));
			}
			playGunSound();
			(new AttackCoolDown(this)).start();
			increaseTemp(8);
			System.out.println("temperature:"+temperature);
		}
		
	}

	public void playGunSound()
	{
		File sound = new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\gun.wav");
		SoundPlayer soundPlayer = new SoundPlayer(sound);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	@Override
	public void paint(Graphics2D g2) {
		//        g2.setColor(new Color(135, 109, 79));
		//        g2.fillRect(x - 50, y - 25, 100, 50);
		g2.drawImage(bufferedImage, x - getWidth()/2, y - getHeight()/2 , null);
	}

	@Override
	public void move() {

	}

	public boolean isMoving()
	{
		return moving;
	}

	public void setMoving(boolean b)
	{
		this.moving = b;
	}

	public void setDecreaseCoolDown(int n)
	{
		synchronized(decreaseCoolDown)
		{
			decreaseCoolDown = n;
		}

	}

	public int getDecreaseCoolDown()
	{
		synchronized(decreaseCoolDown)
		{
			return decreaseCoolDown;
		}
	}

	public void increaseTemp(int d)
	{
		synchronized(temperature)
		{
			if(temperature < maxTemp)
			{
				if(temperature + d > maxTemp)
				{
					temperature = maxTemp;
					//Over Heat
					(new LockShelik(this)).start();

				}
				else
				{
					temperature += d;
				}
			}
		}

	}

	public void decreaseTemp(int d)
	{
		synchronized(temperature)
		{
			if(temperature > 0)
			{
				if(temperature - d < 0)
				{
					temperature = 0;
				}
				else
				{
					temperature -= d;
				}
				MainFrame.getFrame().repaint();
			}
		}
	}

	public ArrayList<Weapon> getTirs() {
		return tirs;
	}

	public void setTirs(ArrayList<Weapon> tirs) {
		this.tirs = tirs;
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public Shelik getShelik() {
		return shelik;
	}

	public boolean isShelik()
	{
		return shelik.isMousePress() && !shelik.isLock();
	}

	public int getTemperature() {
		synchronized(this.temperature)
		{
			return temperature;
		}
	}

	public void setTemperature(int temperature) {
		synchronized(this.temperature)
		{
			this.temperature = temperature;
		}
	}

}
