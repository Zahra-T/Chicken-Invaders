package game.engine.weapon;

import javax.imageio.ImageIO;

import Logger.Logger;
import game.Animatable;
import game.enemy.Enemy;
import game.engine.Game;
import game.swing.GamePictures;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RedBullet implements Weapon {
	transient Logger logger = Logger.getLogger();
    private double x;
    private double y;
    private double vx;
    private double vy;
    private transient BufferedImage bufferedImage;
//    private transient ArrayList<Enemy> enemies;
	private transient int width;
	private transient int height;
	private int power;
	public RedBullet()
	{
		initialize();
	}
    public RedBullet(double x, double y, double vx, double vy,int power) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.power = power;
        initialize();

    }
    public void initialize()
    {
//    	this.enemies = Game.getGamer().getEnemies();
        try {

            // copied from http://www.java2s.com/Code/Java/Advanced-Graphics/RotatingaBufferedImage.htm
//            bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\tir.png"));
            bufferedImage = (BufferedImage) GamePictures.getInstance().get("redBullet");
            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() , bufferedImage.getHeight() );

            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            bufferedImage = op.filter(bufferedImage, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move() {
        x += vx;
        y += vy;
        
        
    }
    

    public int getWidth() {
		return width;
	}
    
	public int getHeight() {
		return height;
	}
	public int getPower()
	{
		return power;
	}
	
	public void paint(Graphics2D g2) {
//        g2.setColor(new Color(113, 4, 5));
//        g2.setStroke(new BasicStroke(3));
//
//        double l = 25.0 / Math.sqrt(vx * vx + vy * vy);
//
//        g2.drawLine((int) (x - l * vx), (int) (y - l * vy), (int)x, (int)y);
//        g2.drawImage(bufferedImage, (int)x, (int)y, null);
		g2.drawImage(bufferedImage, (int)(x - getWidth()/2),(int)( y - getHeight()/2) , null);
    }
}
