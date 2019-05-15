package game.engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import game.Animatable;
import game.enemy.Enemy;
import game.enemy.RedChicken;
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
	private ArrayList <Weapon>tirs;// = new ArrayList<>();
	private ArrayList <Bomb>bombs;// = new ArrayList();
	private ArrayList <Enemy> enemies;// = new ArrayList<>();
	private Background[] backgrounds;

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
		//		rocket = new Rocket(width / 2 - 50, height - 200);
		
		rocket = gamer.getRocket();
		tirs = gamer.getRocket().getTirs();
		bombs = gamer.getRocket().getBombs();
		enemies = gamer.getEnemies();



		backgrounds = new Background[2];
		backgrounds[0] = new Background(0, 1920, 1030,0);
		backgrounds[1] = new Background(-1030 + 20, 1920, 1030,1);
		System.out.println("where");
		(new ShelikThread(gamer)).start();
		System.out.println("where2");

	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(24, 37, 100));
		g2.fillRect(0, 0, width, height);

		for(Background bg : backgrounds)
		{
			bg.paint(g2);
		}


		for (Weapon tir : (ArrayList<Weapon>)this.tirs) {
			tir.paint(g2);
		}

		rocket.paint(g2);


		for (Enemy enemy : (ArrayList<Enemy>)enemies) {
			enemy.paint(g2);
		}

		for (Bomb bomb: (ArrayList<Bomb>)bombs) {
			bomb.paint(g2);
		}


	}

	@Override
	public void move() {
		synchronized (backgrounds) {
			for (Background bg : backgrounds) {
				bg.move();
			}
		}
		rocket.move();
		synchronized (tirs) {
			for (Weapon tir : tirs) {
				
				tir.move();
//				removeOutTirs(tir);
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

		synchronized (enemies) {
			for (Enemy enemy : enemies) {
				enemy.move();
			}
		}





	}
	
	public void removeOutTirs(Weapon tir)
	{
		if(tir.getY()<0  || tir.getY() > 1030)
		{
			tirs.remove(tir);
		}
		if(tir.getX()<0 || tir.getX() > 1920)
		{
			tirs.remove(tir);
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


	public void barkhord()
	{
		for(Weapon tir : (ArrayList<Weapon>)tirs)
		{
			for(Enemy enemy :(ArrayList<Enemy>) enemies)
			{
				if(doesStrike(tir, enemy))
				{
					enemy.decreasePower(tir);
					if(enemy.getPower()<=0)
					{
						enemies.remove(enemy);
					}
					tirs.remove(tir);
				}
			}
		}
	}

	//	public void isDied()
	//	{
	//		for(Enemy enemy : enemies){
	//			if(enemy.getPower() <= 0)
	//			{
	//				enemies.remove(enemy);
	//			}
	//		}
	//	}



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
		//		{
		//			return true;
		//		}

		if((p.getX()-enemy.getX())*(p.getX()-enemy.getX()) 
				+ (p.getY()-enemy.getY())*(p.getY()-enemy.getY())
				<= (enemy.getWidth()/4)*(enemy.getWidth()/4))
		{
			return true;
		}
		return false;
	}

	public static Gamer getGamer() {
		return gamer;
	}

	//	public static void setGamer(Gamer gamer) {
	//		
	//	   gamer = gamer;
	//		
	//
	//
	//	}

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






}
