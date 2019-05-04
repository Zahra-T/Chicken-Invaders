package game.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import game.PaintLoop;
import game.engine.Game;
import game.gamer.Gamer;

public class Menu extends JLayeredPane{
	private JLabel background;
	private JLabel userLabel;
	private static Menu menu;
	SoundPlayer soundPlayer;
	public static Menu getPanel() throws IOException
	{
		if(menu == null)
		{
			menu = new Menu();
		}

		return menu;
	}

	private Menu() throws IOException
	{
		initialize();
	}

	private void initialize() throws IOException
	{
		
		this.setLayout(null);
		background = new JLabel(new ImageIcon(GamePictures.getInstance().get("menuPanel")));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);

		Gamer gamer = Game.getGamer();
		String name = gamer.getUserName();
		userLabel = new JLabel("Hello, " + name + "!");
		userLabel.setFont(new Font("Serif", Font.BOLD, 40));
		userLabel.setForeground(Color.white);
		userLabel.setHorizontalAlignment(JLabel.CENTER);
		userLabel.setBounds(810, 370, 300, 50);
		this.add(userLabel, 3, 0);
		

		setButtons();
//		playSound();


		// TODO MOUSE	
		//		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//        Image img = toolkit.getImage("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\cursor.jpg");
		//        Cursor c = toolkit.createCustomCursor(img, new Point(getX(), getY()), "image");
		//        setCursor(c);
		//TODO MOUSE  
	}

	//	public static <T extends Number> void print(ArrayList<T> list)
	//	{
	//		
	//	}
	//	public static void print(ArrayList<?> list)
	//	{
	//		
	//	}
	//	public static <T> void print(ArrayList<T> list)
	//	{
	//		
	//	}

	//	public static void print(ArrayList<? extends Number> list)
	//	{
	//		
	//	}

	//	public static void print(ArrayList<? super Number> list)
	//	{
	//		
	//	}

	public void setButtons() throws IOException
	{

		Button newGame = new Button("newGame", 660, 430, 600, 100);
		newGame.addActionListener((e)->{
			try {
				newGame();
//				stopSound();


			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}

		});
		newGame.setVisible(true);
		this.add(newGame,2,0);

		Button resume = new Button("resume", 660, 540, 600, 100);
		resume.addActionListener((e)->{
			try {
				resume();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			stopSound();
		});
		this.add(resume,2,0);


		Button ranking = new Button("ranking", 660, 650, 600, 100);
		ranking.addActionListener((e)->{
			//ranking
//			stopSound();
		});
		this.add(ranking,2,0);


		Button setting = new Button("setting", 660, 760, 600, 100);
		setting.addActionListener((e)->{
			try {
				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(new SettingPanel());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			stopSound();
		});
		this.add(setting,2,0);


		Button quit = new Button("quit", 40, 870, 380, 100);
		quit.addActionListener((e)->{
			try {
				saveInfo("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		});
		this.add(quit,2,0);

		Button back = new Button("back", 1480, 870, 380, 100);
		back.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(Menu.getPanel());
				MainFrame.getFrame().add(StartPanel.getPanel());
				StartPanel.getPanel().playSound();
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				
				stopSound();

			}catch(Exception x)
			{

			}
		});
		this.add(back,2,0);
		


	}
	
	public void playSound()
	{
		File sound = new File("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\The Fathiers.wav");
		soundPlayer = new SoundPlayer(sound);
	}
	
	public void stopSound()
	{
		soundPlayer.stop();
	}
	
	public void newGame() throws IOException, InterruptedException
	{
		System.out.println("new");
		Game.getGamer().setNew();
		System.out.println("new3");
		MainPanel.getPanel().setVisible(true);
		System.out.println("new5");
		MainFrame.getFrame().add(MainPanel.getPanel());
		System.out.println("new4");
		MainFrame.getFrame().remove(this);
		MainPanel.getPanel().startGame(); 
		System.out.println("new2");
	}
	

	public void resume() throws IOException, InterruptedException
	{
		MainPanel.getPanel().setVisible(true);
		MainFrame.getFrame().add(MainPanel.getPanel());
		MainFrame.getFrame().remove(this);
		MainPanel.getPanel().startGame(); 
	}

	public JLabel getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(JLabel userLabel) {
		this.userLabel = userLabel;
	}


	public void saveInfo(String address) throws FileNotFoundException
	{

		File p = new File(address + "\\" + "game.data");
		PrintWriter printWriter = new PrintWriter(p);
		ArrayList<Gamer>gamers = Game.getGamers();
		printWriter.println(gamers.size());
		for(Gamer gamer : gamers)
		{
			printWriter.println(gamer.getUserName());
			printWriter.println(gamer.getWave());
			printWriter.println(gamer.getLevel());
			printWriter.println(gamer.getHeart());
			printWriter.println(gamer.getBomb());
			printWriter.println(gamer.getFireLight());
			printWriter.println(gamer.getDrumStick());
		}
		//

		//

		printWriter.close();
		
		System.out.println("saved");

	}
	
//	public void setPlayingSound(boolean b)
//	{
//		this.isPlaying = b;
//	}






}
