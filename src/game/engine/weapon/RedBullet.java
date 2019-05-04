package game.engine.weapon;

import javax.imageio.ImageIO;

import game.Animatable;
import game.enemy.Enemy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RedBullet implements Weapon {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private BufferedImage bufferedImage;
    private ArrayList<Enemy> enemies;
	private int width;
	private int height;
	private int power;
    public RedBullet(double x, double y, double vx, double vy,int power, ArrayList<Enemy> enemies) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.power = power;
        this.enemies = enemies;
        initialize();

    }
    public void initialize()
    {
        try {

            // copied from http://www.java2s.com/Code/Java/Advanced-Graphics/RotatingaBufferedImage.htm
            bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\tir.png"));

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
        g2.drawImage(bufferedImage, (int)x, (int)y, null);
    }
}