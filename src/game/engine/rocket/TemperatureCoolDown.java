package game.engine.rocket;

public class TemperatureCoolDown extends Thread{
	
	Rocket rocket;
	public TemperatureCoolDown(Rocket rocket) throws InterruptedException
	{
		this.rocket = rocket;

	}
	
	@Override
	public void run()
	{
		while(true)
		{
			while(rocket.getDecreaseCoolDown() <= 0)
			{
				rocket.decreaseTemp(1);
				try {
					sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
