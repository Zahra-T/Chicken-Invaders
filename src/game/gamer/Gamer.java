package game.gamer;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;

import Logger.Logger;
import game.enemy.Enemy;
import game.enemy.Giant;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.chickenGroup.CircularGroup;
import game.enemy.chickenGroup.RectangularGroup;
import game.enemy.chickenGroup.RotationalGroup;
import game.enemy.chickenGroup.SuicideGroup;
import game.Location;
import game.Velocity;
import game.enemy.Chicken;
import game.engine.Game;
import game.engine.rocket.AttackCoolDown;
import game.engine.rocket.Rocket;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.swing.MainPanel;
import game.swing.SoundPlayer;
import game.swing.UserLabel;

public class Gamer{

	transient Logger logger = Logger.getLogger();
	private String userName;
	private transient UserLabel userLabel;
	private Rocket rocket;
	//	private transient ArrayList<Weapon> tirs;
	//	private transient ArrayList<Bomb> bombs;
	//	private transient ArrayList enemies = new ArrayList<>();
	private Giant giant;
	private ArrayList <ChickenGroup> chickenGroups ;
	private transient boolean isChoosed;

	private int wave = 1;
	private int level = 1;
	private int heart = 5;
	private int bomb = 3;
	private int fireLight = 0;
	private int drumStick = 0;


	//	private boolean shelik;
	//	private boolean lockShelik = false;
	public Gamer() throws IOException
	{
		initialize();
	}

	public Gamer(String userName) throws IOException
	{
		this.userName = userName;
		initialize();
	}

	private void initialize() throws IOException
	{
		chickenGroups = new ArrayList();
		rocket = new Rocket(960 - 50, 515 - 200);
		//		this.tirs = rocket.getTirs();
		//		this.bombs = rocket.getBombs();
		//		userLabel = new UserLabel(this);
		isChoosed = false;
		setUserLabel();
		setEnemy();
	}
	public void setUserLabel()
	{
		userLabel = new UserLabel(this);
	}

	public void killEnemy()
	{
		synchronized(rocket.getTirs()) {
			ArrayList<Weapon> tirs = rocket.getTirs();

			for(int i = 0; i< tirs.size(); i++)
			{
				Weapon tir = tirs.get(i);

				synchronized(tir) {
					synchronized(chickenGroups) {
						for(int k = 0; k<chickenGroups.size(); k++)
						{
							ChickenGroup c = chickenGroups.get(k);

							ArrayList<Chicken> chickens = c.getGroup();
							synchronized(chickens) {

								for(int j = 0; j<chickens.size(); j++) {
									Chicken chicken = chickens.get(j);
									synchronized(chicken) {
										if(doesStrike(tir, chicken))
										{
											chicken.decreaseHealth(tir.getPower());
											if(chicken.getHealth()<=0)
											{
												c.addAssets(chicken.getChickenLevel(),new Location(chicken.getX(), chicken.getY()));
												c.remove(chicken);
												
											}
											//											synchronized(rocket.getTirs()) {
											rocket.getTirs().remove(tir);
											//											}
										}
									}
								}
							}

						}
					}

					if(giant != null && doesStrikeGiant(tir, giant))
					{
						giant.decreaseHealth(tir.getPower());
					}
				}
			}
		}

	}

	//	public void 
	private boolean doesStrikeGiant(Weapon tir, Enemy enemy) {
		Location northWest = new Location( tir.getX(), tir.getY());
		Location northEast = new Location( tir.getX() + tir.getWidth() ,  tir.getY());

		if(isInGiant(northWest, enemy, 110, 143)) return true;
		if(isInGiant(northEast, enemy, 110, 143)) return true;
		return false;
	}


	static boolean isInGiant(Location p, Enemy enemy, int a, int b) 
	{ 
		double h = enemy.getX(), k = enemy.getY();
		double x = p.getX(), y = p.getY();
		// checking the equation of 
		// ellipse with the given point 
		int g = ((int)Math.pow((x - h), 2) / (int)Math.pow(a, 2)) 
				+ ((int)Math.pow((y - k), 2) / (int)Math.pow(b, 2)); 

		return g<=1; 
	} 

	private boolean doesStrike(Weapon tir, Enemy enemy) { 

		Location northWest = new Location( tir.getX(), tir.getY());
		Location northEast = new Location( tir.getX() + tir.getWidth() ,  tir.getY());
		Location southWest = new Location(tir.getX() ,tir.getY() + tir.getHeight());
		Location southEast = new Location(tir.getX() + tir.getWidth(),  tir.getY() + tir.getHeight());

		if(isIn(northWest, enemy))
		{
			return true;
		}
		else if(isIn(northEast, enemy))
		{
			return true;
		}
		else if(isIn(southWest, enemy))
		{
			return true;
		}
		else if(isIn(southEast, enemy))
		{
			return true;
		}


		return false;
	}

	public boolean isIn(Location p, Enemy enemy)
	{

		//		if(p.getX()>= enemy.getX()-enemy.getWidth()/2 && p.getX() <= enemy.getX()+enemy.getWidth()/2
		//				&& p.getY() >= enemy.getY() - enemy.getHeight()/2 && p.getY() <= enemy.getY() + enemy.getHeight()/2)
		//		{0
		//			return true;
		//		}

		if((p.getX()-enemy.getLocation().x)*(p.getX()-enemy.getLocation().x )
				+ (p.getY()-enemy.getLocation().y)*(p.getY()-enemy.getLocation().y)
				<= (enemy.getWidth()/2)*(enemy.getWidth()/2))
		{
			return true;
		}
		return false;
	}


	public void setEnemy()
	{
		chickenGroups.add(new RectangularGroup(1, 8, 1));
	}


	public Rocket getRocket() {
		return rocket;
	}


	public String getUserName() {
		return userName;
	}

	public UserLabel getUserLabel() {
		return userLabel;
	}

	public void isChoosed(boolean b)
	{
		this.isChoosed = b;
		if(b)
		{
			this.getUserLabel().setBorder(BorderFactory.createLineBorder(Color.white));
		}
		else
		{
			this.getUserLabel().setBorder(null);
		}
	}

	public boolean isChoosed()
	{
		return this.isChoosed;
	}
	public void setNew()
	{

		rocket.setTirs(new ArrayList());
		rocket.setBombs(new ArrayList());
		//		this.enemies = new ArrayList();
		this.level = 1;

		//TODO setFirstEnemies
		//TODO setFirstLevel
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getFireLight() {
		return fireLight;
	}

	public void setFireLight(int fireLight) {
		this.fireLight = fireLight;
	}

	public int getDrumStick() {
		return drumStick;
	}

	public void setDrumStick(int drumStick) {
		this.drumStick = drumStick;
	}

	public void increaseHeart(int n)
	{
		heart += n;
	}

	public void increaseBomb(int n)
	{
		bomb += n;
	}

	public void increaseFireLight(int n)
	{
		fireLight += n;
	}

	public void increaseDrumStick(int n)
	{
		drumStick += n;
	}

	public void decreaseHeart()
	{
		heart -= 1;
	}

	public void decreaseBomb()
	{
		bomb -= 1;
	}

	public void decreaseFireLight(int n)
	{
		fireLight -= n;
	}

	public void decreaseDrumStick(int n)
	{
		drumStick -= n;
	}

	public int getWave() {
		return wave;
	}

	public int getLevel() {
		return level;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserLabel(UserLabel userLabel) {
		this.userLabel = userLabel;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}


	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<ChickenGroup> getChickenGroups() {
		return chickenGroups;
	}

	public Giant getGiant() {
		return this.giant;
	}

	public void setChickenGroups(ArrayList<ChickenGroup> chickenGroups) {
		this.chickenGroups = chickenGroups;
	}

	public void setGiant(Giant g) {
		this.giant = g;
	}

	public void comeEnemies()
	{
		synchronized(chickenGroups) {
			for(ChickenGroup c : chickenGroups)
			{
				synchronized(c) {
					c.startThreads();
				}
			}
		}

		if(giant != null) {
			giant.start();
		}
	}

	public void rocketDestroyed() {
		this.decreaseHeart();
		this.setRocket(new Rocket(515, 960));
	}







}
