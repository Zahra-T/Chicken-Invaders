package game;

import javax.swing.SwingUtilities;

import game.swing.MainPanel;

public class PaintLoop extends Thread {
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
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					mainPanel.moveGame();
					mainPanel.deadEnemy();
					mainPanel.repaint();
		
				}

			});


			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		while(!running)
		{
			try {
				Thread.sleep(100);
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
