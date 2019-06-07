package game.levelManager;

import game.enemy.Giant;
import game.enemy.chickenGroup.ChickenGroup;
import game.enemy.chickenGroup.CircularGroup;
import game.enemy.chickenGroup.RectangularGroup;
import game.enemy.chickenGroup.RotationalGroup;
import game.engine.Game;

public class LevelHandler {
	private int level;
	private Game game;
	
	public LevelHandler(Game game) {
		this.level = 1;
		this.game = game;
		addGroup(new RectangularGroup(1, 5, 1));
	}
	
	public void nextLevel() {
		this.level++;
		if(level == 2) {
			addGroup(new RectangularGroup(3, 5, 1));
		}
		else if(level == 3) {
			addGroup(new CircularGroup(20, 2));
		}
		else if(level == 4) {
			addGroup(new RotationalGroup(30, 3, 2, 1));
		}
		else if(level == 5) {
			addGiant(new Giant(1));
		}
			
	}
	
	public void addGroup(ChickenGroup c)
	{
		synchronized(game.getChickenGroups()) {
			
			game.addGroup(c);
		}
	}
	
	public void addGiant(Giant g) {
		game.addGiant(g);
	}
	

}
