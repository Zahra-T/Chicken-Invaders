package game.enemy.chickenGroup;

import java.util.ArrayList;

import game.Animatable;
import game.Location;
import game.enemy.Chicken;
import game.enemy.asset.AssetHolder;

public interface ChickenGroup extends Animatable{
	
	public void addAssets(int level, Location location);
	
	public AssetHolder getAssetHolder();
	
	public void moveHandler();
	
	public void comeInFunction();
	
	public void translationalMotion();
	
	public void rotationalMotion();
	
	public ArrayList<Chicken> getGroup();
	
	public void startThreads();
	
	public void joinThreads();
	
	public void remove(Chicken chicken);
	
	public void reset();
	
	public int size();
	
	
}
