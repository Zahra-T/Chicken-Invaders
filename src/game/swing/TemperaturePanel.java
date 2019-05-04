package game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import game.engine.rocket.Rocket;

public class TemperaturePanel extends JLayeredPane{
	private JLabel tempLabel;
	private Rocket rocket;
//	private DegreeRect degreeRect;



	public TemperaturePanel(Rocket rocket) throws IOException
	{
		this.rocket = rocket;
		initialize();
	}

	public void initialize() throws IOException
	{
		this.setBounds(0, -10, 700, 100);

//		JLabel background = new JLabel();
//		background.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\leftUp.png"))));
//		background.setBounds(0, 0, 600, 100);
//		this.add(background,0,0);

		tempLabel = new JLabel("0");

		tempLabel.setFont(new Font("Serif", Font.BOLD, 40));
		tempLabel.setForeground(Color.white);
		tempLabel.setHorizontalAlignment(JLabel.CENTER);
		tempLabel.setBounds(30, 20, 200, 50);
		tempLabel.setForeground(Color.white);
		this.add(tempLabel, 2, 0);

	}



//	public void increase(int i) throws InterruptedException
//	{
//		if(!isOverHeat)
//		{
//			this.temperature += i;
//			this.tempLabel.setText(temperature+"");
//			if(temperature >= maxTemp)
//			{
//				isOverHeat = true;
//				temperature = maxTemp;
//			}
//		}
//		else
//		{
//			Thread.sleep(4000);
//			isOverHeat = false;
//		}
		//		degreeRect.increase(i);
//	}
//	public void setText()
//	{
//		
//	}
//	public void decrease(int d)
//	{
//		if(temperature > 0)
//		{
//			this.temperature -= d;
//			
//			if(temperature < 0)
//			{
//				temperature = 0;
//			}
//			this.tempLabel.setText(temperature+"");
//			if(temperature < 100)
//			{
//				isOverHeat = false;
//			}
//		}
//		//		degreeRect.decrease(d);
//	}
	@Override 
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		tempLabel.setText(rocket.getTemperature()+"");
//		g.drawRect(100, 20, temperature, 40);
		g.setColor(Color.white);
		g.fillRect(200, 30, rocket.getTemperature()*3, 40);
	}
//	
//	public boolean overHeat()
//	{
//		return isOverHeat;
//	}





}

//class DegreeRect 
//{
//
//	private int temp;
//	public DegreeRect()
//	{
//		this.temp = 0;
//	}
//
//	public void paint(Graphics g)
//	{
//		g.drawRect(100, 20, temp, 40);
//	}
//
//	public void increase(int i)
//	{
//		temp += i;
//	}
//
//	public void decrease(int d)
//	{
//		temp -= d;
//	}
//
//
//}
