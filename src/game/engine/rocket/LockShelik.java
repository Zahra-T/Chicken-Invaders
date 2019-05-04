package game.engine.rocket;


public class LockShelik extends Thread{
	
	Rocket rocket;
	public LockShelik(Rocket rocket)
	{
		this.rocket = rocket;

	}
	
	@Override 
	public void run()
	{
		rocket.getShelik().setLock(true);
		try {
			sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rocket.getShelik().setLock(false);
	}
	


}

