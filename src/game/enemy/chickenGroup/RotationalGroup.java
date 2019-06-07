package game.enemy.chickenGroup;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Logger.Logger;
import game.Location;
import game.enemy.Chicken;

public class RotationalGroup implements ChickenGroup{
	private transient Logger logger = Logger.getLogger();
	private ArrayList<Chicken> chickens1;
	private ArrayList<Chicken> chickens2;
	private ArrayList<Chicken> chickens3;

	private int chickenLevel1;
	private int chickenLevel2;
	private int chickenLevel3;
	private int num;
	private Double radius;
	private double newRadius;
	private double angularFrequency;
	private Location center;
	private boolean start;
	private boolean inPosition;
	
	public RotationalGroup()
	{
		initialize();
	}
	public RotationalGroup(int num, int chickenLevel1,int chickenLevel2, int chickenLevel3)
	{

		this.chickenLevel1 = chickenLevel1;
		this.chickenLevel2 = chickenLevel2;
		this.chickenLevel3 = chickenLevel3;
		this.num = num;
		initialize();
	}

	private void initialize()
	{
		this.angularFrequency = 0.05;
		this.start = false;
		this.inPosition = false;
		this.center = new Location(1920/2, 1030/2);
		this.radius = (double) (1800/2);
		this.newRadius = (num) * 30;
		chickens1 = new ArrayList<Chicken>();
		chickens2 = new ArrayList<Chicken>();
		chickens3 = new ArrayList<Chicken>();
		for(int i  = 0; i<num; i++)
		{
			double angle = ((double)i/num)*Math.PI*2;
			chickens1.add(new Chicken(center, angle, radius-80,chickenLevel1));
			chickens2.add(new Chicken(center, angle+(Math.PI/180), radius, chickenLevel2));
			chickens3.add(new Chicken(center, angle+2*(Math.PI/180), radius+80, chickenLevel3));


		}
	}


	@Override
	public void paint(Graphics2D g2) {
		for(Chicken chicken : chickens1)
		{
			chicken.paint(g2);
		}
		for(Chicken chicken : chickens2)
		{
			chicken.paint(g2);
		}
		for(Chicken chicken : chickens3)
		{
			chicken.paint(g2);
		}
	}
	@Override
	public void move() {
		if(start) {
			if(inPosition)
			{
				this.rotationalMotion();
			}
			else
			{
				radius-= 50;
				radialMotion();
				if(radius <= newRadius) inPosition = true;
			}

		}

	}

	private void radialMotion()
	{
		synchronized(chickens1) {
			for(Chicken c : chickens1) {
				c.radialMotion(radius-80, center);
			}
		}
		synchronized(chickens2) {
			for(Chicken c : chickens2) {
				c.radialMotion(radius, center);
			}
		}
		synchronized(chickens3) {
			for(Chicken c : chickens3) {
				c.radialMotion(radius+80, center);
			}
		}
	}

	//	private void reduceRadius(double n)
	//	{
	//
	//		synchronized(this.radius)
	//		{
	//			Thread t = new Thread(new Runnable()
	//			{
	//
	//				@Override
	//				public void run() {
	//					for(int i = 0; i<n; i++)
	//					{
	//						radius --;
	//
	//						try {
	//							Thread.sleep(100);
	//						} catch (InterruptedException e) {
	//							// TODO Auto-generated catch block
	//							e.printStackTrace();
	//						}
	//					}
	//				}
	//
	//			});
	//
	//			t.start();
	//
	//		}
	//	}
	@Override
	public void moveHandler() {

	}
	@Override
	public void comeInFunction() {

	}
	@Override
	public void translationalMotion() {

	}
	@Override
	public void rotationalMotion() {

		synchronized(chickens1) {
			for(Chicken c : chickens1)
			{
				c.rotationalMotion(angularFrequency, radius-80, center);
			}
		}
		synchronized(chickens2)
		{
			for(Chicken c : chickens2)
			{
				c.rotationalMotion(angularFrequency, radius, center);
			}
		}
		synchronized(chickens3)
		{
			for(Chicken c : chickens3)
			{
				c.rotationalMotion(angularFrequency, radius+80, center);
			}
		}
	}
	@Override
	public ArrayList<Chicken> getGroup() {
		ArrayList<Chicken> chickens = new ArrayList<Chicken>();
		chickens.addAll(chickens1);
		chickens.addAll(chickens2);
		chickens.addAll(chickens3);

		return chickens;
	}
	@Override
	public void startThreads() {
		start = true;
	}
	@Override
	public void joinThreads() {

	}

//	private Integer remaining = 3;
	@Override
	public void remove(Chicken chicken) {
		if(chickens1.contains(chicken)) chickens1.remove(chicken);
		else if(chickens2.contains(chicken)) chickens2.remove(chicken);
		else if(chickens3.contains(chicken)) chickens3.remove(chicken);
//		synchronized(remaining) {
//			remaining--;
//			if(remaining == 0)
//			{
//				reset();
//				remaining = 3;
//			}
//		}
	}
	@Override
	public void reset() {
//		logger.debug("reset");
//		synchronized(chickens1) {
//			synchronized(chickens2){
//				synchronized(chickens3) {
//					while(!(chickens1.size() == chickens2.size() && chickens2.size() == chickens3.size()))
//					{
//						logger.debug("in while");
//						if(chickens1.size() > chickens2.size())
//						{
//							Chicken c = chickens1.get(chickens1.size()-1);
//							chickens1.remove(c);
//							chickens2.add(c);
//							setAngles(chickens1);
//							setAngles(chickens2);
//						}
//						else if(chickens2.size() > chickens2.size())
//						{
//							Chicken c = chickens2.get(chickens2.size()-1);
//							chickens2.remove(c);
//							chickens1.add(c);
//							setAngles(chickens1);
//							setAngles(chickens2);
//
//
//						}
//
//						if(chickens2.size() > chickens3.size())
//						{
//							Chicken c = chickens2.get(chickens2.size()-1);
//							chickens2.remove(c);
//							chickens3.add(c);
//							setAngles(chickens2);
//							setAngles(chickens3);
//						}
//						else if(chickens3.size() > chickens2.size())
//						{
//							Chicken c = chickens3.get(chickens3.size()-1);
//							chickens3.remove(c);
//							chickens2.add(c);
//							setAngles(chickens2);
//							setAngles(chickens3);
//						}
//
//
//					}
//				}
//			}
//		}

	}

	private void setAngles(ArrayList<Chicken> chickens)
	{
		synchronized(chickens) {
			for(int i = 0; i<chickens.size(); i++)
			{
				Chicken c = chickens.get(i);
				double angle = ((double)i/(num))*Math.PI*2;
				c.setAngle(angle);
			}
		}
	}









}
