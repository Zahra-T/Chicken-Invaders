package game.swing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Animatable;

public class Background implements Animatable{
	private Image bufferedImage;
	private double y;
	private double vy;
	private int width;
	private int height;
	private int which;
	public Background(double y, int width, int height, int which) throws IOException
	{
		super();
		this.y = y;
		this.width = width;
		this.height = width;
		this.which = which;
		initialize();
	}
	
//	public Background(ImageIcon Img)
//	{
//		super();
//	}
	
	private void initialize() throws IOException
	{
		vy = 0;
        try {
        	if(which == 0) {
            bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\background.jpg"));
        	}
        	else 
        	{
            bufferedImage = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\background.jpg"));
        	}
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	@Override
	public void move()
	{
		y += vy;
		if(y >= height)
		{
			y = -height/2;
		}
	}
	
    @Override
    public void paint(Graphics2D g2) {
      g2.drawImage(bufferedImage, 0, (int)y, null);
  }

}
