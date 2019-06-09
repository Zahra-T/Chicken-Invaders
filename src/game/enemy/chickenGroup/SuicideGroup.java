package game.enemy.chickenGroup;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Logger.Logger;
import game.Location;
import game.enemy.Chicken;
import game.enemy.asset.AssetHolder;
import game.enemy.asset.Coin;
import game.enemy.asset.Egg;
import game.enemy.asset.Empowerer;
import game.enemy.asset.TypeEmpowerer;
import game.engine.rocket.Rocket;
public class SuicideGroup implements ChickenGroup{

	private ArrayList<Chicken> chickens;
	private int num;
	private Random random;
	private int chickenLevel;
	private boolean start;
	private long previousTime;
	private long lastAttack;
	private Rocket rocket;
	private AssetHolder assetHolder;
	public SuicideGroup(int num, int chickenLevel, Rocket rocket)
	{
		this.num = num;
		this.rocket = rocket;
		this.chickenLevel = chickenLevel;
		initialize();

	}
	private void initialize()
	{
		random = new Random();
		start = false;
		this.lastAttack = System.currentTimeMillis();
		chickens = new ArrayList();
		//		for(int i = 0; i<num; i++)+
		//		{
		//			Location l = new Location(random.nextInt(1920), random.nextInt(1030));
		//			chickens.add(new Chicken(l, chickenLevel));
		//		}
		this.previousTime = System.currentTimeMillis();

		
		for(int i = 0; i<num; i++)
		{
			Location beginning = new Location(random.nextInt(300)+1950, random.nextInt(1030)+300);
			chickens.add(new Chicken(beginning, chickenLevel));
		}
		
		assetHolder = new AssetHolder();

	}
	@Override
	public void paint(Graphics2D g2) {
		synchronized(chickens) {
			for(Chicken c : chickens)
			{
				c.paint(g2);
			}
		}
		synchronized(assetHolder) {
		assetHolder.paint(g2);
		}
	}
	@Override
	public void move() {
		if(start) {
		translationalMotion();
		
		assetHolder.move();
		}
	}

	@Override
	public void moveHandler() {

	}

	@Override
	public void comeInFunction() {

	}
	@Override
	public void translationalMotion() {
		//		Thread t = new Thread(new Runnable()
		//				{
		//
		//					@Override
		//					public void run() {

		for(int i = 0; i<chickens.size(); i++)
		{
			Chicken c = chickens.get(i);

			if(System.currentTimeMillis() - lastAttack > 10000) {
				c.suicideMotion(rocket.getX(), rocket.getY());
				lastAttack = System.currentTimeMillis();
			}
			c.translationalMotion();
		}


		//					}
		//			
		//				});
		//		t.start();
	}
	
	@Override
	public void addAssets(int level, Location l) {
		if(Math.random() < 0.05) {
			assetHolder.add(new Egg(level, new Location(l.getX(), l.getY())));
		}
		if(Math.random() < 0.06) {
			assetHolder.add(new Coin(new Location(l.getX(), l.getY())));
		}
		if(Math.random() < 0.03) {
			assetHolder.add(new Empowerer(new Location(l.getX(), l.getY())));
		}
		else if(Math.random() < 0.03) {
			assetHolder.add(new TypeEmpowerer(new Location(l.getX(), l.getY())));
		}
	}
	@Override
	public void rotationalMotion() {

	}
	@Override
	public ArrayList<Chicken> getGroup() {
		return chickens;
	}
	@Override
	public void startThreads() {
		start = true;
	}

	@Override
	public void joinThreads() {

	}

	@Override
	public void remove(Chicken chicken) {
		chickens.remove(chicken);
	}
	@Override
	public void reset() {

	}

	public int size() {
		return this.chickens.size();
	}
	public AssetHolder getAssetHolder() {
		return assetHolder;
	}
	public void setAssetHolder(AssetHolder assetHolder) {
		this.assetHolder = assetHolder;
	}




}
