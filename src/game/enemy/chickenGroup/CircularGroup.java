package game.enemy.chickenGroup;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Logger.Logger;
import game.Animatable;
import game.Location;
import game.Velocity;
import game.enemy.Chicken;

public class CircularGroup implements Animatable, ChickenGroup{
	public ArrayList <Chicken> chickens;
	//	public Thread comeInThread;
	//	public Thread velocityHandler;
//	private Thread centerMoveThread;
	transient Logger logger = Logger.getLogger();
	private Double angularFrequency;
	private Double radius;
	private Location center;
	private Location newCenter;

	private Velocity centerStep;
	private int stepNum;
	private boolean start;
	Random randomValue;
	private int chickenLevel;
	private int num;
//	private long previousTime;
	public CircularGroup()
	{
		initialize();
	}

	public CircularGroup(int num, int chickenLevel)
	{
		this.num = num;
		this.chickenLevel = chickenLevel;
		
		initialize();
	}

	private void initialize()
	{
		this.doesTranslationalMotion = true;

//		this.previousTime = System.currentTimeMillis();
		this.start = false;
		randomValue = new Random();
		center = new Location(100, 100);
		newCenter = new Location(randomValue.nextInt(1920), randomValue.nextInt(1030));

		this.stepNum = 100;
		this.remainingSteps = stepNum;
		this.centerStep = getCenterStep(stepNum);
		angularFrequency = (double) 0.05;
		setRadius(num);
		chickens = new ArrayList();
		for(int i = 0; i<num; i++)
		{
			double angle = ((double)i/num)*Math.PI*2;
			chickens.add(new Chicken(center, angle, radius, chickenLevel ));
		}





		//		centerMoveThread();
	}

	private void setRadius(int n)
	{
		radius = (double) (n * 15);
	}


	//	public void centerMoveThread()
	//	{
	//		centerMoveThread = new Thread(new Runnable()
	//		{
	//			@Override
	//			public void run() {
	//				while(!chickens.isEmpty())
	//				{
	//					translationalMotion();
	//					//							centerStep = getCenterStep();
	//					//					System.out.println("in move Thread");
	//					try {
	//						Thread.sleep(5000);
	//					} catch (InterruptedException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//					setNewCenter();
	//					setCenterStep();
	//				}
	//			}
	//
	//		});
	//
	//
	//	}

	private void setNewCenter()
	{
		center = newCenter;
		newCenter = new Location(randomValue.nextInt(1920), randomValue.nextInt(1030));
	}

	@Override
	public void translationalMotion()
	{
		synchronized(center)
		{
			center.setX(center.getX()+centerStep.vx);
			center.setY(center.getY()+centerStep.vy);
		}
		synchronized(chickens) {
			for(Chicken c : chickens)
			{
//				System.out.println("center move");
				c.move(centerStep);
//				System.out.println(centerStep.vx +" " + centerStep.vy);
			}
		}

		//		int stepNum = 40;
		//		Velocity step = getCenterStep(stepNum);

		//		for(int i = 0; i<stepNum; i++)
		//		{
		//			for(Chicken c : chickens)
		//			{
		//				System.out.println("center move");
		//				c.move(centerStep);
		//			}
		//			try {
		//				Thread.sleep(40);  //marboot be center move thread.
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}


	}

	Integer remainingSteps;
	Boolean doesTranslationalMotion;

	@Override
	public void move() {
		if(start) {
			rotationalMotion();
//			System.out.println(doesTranslationalMotion);
			synchronized(doesTranslationalMotion) {
				if(doesTranslationalMotion)
				{
					synchronized(remainingSteps) {
						if(remainingSteps > 0)
						{
							
							translationalMotion();
							remainingSteps--;
						}
						else
						{

							doesTranslationalMotion = false;
							Thread t = new Thread(new Runnable()
							{
								@Override
								public void run() {
									
									try {
										Thread.sleep(5000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									setNewCenter();
									setCenterStep();
									
									synchronized(doesTranslationalMotion) {
										doesTranslationalMotion = true;
									}
								}

							});
							t.start();
						}
					}
				}
			}


		}

	}



	private Velocity getCenterStep(int stepNum)
	{
		double xStep = (newCenter.getX() - center.getX())/stepNum;
		double yStep = (newCenter.getY() - center.getY())/stepNum;
		return (new Velocity(xStep,yStep));
	}

	private void setCenterStep()
	{
		synchronized(remainingSteps) {
			this.remainingSteps = stepNum;
		}
		synchronized(centerStep) {
			this.centerStep = getCenterStep(stepNum);
		}

	}



	@Override
	public void paint(Graphics2D g2) {
		for(Chicken chicken : chickens)
		{
			chicken.paint(g2);
		}
	}




	@Override
	public void moveHandler() {

	}

	@Override
	public void comeInFunction() {

	}

	//	@Override
	//	public void translationalMotion() {
	//		
	//		
	//	}

	@Override
	public void rotationalMotion() {
		
		synchronized(chickens) {

			for(Chicken c : chickens)
			{
				c.rotationalMotion(angularFrequency, radius, center);
			}
		}
	}

	@Override
	public ArrayList<Chicken> getGroup() {
		return chickens;
	}

	@Override
	public void startThreads() {
		start = true;
		//		centerMoveThread.start();

	}

	@Override
	public void joinThreads() {
		start = false;
//		try {
////			centerMoveThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

	@Override
	public void reset()
	{
		synchronized(chickens) {
			for(int i = 0; i<chickens.size(); i++)
			{
				Chicken c = chickens.get(i);
				synchronized(c.getAngle()) {
					chickens.get(i).setAngle( (((double)i/chickens.size())*Math.PI*2)  );
					reduceRadius(3);
				}

			}
		}
	}

	private void reduceRadius(double n)
	{

		synchronized(this.radius)
		{
			Thread t = new Thread(new Runnable()
			{

				@Override
				public void run() {
					for(int i = 0; i<n; i++)
					{
						radius --;
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			});

			t.start();

		}
	}

	@Override
	public void remove(Chicken chicken) {
		synchronized(chickens)
		{
//			synchronized(chicken) {
			chickens.remove(chicken);
//			}
			reset();
		}
	}





}
