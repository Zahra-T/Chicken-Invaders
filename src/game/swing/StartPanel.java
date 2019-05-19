package game.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.engine.Game;
import game.gamer.Gamer;

public class StartPanel extends JPanel{
	private JLabel background;
	private static StartPanel panel;
	private JLabel userBackground;
	SoundPlayer soundPlayer;
	private ButtonPictures buttonPictures;
	private GamePictures gamePictures;
	
	public static StartPanel getPanel() throws IOException
	{
		if(panel == null)
		{
			panel = new StartPanel();
		}

		return panel;
	}
	private StartPanel() throws IOException
	{
		initialize();
	}

	private void initialize() throws IOException
	{
		this.gamePictures = GamePictures.getInstance();
		buttonPictures = ButtonPictures.getInstance();
		
		background = new JLabel(new ImageIcon(gamePictures.get("startPanel")));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);

		this.setLayout(null);



		userBackground = new JLabel(new ImageIcon(gamePictures.get("userPanel")));
		userBackground.setBounds(500, 200, 950, 500);
		this.add(userBackground, 2,0);


		JLabel karbaran = new JLabel("USERS");
		karbaran.setBounds(900, 100, 200, 40);
		karbaran.setFont(new Font("Serif", Font.BOLD, 40));

		//		 karbaran.setSize(500,200);
		karbaran.setForeground(Color.WHITE);
		karbaran.setVisible(true);
		this.add(karbaran, 2, 0);

		UserPanel users = UserPanel.getPanel();

		this.add(users, 2, 0);

		setButtons();

		this.setVisible(true);
		playSound();


	}
	
	public void playSound()
	{
		File sound = new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\I Saved You.wav");
		soundPlayer = new SoundPlayer(sound);
	}
	
	public void stopSound()
	{
		soundPlayer.stop();
	}
	


	private void setButtons() throws IOException
	{

		Button enter = new Button("enter", 390, 750, 380, 100);
		enter.addActionListener((e)->{


			try {
				if(!Game.getGamer().equals(null))
				{
					Menu menu = new Menu();

					MainFrame.getFrame().add(menu);
					
					menu.setVisible(true);
					MainFrame.getFrame().remove(StartPanel.getPanel());
					stopSound();
					menu.playSound();
					MainFrame.getFrame().validate();
					MainFrame.getFrame().repaint();
				}
				else
				{
					//TODO please choose user name.
					//TODO please enter your name.
				}

			}catch(Exception x)
			{

			}

		});
		this.add(enter,2,0);

		Button addUser = new Button("addUser", 790, 750, 380, 100);
		addUser.addActionListener((e)->{

			AddingPanel addingPanel;
			try {
				addingPanel = new AddingPanel(720, 250);
				StartPanel.getPanel().disablePanel();
				StartPanel.getPanel().add(addingPanel, 4, 0);
				//				StartPanel.getPanel().setEnabled(false);
				StartPanel.getPanel().repaint();


			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		this.add(addUser,2,0);

		Button removeUser = new Button("removeUser", 1190, 750, 380, 100);
		removeUser.addActionListener((e)->{

			RemovingPanel removingPanel;
			try {
				removingPanel = new RemovingPanel(720, 250);
				StartPanel.getPanel().disablePanel();
				StartPanel.getPanel().add(removingPanel, 4, 0);
				//				StartPanel.getPanel().setEnabled(false);
				StartPanel.getPanel().repaint();


			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		this.add(removeUser,2,0);

	}

	public void disablePanel()
	{

		for (Component c : this.getComponents()) {
			c.setEnabled(false);
		}
	}

	public void enablePanel()
	{
		for (Component c : this.getComponents()) {
			c.setEnabled(true);
		}
	}

	public float getVolume()
	{
		return soundPlayer.getVolume();
	}
	
	public void setVolume(double d)
	{
		soundPlayer.setVolume(d);
	}

	//	@Override
	//	public void paintComponent(Graphics g)
	//	{
	//		super.paintComponent(g);
	//		g.drawImage(background,0,0,this);
	//	}


}
