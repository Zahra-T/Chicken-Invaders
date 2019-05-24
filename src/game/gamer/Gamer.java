package game.gamer;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;

import game.enemy.Enemy;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.chickenGroup.CircularGroup;
import game.enemy.chickenGroup.RectangularGroup;
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


	private String userName;
	private transient UserLabel userLabel;
	private Rocket rocket;
	//	private transient ArrayList<Weapon> tirs;
	//	private transient ArrayList<Bomb> bombs;
	//	private transient ArrayList enemies = new ArrayList<>();
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

	public void kill()
	{

		synchronized(rocket.getTirs()) {
			for(Weapon tir : rocket.getTirs())
			{
				synchronized(chickenGroups) {
					for(ChickenGroup c :chickenGroups)
					{
						ArrayList<Chicken> chickens = c.getGroup();
						for(Enemy enemy : chickens) {
							if(doesStrike(tir, enemy))
							{
								enemy.decreasePower(tir);
								if(enemy.getPower()<=0)
								{
									chickens.remove(enemy);
								}
								rocket.getTirs().remove(tir);
							}
						}
					}
				}
			}

		}
	}

	public boolean doesStrike(Weapon tir, Enemy enemy)
	{  
		Point northWest = new Point((int) tir.getX(), (int) tir.getY());
		Point northEast = new Point((int) tir.getX() + tir.getWidth() , (int) tir.getY());
		Point southWest = new Point((int)tir.getX() , (int)tir.getY() + tir.getHeight());
		Point southEast = new Point((int)tir.getX() + tir.getWidth(), (int) tir.getY() + tir.getHeight());

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

	public boolean isIn(Point p, Enemy enemy)
	{

		//		if(p.getX()>= enemy.getX()-enemy.getWidth()/2 && p.getX() <= enemy.getX()+enemy.getWidth()/2
		//				&& p.getY() >= enemy.getY() - enemy.getHeight()/2 && p.getY() <= enemy.getY() + enemy.getHeight()/2)
		//		{0
		//			return true;
		//		}

		if((p.getX()-enemy.getLocation().x)*(p.getX()-enemy.getLocation().y )
				+ (p.getY()-enemy.getLocation().y)*(p.getY()-enemy.getLocation().y)
				<= (enemy.getWidth()/5)*(enemy.getWidth()/4))
		{
			return true;
		}
		return false;
	}



	public void setEnemy()
	{
		//		Chicken redChicken = new Chicken(new Point(-50, 300),new Velocity(10, 0), 1);
//		chickenGroups.add(new RectangularGroup(5, 8, 2));
//		CircularGroup c = 
		
		chickenGroups.add(new CircularGroup(10, 1));
//		c.startThreads();
		
		//		enemies.add(redChicken);
	}


	public Rocket getRocket() {
		return rocket;
	}

	//	public ArrayList<Weapon> getTirs() {
	//		return tirs;
	//	}
	//
	//	public ArrayList<Bomb> getBombs() {
	//		return bombs;
	//	}

	//	public ArrayList<Enemy> getEnemies() {
	//		return (ArrayList<Enemy>) enemies;
	//	}

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

	//	public void setTirs(ArrayList<Weapon> tirs) {
	//		this.tirs = tirs;
	//	}
	//
	//	public void setBombs(ArrayList<Bomb> bombs) {
	//		this.bombs = bombs;
	//	}

	//	public void setEnemies(ArrayList enemies) {
	//		this.enemies = enemies;
	//	}

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

	public void setChickenGroups(ArrayList<ChickenGroup> chickenGroups) {
		this.chickenGroups = chickenGroups;
	}
	
	public void comeEnemies()
	{
		for(ChickenGroup c : chickenGroups)
		{
			c.startThreads();
		}
	}





	//	public boolean isShelik() {
	//		return shelik;
	//	}
	//
	//	public void setShelik(boolean shelik) {
	//		this.shelik = shelik;
	//	}




}
