package game.engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import Logger.Logger;
import game.Animatable;
import game.PaintLoop;
import game.enemy.Enemy;
import game.enemy.Giant;
import game.enemy.asset.AssetHolder;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.Chicken;
import game.engine.rocket.Rocket;
import game.engine.rocket.ShelikThread;
import game.engine.rocket.TemperatureCoolDown;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.gamer.Gamer;
import game.levelManager.LevelHandler;
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
	private Rocket rocket;
	private ArrayList<Weapon> rocketTirs;// = new ArrayList<>();
	private ArrayList<Bomb> bombs;// = new ArrayList();
	private Background[] backgrounds;
	private ArrayList<ChickenGroup> chickenGroups;
	private Giant giant;
	private File levelFile;
	private transient LevelHandler levelHandler;
	private transient Logger logger = Logger.getLogger();

	public Game(MainPanel mainPanel) throws IOException, InterruptedException {
		this.mainPanel = mainPanel;
		initialize();

	}

	private void initialize() throws IOException, InterruptedException {

		rocket = gamer.getRocket();
		rocketTirs = (ArrayList<Weapon>) gamer.getRocket().getTirs();
		bombs = (ArrayList<Bomb>) gamer.getRocket().getBombs();
		chickenGroups = gamer.getChickenGroups();
		giant = gamer.getGiant();

		levelHandler = new LevelHandler(this);
		levelHandler.start();
		backgrounds = new Background[2];
		backgrounds[0] = new Background(0, 1920, 1030, 0);
		backgrounds[1] = new Background(-1030 + 20, 1920, 1030, 1);
		(new ShelikThread(gamer)).start();
		
		

	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(new Color(24, 37, 100));
		g2.fillRect(0, 0, width, height);

		for (Background bg : backgrounds) {
			bg.paint(g2);
		}

		for (int i = 0; i < rocketTirs.size(); i++) {
			Weapon tir = this.rocketTirs.get(i);
			tir.paint(g2);
		}

		if (giant != null) {
			synchronized (gamer.getGiant().getTirs()) {
				for (int i = 0; i < giant.getTirs().size(); i++) {
					RedBullet tir = this.giant.getTirs().get(i);
					tir.paint(g2);
				}
			}
		}

		rocket.paint(g2);

		for (int i = 0; i < chickenGroups.size(); i++) {
			ChickenGroup c = chickenGroups.get(i);
			c.paint(g2);
		}

		for (int i = 0; i < bombs.size(); i++) {
			Bomb bomb = bombs.get(i);
			bomb.paint(g2);
		}
		if(giant != null) {
			giant.paint(g2);
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

		synchronized (rocketTirs) {

			for (Weapon tir : rocketTirs) {

				tir.move();
				// removeOutTirs(tir);
			}
		}

		if (giant != null) {
			for (int i = 0; i < giant.getTirs().size(); i++) {
				RedBullet tir = giant.getTirs().get(i);
				tir.move();
			}
		}

		synchronized (bombs) {
			for(int i = 0; i<bombs.size(); i++) {
				Bomb bomb = bombs.get(i);
				bomb.move();
				if (bomb.getDistance() <= 20) {
					bombs.remove(bomb);
					bombEvent();
					
				}
			}
		}

		synchronized (chickenGroups) {
			for (ChickenGroup c : chickenGroups) {
				c.move();
				
			}
		}
		if (giant != null) {
			synchronized (giant) {
				giant.move();
			}
		}

	}

	public void removeOutTirs() {
		for (int i = 0; i < rocketTirs.size(); i++) {
			Weapon tir = rocketTirs.get(i);
			if (tir.getY() < 0 || tir.getY() > 1030) {
				rocketTirs.remove(tir);
			}
			if (tir.getX() < 0 || tir.getX() > 1920) {
				rocketTirs.remove(tir);
			}
		}

		if (giant != null) {
			for (int i = 0; i < giant.getTirs().size(); i++) {
				Weapon tir = giant.getTirs().get(i);
				if (tir.getY() < 0 || tir.getY() > 1030) {
					giant.getTirs().remove(tir);
				}
				if (tir.getX() < 0 || tir.getX() > 1920) {
					giant.getTirs().remove(tir);
				}
			}
		}
	}

	public Rocket getRocket() {
		return rocket;
	}

	public void shelik() {
		gamer.getRocket().shelik();
	}

	public void increaseThread() {

	}



	public void throwBomb() throws IOException, InterruptedException {
		if (gamer.getBomb() > 0) {
			decreaseBomb();

			synchronized (bombs) {
				bombs.add(new Bomb());
			}
		}

	}
	
	private void bombEvent() {
		chickenGroups.clear();
		if(giant != null) {
			giant.decreaseHealth(50);
		}
	}

	private void decreaseBomb() {
		gamer.decreaseBomb();
		mainPanel.getAchievement().decreaseBomb();

	}

	public void kill() {
		gamer.killEnemy();
		if (giant != null) {
			giant.killRocket();
		}



	}
	public void nextLevel() {
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					chickenGroups.clear();
					levelHandler.nextLevel();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		t.start();
		
	}

	public boolean checkNextLevelTime() {
		boolean isEmptyGroups = true;
		if(chickenGroups != null) {
			synchronized(chickenGroups) {

				for(ChickenGroup c : chickenGroups) {
					isEmptyGroups = isEmptyGroups && (c.size() == 0);
				}
			}
		}

		boolean isEmptyGiant = true;
		if(giant != null) {
			synchronized(giant) {
				isEmptyGiant = (giant == null);
			}
		}
		return (isEmptyGroups && isEmptyGiant);
	}


	public void start() {
		gamer.comeEnemies();
	}
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
	
	public Giant getGiant() {
		return this.giant;
	}

}
