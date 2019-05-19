package game.enemy.chickenGroup;

import java.util.ArrayList;

import game.Animatable;
import game.enemy.Chicken;

public interface ChickenGroup extends Animatable{
	
	public void moveHandler();
	
	public void comeInFunction();
	
	public void translationalMotion();
	
	public void rotationalMotion();
	
	public ArrayList<Chicken> getGroup();
	
	public void startThreads();
	
	public void joinThreads();
	

}
