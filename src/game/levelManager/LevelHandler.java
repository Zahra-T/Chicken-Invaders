package game.levelManager;

import Logger.Logger;
import game.enemy.Giant;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.chickenGroup.CircularGroup;
import game.enemy.chickenGroup.RectangularGroup;
import game.enemy.chickenGroup.RotationalGroup;
import game.enemy.chickenGroup.SuicideGroup;
import game.engine.Game;

public class LevelHandler extends Thread{
	private int level;
	private Game game;
	private transient Logger logger = Logger.getLogger();
	public LevelHandler(Game game) {
		this.level = 1;
		this.game = game;
		addGroup(new RectangularGroup(1, 5, 1));
	}

	@Override 
	public void run() {

		while(level != 20) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(game.checkNextLevelTime()) {
				game.nextLevel();
			}
		}
	}

	public void nextLevel() {
		this.level++;
		if(level == 2) {
			logger.debug(2+"");
			ChickenGroup c = new RectangularGroup(3, 5, 1);
			addGroup(c);
		}
		else if(level == 3) {
			logger.debug(3+"");
			ChickenGroup c = new CircularGroup(20, 1);
			addGroup(c);
		}
		else if(level == 4) {
			logger.debug(4+"");
			ChickenGroup c = new RotationalGroup(15, 1, 1, 1);
			addGroup(c);
		}
		else if(level == 5) {
			logger.debug(5+"");
			Giant g = new Giant(1);
			addGiant(g);
		}
		else if(level == 6) {
			logger.debug(6+"");
			ChickenGroup c = new RectangularGroup(4, 6, 1);
			addGroup(c);
		}
		else if(level == 7) {
			ChickenGroup c = new CircularGroup(25, 2);
			addGroup(c);
		}
		else if(level == 8) {
			ChickenGroup c = new SuicideGroup(30, 2, game.getRocket());
			addGroup(c);
		}
		else if(level == 9) {
			ChickenGroup c = new RotationalGroup(15, 2, 1, 2);
			addGroup(c);
		}
		else if(level == 10) {
			Giant g = new Giant(2);
			addGiant(g);
		}
		else if(level == 11) {
			ChickenGroup c = new RectangularGroup(5, 7, 3);
			addGroup(c);
		}
		else if(level == 12) {
			ChickenGroup c = new SuicideGroup(40, 3, game.getRocket());
			addGroup(c);
		}
		else if(level == 13) {
			ChickenGroup c = new RotationalGroup(15, 2, 3, 1);
			addGroup(c);
		}
		else if(level == 14) {
			ChickenGroup c1 = new CircularGroup(25, 3);
			addGroup(c1);
			ChickenGroup c2 = new CircularGroup(20, 2);
			addGroup(c2);
		}
		else if(level == 15)
		{
			Giant g = new Giant(3);
			addGiant(g);
		}
		else if(level == 16) {
			ChickenGroup c = new RectangularGroup(5, 8, 4);
			addGroup(c);
		}
		else if(level == 17) {
			ChickenGroup c1 = new RectangularGroup(5, 8, 4);
			addGroup(c1);
			ChickenGroup c2 = new SuicideGroup(30, 30, game.getRocket());
			addGroup(c2);
		}
		else if(level == 18) {
			ChickenGroup c = new RotationalGroup(15,3,4,2);
			addGroup(c);
		}
		else if(level == 19) {
			ChickenGroup c = new SuicideGroup(50, 4, game.getRocket());
			addGroup(c);
		}
		else if(level == 20) {
			Giant g = new Giant(4);
			addGiant(g);
		}
		logger.debug("game.start called");
		game.start();

	}

	public void addGroup(ChickenGroup c)
	{
		synchronized(game.getChickenGroups()) {

			game.addGroup(c);
		}
	}

	public void addGiant(Giant g) {
		
			game.addGiant(g);
			logger.debug("giant added");
	}


}
