package game.engine.rocket;

import javax.swing.SwingUtilities;

import game.gamer.Gamer;

public class ShelikThread extends Thread{
	
	Gamer gamer;

	public ShelikThread(Gamer gamer) {
		this.gamer = gamer;
	}

	@Override
	public void run() {
		while(true) {
			while (gamer.getRocket().isShelik()) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						gamer.getRocket().shelik();
					}

				});


				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			while(!gamer.getRocket().isShelik())
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
