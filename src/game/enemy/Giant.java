package game.enemy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Logger.Logger;
import game.Animatable;
import game.Location;
import game.Velocity;
import game.enemy.chickenGroup.ChickenGroup;
import game.engine.Game;
import game.engine.rocket.Rocket;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.gamer.Gamer;
import game.swing.GamePictures;

public class Giant implements Enemy{
	BufferedImage bufferedImage;
	private ArrayList<RedBullet> giantTirs;
	private Location location;
	private Velocity velocity;
	private boolean pictureLoaded;
	private int giantLevel;
	private int health;
	private boolean inState;
	private boolean start;
	private transient Logger logger;
	private Gamer gamer;
	public Giant()
	{
		initialize();
	}

	public Giant(int giantLevel) {
		this.logger = logger.getLogger();
		this.giantLevel = giantLevel;

		this.location = new Location(960, -1000);
		this.inState = false;
		this.health = giantLevel * 250;
		giantTirs = new ArrayList();
		
		initialize();

	}
	public void initialize(){
		this.gamer = Game.getGamer();
	}

	@Override
	public void paint(Graphics2D g2) {

		if(!pictureLoaded) {
			loadImage();
			pictureLoaded = true;
		}

		g2.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
	}

	private transient Random r = new Random();
	@Override
	public void move() {
		if(start) {
			if(!inState) {
				this.location.y += 20;
				if(this.location.y >= 300) {

					inState();
				}
			}
			else
			{
				this.setX(r.nextInt(10)+955);
				this.setY(r.nextInt(10)+295);
			}
		}
	}

	private void inState()
	{

		inState = true;
		shelikThread();
	}

	private void shelikThread() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(Giant.this.health > 0) {

					shelik();

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		t.start();
	}

	public void start()
	{
		this.start = true;
	}

	private void shelik() {
		synchronized (giantTirs) {
			int r = 100;
			for (int i = 0; i < 8; i++) {
				if(Math.random() < 0.25) {
					double degree = 45*i / 180.0 * Math.PI;
					giantTirs.add(new RedBullet(getX()-50 ,
							getY() ,
							10 * Math.cos(degree),
							-10 * Math.sin(degree), 1));
				}
			}
		}

	}

	public void killRocket() {
		synchronized(giantTirs) {

			for(int i = 0; i< giantTirs.size(); i++)
			{
				RedBullet tir = giantTirs.get(i);

				synchronized(tir) {
					synchronized(giantTirs) {

						if(doesStrike(tir, gamer.getRocket()))
						{
							
							logger.debug("in destroy rocket");
							gamer.rocketDestroyed();
							giantTirs.remove(tir);
							
						}

					}

				}

			}
		}
	}

	private boolean doesStrike(Weapon tir, Rocket rocket) { 

		Location northWest = new Location( tir.getX(), tir.getY());
		Location northEast = new Location( tir.getX() + tir.getWidth() ,  tir.getY());
		Location southWest = new Location(tir.getX() ,tir.getY() + tir.getHeight());
		Location southEast = new Location(tir.getX() + tir.getWidth(),  tir.getY() + tir.getHeight());

		if(isIn(northWest, rocket))
		{
			return true;
		}
		else if(isIn(northEast, rocket))
		{
			return true;
		}
		else if(isIn(southWest, rocket))
		{
			return true;
		}
		else if(isIn(southEast, rocket))
		{
			return true;
		}


		return false;
	}

	public boolean isIn(Location p, Rocket rocket)
	{

		//		if(p.getX()>= enemy.getX()-enemy.getWidth()/2 && p.getX() <= enemy.getX()+enemy.getWidth()/2
		//				&& p.getY() >= enemy.getY() - enemy.getHeight()/2 && p.getY() <= enemy.getY() + enemy.getHeight()/2)
		//		{0
		//			return true;
		//		}

		if((p.getX()-rocket.getX())*(p.getX()-rocket.getX() )
				+ (p.getY()-rocket.getY())*(p.getY()-rocket.getY())
				<= (rocket.getWidth()/2)*(rocket.getWidth()/2))
		{
			return true;
		}
		return false;
	}


	private void loadImage() {
		try {
			bufferedImage = (BufferedImage) GamePictures.getInstance().get("giant"+giantLevel);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void setX(double x) {
		this.location.setX(x);
	}

	public void setY(double y) {
		this.location.setY(y);
	}

	@Override
	public double getX() {
		return this.location.x;
	}
	@Override
	public double getY() {
		return this.location.y;
	}

	@Override
	public int getWidth() {
		if(bufferedImage == null) {
			loadImage();
		}

		return bufferedImage.getWidth();
	}



	@Override
	public int getHeight() {
		if(bufferedImage == null) {
			loadImage();
		}
		return bufferedImage.getHeight();
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	public ArrayList<RedBullet> getTirs(){
		return this.giantTirs;
	}

	@Override
	public void decreaseHealth(int n) {
		this.health -= n;
	}





}
