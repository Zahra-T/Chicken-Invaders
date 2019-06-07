package game.engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import game.Animatable;
import game.PaintLoop;
import game.enemy.Enemy;
import game.enemy.Giant;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.Chicken;
import game.engine.rocket.Rocket;
import game.engine.rocket.ShelikThread;
import game.engine.rocket.TemperatureCoolDown;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.gamer.Gamer;
import game.swing.Achievement;
import game.swing.Background;
import game.swing.MainPanel;
import game.swing.Menu;
import game.swing.SoundPlayer;
import game.swing.UserPanel;
public class Game implements Animatable {
	private static ArrayList<Gamer> gamers = new ArrayList();
	private static Gamer gamer;
	private MainPanel mainPanel;
	private int width;
	private int height;
	//	SoundPlayer soundPlayer;

	private Rocket rocket;
	//	private ArrayList<Weapon> tirs ; //final?
	//	private ArrayList<Bomb> bombs ;
	//	private ArrayList<Enemy> enemies;
	private ArrayList <Weapon>rocketTirs;// = new ArrayList<>();
	//	private ArrayList<RedBullet> giantTirs;
	private ArrayList <Bomb>bombs;// = new ArrayList();
	//	private ArrayList <Enemy> enemies;// = new ArrayList<>();
	private Background[] backgrounds;
	private ArrayList<ChickenGroup> chickenGroups;
	private Giant giant;
	private File levelFile;
	//	private static Game game;

	//	public static Game getGame() throws IOException
	//	{
	//		if(game == null)
	//		{
	//			game = new Game();
	//		}
	//
	//		return game;
	//	}

	public Game(MainPanel mainPanel) throws IOException, InterruptedException {
		this.mainPanel = mainPanel;
		initialize();

	}



	private void initialize() throws IOException, InterruptedException
	{

		rocket = gamer.getRocket();
		rocketTirs = (ArrayList <Weapon>)gamer.getRocket().getTirs();
		bombs = (ArrayList <Bomb>)gamer.getRocket().getBombs();
		chickenGroups = gamer.getChickenGroups();
		giant = gamer.getGiant();


		backgrounds = new Background[2];
		backgrounds[0] = new Background(0, 1920, 1030,0);
		backgrounds[1] = new Background(-1030 + 20, 1920, 1030,1);
		(new ShelikThread(gamer)).start();

	}


	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(24, 37, 100));
		g2.fillRect(0, 0, width, height);

		for(Background bg : backgrounds)
		{
			bg.paint(g2);
		}

		for(int i = 0; i<rocketTirs.size(); i++) {
			Weapon tir = this.rocketTirs.get(i);
			tir.paint(g2);
		}

		if(giant != null) {

			for(int i = 0; i<giant.getTirs().size(); i++) {
				RedBullet tir = this.giant.getTirs().get(i);
				tir.paint(g2);
			}
		}

		rocket.paint(g2);

		for(int i = 0; i<chickenGroups.size(); i++ ) {
			ChickenGroup c = chickenGroups.get(i);
			c.paint(g2);
		}

		for(int i = 0; i<bombs.size(); i++) {
			Bomb bomb = bombs.get(i);
			bomb.paint(g2);
		}

		giant.paint(g2);
	}

	@Override
	public void move() {
		synchronized (backgrounds) {
			for (Background bg : backgrounds) {
				bg.move();
			}
		}
		rocket.move();

		synchronized (rocketTirs) {

			for (Weapon tir : rocketTirs) {

				tir.move();
				//				removeOutTirs(tir);
			}
		}

		if(giant != null) {
			for(int i = 0; i<giant.getTirs().size(); i++) {
				Weapon tir = this.giant.getTirs().get(i);
				tir.move();
			}
		}

		synchronized (bombs) {
			for (Bomb bomb : bombs) {
				bomb.move();
				if(bomb.getDistance() <= 20)
				{
					bombs.remove(bomb);
				}
			}
		}

		synchronized (chickenGroups) {
			for (ChickenGroup c : chickenGroups) {
				c.move();
			}
		}
		if(giant != null) {
			synchronized(giant) {
				giant.move();
			}
		}

	}


	public void removeOutTirs()
	{
		for(int i = 0; i<rocketTirs.size(); i++) {
			Weapon tir = rocketTirs.get(i);
			if(tir.getY()<0  || tir.getY() > 1030)
			{
				rocketTirs.remove(tir);
			}
			if(tir.getX()<0 || tir.getX() > 1920)
			{
				rocketTirs.remove(tir);
			}
		}

		if(giant != null) {
			for(int i = 0; i<giant.getTirs().size(); i++) {
				Weapon tir = giant.getTirs().get(i);
				if(tir.getY()<0  || tir.getY() > 1030)
				{
					giant.getTirs().remove(tir);
				}
				if(tir.getX()<0 || tir.getX() > 1920)
				{
					giant.getTirs().remove(tir);
				}
			}
		}
	}

	public Rocket getRocket() {
		return rocket;
	}



	public void shelik() {
		//		synchronized (tirs) {
		//			int r = 100;
		//			for (int i = 0; i < 5; i++) {
		//				double degree = (68 + i * 10) / 180.0 * Math.PI;
		//				tirs.add(new RedBullet(rocket.getX() - 90 + r * Math.cos(degree),
		//						rocket.getY() + - rocket.getHeight()/2 -r * Math.sin(degree),
		//						10 * Math.cos(degree),
		//						-10 * Math.sin(degree), 20, enemies));
		//			}
		////			new IncreaseThread();
		//			System.out.println("temp:"+IncreaseThread.getDecreaseCoolDown());
		//			playGunSound();
		//		}
		gamer.getRocket().shelik();
	}

	public void increaseThread()
	{

	}


	//	try {
	//		//thread
	//		{
	//			if(decreaseCoolDown < 0) {
	//				decreaseCoolDown = 20;
	//				while(decreaseCoolDown-- > 0) {
	////					attackCoolDown --;
	//					Thread.sleep(1);
	//				}
	//			}
	//			else {
	//				decreaseCoolDown=20;
	//			}	
	//		}
	//	}
	//	catch (Exception ex) {
	//		
	//	}



	public void throwBomb() throws IOException, InterruptedException {
		if(gamer.getBomb()>0) {
			decreaseBomb();

			synchronized (bombs) {
				bombs.add(new Bomb());
			}
		}


	}

	private void decreaseBomb()
	{
		gamer.decreaseBomb();
		mainPanel.getAchievement().decreaseBomb();

	}

	public void kill()
	{
		gamer.killEnemy();
		if(giant != null) {
			giant.killRocket();
		}

	}




	//	public void barkhord()
	//	{
	//		for(Weapon tir : tirs)
	//		{
	//			for(Enemy enemy :enemies)
	//			{
	//				if(doesStrike(tir, enemy))
	//				{
	//					enemy.decreasePower(tir);
	//					if(enemy.getPower()<=0)
	//					{
	//						enemies.remove(enemy);
	//					}
	//					tirs.remove(tir);
	//				}
	//			}
	//		}
	//	}

	//	public void isDied()
	//	{
	//		for(Enemy enemy : enemies){
	//			if(enemy.getPower() <= 0)
	//			{
	//				enemies.remove(enemy);
	//			}
	//		}
	//	}



	//	public boolean doesStrike(Weapon tir, Enemy enemy)
	//	{  
	//		Point northWest = new Point((int) tir.getX(), (int) tir.getY());
	//		Point northEast = new Point((int) tir.getX() + tir.getWidth() , (int) tir.getY());
	//		Point southWest = new Point((int)tir.getX() , (int)tir.getY() + tir.getHeight());
	//		Point southEast = new Point((int)tir.getX() + tir.getWidth(), (int) tir.getY() + tir.getHeight());
	//
	//		if(isIn(northWest, enemy))
	//		{
	//			return true;
	//		}
	//		else if(isIn(northEast, enemy))
	//		{
	//			return true;
	//		}
	//		else if(isIn(southWest, enemy))
	//		{
	//			return true;
	//		}
	//		else if(isIn(southEast, enemy))
	//		{
	//			return true;
	//		}
	//
	//
	//		return false;
	//	}

	//	public boolean isIn(Point p, Enemy enemy)
	//	{
	//
	//		//		if(p.getX()>= enemy.getX()-enemy.getWidth()/2 && p.getX() <= enemy.getX()+enemy.getWidth()/2
	//		//				&& p.getY() >= enemy.getY() - enemy.getHeight()/2 && p.getY() <= enemy.getY() + enemy.getHeight()/2)
	//		//		{0
	//		//			return true;
	//		//		}
	//
	//		if((p.getX()-enemy.getLocation().x)*(p.getX()-enemy.getLocation().y )
	//				+ (p.getY()-enemy.getLocation().y)*(p.getY()-enemy.getLocation().y)
	//				<= (enemy.getWidth()/4)*(enemy.getWidth()/4))
	//		{
	//			return true;
	//		}
	//		return false;
	//	}

	public static Gamer getGamer() {
		return gamer;
	}


	public static void setGamer(Gamer gamer) {
		Game.gamer = gamer;
	}

	public int getWidth() {
		return width;
	}





	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}



	public static ArrayList<Gamer> getGamers() {
		return gamers;
	}



	public static void setGamers(ArrayList<Gamer> gamers) {
		Game.gamers = gamers;
	}



	public ArrayList<ChickenGroup> getChickenGroups() {
		return chickenGroups;
	}



	public void setChickenGroups(ArrayList<ChickenGroup> chickenGroups) {
		this.chickenGroups = chickenGroups;
	}

	public void addGroup(ChickenGroup c) {
		chickenGroups.add(c);
	}

	public void addGiant(Giant g) {
		gamer.setGiant(g);
		this.giant = g;
	}








}
