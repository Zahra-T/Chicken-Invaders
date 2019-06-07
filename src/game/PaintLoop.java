package game;

import javax.swing.SwingUtilities;

import Logger.Logger;
import game.swing.MainPanel;

public class PaintLoop extends Thread {
	Logger logger = Logger.getLogger();
	private MainPanel mainPanel;
	private boolean running;
	
	
	public PaintLoop(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.running = true;
	}

	@Override
	public void run() {
		while(true) {
		while (running) {
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
					mainPanel.moveGame();
					mainPanel.kill();
					mainPanel.removeOutTirs();
					mainPanel.revalidate();
					mainPanel.repaintFunction();
//					logger.debug("in running");
					
//				}
//
//			});

			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		while(!running)
		{
//			logger.debug("in not running");
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean b)
	{
		System.out.println("here2");
		running = b;
	}
}
