package game.enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Animatable;
import game.engine.weapon.Weapon;
import game.swing.GamePictures;

public class RedChicken implements Enemy{
	private int x;
	private int y;
	private int vx; 
	private int vy;
	private int width;
	private int height;
	private int power;
	private BufferedImage bufferedImage;
	boolean b =false;
    public RedChicken(int x, int y, int vx, int vy, int power)
    {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.power = power;
        initialize();
    }
    public void initialize()
    {
    	 try {
             bufferedImage = (BufferedImage) GamePictures.getInstance().get("redChicken");
         } catch (IOException ex) {
             ex.printStackTrace();
         }
         
         this.width = bufferedImage.getWidth();
         this.height = bufferedImage.getHeight();
    }
	@Override
	public void paint(Graphics2D g2) {
		// TODO Auto-generated method stub
		  g2.drawImage(bufferedImage, x - getWidth()/2, y - getHeight()/2 , null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		x += vx;
		y += vy;
		if(x >= 1700)
		{
			vx *= -1;
			b = true;
			
		}
		if(x <= 200 && b)
		{
			vx *= -1;
		}
		
	}
	
	@Override
	public void decreasePower(Weapon tir) {
		this.power -= tir.getPower();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return this.power;
	}

}
