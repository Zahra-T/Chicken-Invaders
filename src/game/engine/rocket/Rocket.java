package game.engine.rocket;

import javax.imageio.ImageIO;

import Logger.Logger;
import game.Animatable;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.gamer.Gamer;
import game.gamer.Shelik;
import game.swing.GamePictures;
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
//	private Gamer gamer;
	private int x;
	private int y;
	private transient int width;
	private transient int height;
	private transient BufferedImage bufferedImage;
	private boolean moving;
	private Integer decreaseCoolDown = 0;
	//	private Integer lockMillies = 0;
	//	private TemperaturePanel temperature;
	private Integer temperature = 0;
	private int maxTemp = 100;
	private ArrayList rocketTirs = new ArrayList<RedBullet>();
	private ArrayList bombs = new ArrayList<Bomb>();
	//	here
	//	private boolean isOverHeat = false; 
	private Shelik shelik;
	transient Logger logger = Logger.getLogger();
	
	public Rocket()
	{
		initialize();
	}
	public Rocket(int x, int y) {
//		this.gamer = gamer;
		this.x = x;
		this.y = y;
		this.moving = true;
		
		initialize();

	}

	private void initialize()
	{
		shelik = new Shelik();
		
		
//		try {
//			bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\Rocket.png"));
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
		try {
			bufferedImage = (BufferedImage) GamePictures.getInstance().get("rocket");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.width = bufferedImage.getWidth();
		this.height = bufferedImage.getHeight();

		try {
			(new TemperatureCoolDown(this)).start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shelik()
	{
		synchronized (rocketTirs) {
			int r = 100;
//			rocketTirs.add(new RedBullet(getX() - 90, getY() -getHeight()/2-50,0,-10,1));
			for (int i = 0; i < 5; i++) {
				double degree = (68 + i * 10) / 180.0 * Math.PI;
				rocketTirs.add(new RedBullet(getX()-30 + r * Math.cos(degree),
						getY()  -r * Math.sin(degree),
						10 * Math.cos(degree),
						-10 * Math.sin(degree), 1));
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
//		logger.debug("X : "+x);
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
		return this.bufferedImage.getWidth();
	}

	public int getHeight()
	{
		return this.bufferedImage.getHeight();
	}
	@Override
	public void paint(Graphics2D g2) {
		//        g2.setColor(new Color(135, 109, 79));
		//        g2.fillRect(x - 50, y - 25, 100, 50);
		g2.drawImage(bufferedImage,(int)( x - getWidth()/2), (int)(y - getHeight()/2 ), null);
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
		return (ArrayList<Weapon>)rocketTirs;
	}

	public void setTirs(ArrayList<Weapon> tirs) {
		this.rocketTirs = tirs;
	}

	public ArrayList<Bomb> getBombs() {
		return (ArrayList<Bomb>)bombs;
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
