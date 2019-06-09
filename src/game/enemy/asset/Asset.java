package game.enemy.asset;

import java.awt.Graphics;

public interface Asset {
	
    void move();
	
    void paint(Graphics g2);

	void setImage();
	
	int getWidth();
	
	int getHeight();
	
	
	
}
