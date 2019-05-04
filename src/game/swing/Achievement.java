package game.swing;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import game.gamer.Gamer;

public class Achievement extends JLayeredPane{
	private static Achievement achievement;
	private Gamer gamer;
	
	private JLabel Heart;
	private JLabel Bomb;
	private JLabel FireLight;
	private JLabel DrumStick;
	
	public Achievement(Gamer gamer) throws IOException
	{
		this.gamer = gamer;
		initialize();
	}
	
//	public static Achievement getInstance() throws IOException
//	{
//		if(achievement == null)
//		{
//			achievement = new Achievement();
//		}
//		return achievement;
//	}
	private void initialize() throws IOException
	{
		this.setBounds(0, 900, 600, 100);
		
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\leftDown.png"))));
		background.setBounds(0, 0, 600, 100);
		this.add(background,1,0);
		
		Heart = new JLabel(gamer.getHeart()+"");
		Heart.setBounds(70, 55, 10, 10);
		Heart.setForeground(Color.white);
		this.add(Heart, 2, 0);
		
		Bomb = new JLabel(gamer.getBomb()+"");
		Bomb.setBounds(165, 55, 10, 10);
		Bomb.setForeground(Color.white);
		this.add(Bomb, 2, 0);
		
		FireLight = new JLabel(gamer.getFireLight()+"");
		FireLight.setBounds(265, 55, 10, 10);
		FireLight.setForeground(Color.white);
		this.add(FireLight, 2, 0);
		
		DrumStick = new JLabel(gamer.getDrumStick()+"");
		DrumStick.setBounds(360, 55, 10, 10);
		DrumStick.setForeground(Color.white);
		this.add(DrumStick, 2, 0);
		
	}
	
	public void increaseHeart(int n)
	{
		Heart.setText(gamer.getHeart()+"");
		repaint();
	}
	
	public void increaseBomb(int n)
	{
		Bomb.setText(gamer.getBomb()+"");
		repaint();
	}
	
	public void increaseFireLight(int n)
	{
		FireLight.setText(gamer.getFireLight()+"");
		repaint();
	}
	
	public void increaseDrumStick(int n)
	{
		DrumStick.setText(gamer.getDrumStick() +"");
		repaint();
	}
	
	public void decreaseHeart()
	{
		Heart.setText(gamer.getHeart()+"");
		repaint();
	}
	
	public void decreaseBomb()
	{
		Bomb.setText(gamer.getBomb()+"");
		repaint();
	}
	
	public void decreaseFireLight(int n)
	{
		FireLight.setText(gamer.getFireLight() +"");
		repaint();
	}
	
	public void decreaseDrumStick(int n)
	{
		DrumStick.setText(gamer.getDrumStick() +"");
		repaint();
	}



}
