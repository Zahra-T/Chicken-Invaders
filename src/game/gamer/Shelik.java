package game.gamer;

public class Shelik {

	private Boolean mousePress;
	private Boolean lock;



	public Shelik()
	{
		mousePress = false ;
		lock = false;
	}

	public boolean isMousePress() {
		synchronized(mousePress)
		{
			return mousePress;
		}
	}

	public void setMousePress(boolean mousePress) {
		synchronized(this.mousePress)
		{
			this.mousePress = mousePress;
		}
	}

	public boolean isLock() {
		synchronized(lock)
		{
			return lock;
		}
	}

	public void setLock(boolean lock) {
		synchronized(this.lock)
		{
			this.lock = lock;
		}
	}



}
