package game.enemy.chickenGroup;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import game.Animatable;
import game.Velocity;
import game.enemy.Chicken;

public class CircularGroup implements Animatable, ChickenGroup{
	public ArrayList <Chicken> chickens;
	//	public Thread comeInThread;
	//	public Thread velocityHandler;
	private Thread centerMoveThread;

	private Double angularFrequency;
	private Double radius;
	private Point center;
	private Point newCenter;

	private Velocity centerStep;
	private int stepNum;
	private boolean start;
	Random randomValue;
	private long previousTime;


	public CircularGroup(int n, int chickenLevel)
	{
		initialize(n, chickenLevel);
	}

	private void initialize(int n, int chickenLevel)
	{
		this.doesTranslationalMotion = true;
	
		this.previousTime = System.currentTimeMillis();
		this.start = false;
		randomValue = new Random();
		center = new Point(100, 100);
		newCenter = new Point(randomValue.nextInt(1920), randomValue.nextInt(1030));
		
		this.stepNum = 40;
		this.centerStep = getCenterStep(stepNum);
		angularFrequency = (double) 0.05;
		setRadius(n);
		chickens = new ArrayList();
		for(int i = 0; i<n; i++)
		{
			double angle = ((double)i/n)*Math.PI*2;
			chickens.add(new Chicken(center, angle, radius, chickenLevel ));
		}





		centerMoveThread();
	}

	private void setRadius(int n)
	{
		radius = (double) (n * 15);
	}


	public void centerMoveThread()
	{
		centerMoveThread = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while(!chickens.isEmpty())
				{
					translationalMotion();
					//							centerStep = getCenterStep();
					//					System.out.println("in move Thread");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setNewCenter();
					setCenterStep();
				}
			}

		});


	}

	private void setNewCenter()
	{
		center = newCenter;
		newCenter = new Point(randomValue.nextInt(1920), randomValue.nextInt(1030));
	}

	@Override
	public void translationalMotion()
	{

//		int stepNum = 40;
//		Velocity step = getCenterStep(stepNum);

		for(int i = 0; i<stepNum; i++)
		{
			for(Chicken c : chickens)
			{
				System.out.println("center move");
				c.move(centerStep);
			}
			try {
				Thread.sleep(40);  //marboot be center move thread.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}

	int remainingSteps;
	boolean doesTranslationalMotion;

	@Override
	public void move() {
		if(start) {
			rotationalMotion();

			if(doesTranslationalMotion)
			{
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
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									setNewCenter();
									setCenterStep();
									doesTranslationalMotion = true;
								}
						
							});
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
		this.remainingSteps = stepNum;
		this.centerStep = getCenterStep(stepNum);
	
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
		centerMoveThread.start();

	}

	@Override
	public void joinThreads() {
		try {
			centerMoveThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
					reduceRadius(10);
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
					System.out.println("in reduce");

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
			chickens.remove(chicken);
			reset();
		}
	}





}
