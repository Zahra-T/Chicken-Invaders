package game.enemy.chickenGroup;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Logger.Logger;
import game.Animatable;
import game.Location;
import game.Velocity;
import game.enemy.Chicken;
import game.enemy.asset.AssetHolder;
import game.enemy.asset.Coin;
import game.enemy.asset.Egg;
import game.enemy.asset.Empowerer;
import game.enemy.asset.TypeEmpowerer;

public class RectangularGroup implements Animatable,ChickenGroup{
	public ArrayList<Chicken> chickens;
	public transient Thread comeInThread;
	public transient Thread velocityHandler;
	private int chickenLevel;
	private int row;
	private int column;
	private int step;
	private AssetHolder assetHolder;


	private transient Logger logger = Logger.getLogger();
	public RectangularGroup()
	{
		initialize();
	}
	public RectangularGroup(int row, int column, int chickenLevel)
	{
		this.chickenLevel = chickenLevel;
		this.row = row;
		this.column = column;

		initialize();

	}

	private void initialize()
	{
		this.step = 10;
		chickens = new ArrayList();
		assetHolder = new AssetHolder();
		moveHandler();
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
	public void moveHandler() {

		comeInFunction();

		translationalMotion();



	}

	@Override
	public void paint(Graphics2D g2) {
		for(Chicken chicken : chickens)
		{
			chicken.paint(g2);
		}
		assetHolder.paint(g2);
	}

	@Override
	public void move() {
		synchronized(chickens) {
			for(Chicken chicken: chickens)
			{

				chicken.move(new Velocity(step, 0));
				if((chicken.getLocation().getX() > 1800&& step > 0) || (chicken.getLocation().getX() < 100 && step <0))
				{
					this.step *= -1;
				}
			}
		}
		synchronized(assetHolder) {
			assetHolder.move();
		}
	}

	@Override
	public void comeInFunction() {
		comeInThread = new Thread(new Runnable()
		{

			@Override
			public void run() {
				for(int i = 0; i<column; i++)
				{
					for(int j = 0; j<row; j++)
					{
						synchronized(chickens) {
							Chicken c = new Chicken(new Location(-50, 150+100*j),new Velocity(10, 0), chickenLevel);
							chickens.add(c);
						}

					}
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
		//		comeInThread.start();
		try {
			comeInThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void translationalMotion() {
		velocityHandler = new Thread(new Runnable()
		{
			@Override
			public void run() {
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while(RectangularGroup.this.chickens.size() != 0) {
					for(Chicken chicken : chickens)
					{
						synchronized(chicken)
						{
							Velocity v = chicken.getVelocity();
							v.vx *= -1;
						}
					}
					try {
						Thread.sleep(4000-400*column);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		//		velocityHandler.start();
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
		comeInThread.start();
		velocityHandler.start();
	}

	@Override
	public void joinThreads() {
		// TODO Auto-generated method stub

	}

	//	@Override
	//	public void joinThreads() {
	//		this.comeInThread.join();
	//		this.velocityHandler.join();
	//		
	//	}

	public void remove(Chicken chicken)
	{
		synchronized(chickens) {
			chickens.remove(chicken);
			//			reset();
		}

	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	public int size() {
		return this.chickens.size();
	}
	//	public void reset()
	//	{
	//		Thread t = new Thread(new Runnable()Ú
	//		{
	//		
	//			@Override
	//			public void run() {
	//				for(int i = 0; i<chickens.size(); i++)
	//				{
	//					Chicken c = chickens.get(i);
	//					
	//					Point previousLocation = c.getLocation();
	//					Point newLocation = new Point()
	//					
	//					
	//				}				
	//			}
	//
	//		});
	//		
	//		t.start();
	//	}

	public AssetHolder getAssetHolder() {
		return assetHolder;
	}
	public void setAssetHolder(AssetHolder assetHolder) {
		this.assetHolder = assetHolder;
	}





}
