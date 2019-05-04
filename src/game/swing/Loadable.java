package game.swing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loadable {
	
	public Image loadJPGImage(String name) throws IOException
	{
		Image image = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\"+name+".jpg"));
		return image;
	}
	
	public Image loadPNGImage(String name) throws IOException
	{
		Image image = ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\"+name+".png"));
		return image;
	}

}
