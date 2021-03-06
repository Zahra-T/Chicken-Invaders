package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.swing.StartPanel;
import game.enemy.chickenGroup.ChickenGroup;
import game.engine.Game;
import game.gamer.Gamer;
import game.swing.MainFrame;
import game.swing.MainPanel;
import game.swing.Menu;

public class Main {
    public static void main(String[] args) throws IOException {
//        MainFrame mainFrame = MainFrame.getFrame();
        
//        mainFrame.setLayout(null);
//        GamerPanel gamerPanel = new GamerPanel();
//        gamerPanel.setBounds(0,0,1920,1030);
//        gamerPanel.setVisible(true);
//        mainFrame.add(gamerPanel);
//        mainFrame.setVisible(true);
        
//        mainFrame.setLayout(null);
//        StartingPanel startingPanel = new StartingPanel();
//        mainFrame.add(startingPanel);
//        startingPanel.setVisible(true);
//       
//
//        mainFrame.setVisible(true);
//    	Gson gson = new Gson();
//    	String str = gson.toJson(src);
//    	gson.f   	
		MainFrame frame = MainFrame.getFrame();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(0,0, 1920, 1030);
		loadInfo("C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game");
		frame.add(StartPanel.getPanel());
//		frame.add(Menu.getPanel());
//
//		frame.add(panel);
//		panel.setVisible(true);
		frame.setVisible(true);    	


    }
    
	public static void loadInfo(String address) throws IOException
	{
		
		Scanner sc = new Scanner(new File(address+"\\game.data"));
		int n = sc.nextInt();
		ArrayList<Gamer> gamers = Game.getGamers();
//		Gson gson = new Gson();
//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(ChickenGroup.class, new InterfaceAdapter<ChickenGroup());
//		Gson gson = builder.create();
		Gson gson = new GsonBuilder().registerTypeAdapter(ChickenGroup.class, new InterfaceAdapter<ChickenGroup>())
                .create();
		for(int i = 0; i<n; i++)
		{
//			String userName = sc.next();
//			Gamer gamer = new Gamer(userName);
			
//			gamer.setWave(sc.nextInt());
//			gamer.setLevel(sc.nextInt());
//			gamer.setHeart(sc.nextInt());
//			gamer.setBomb(sc.nextInt());
//			gamer.setFireLight(sc.nextInt());
//			gamer.setDrumStick(sc.nextInt());
			String str = sc.next();
			Gamer gamer = gson.fromJson(str, Gamer.class);
			gamer.setUserLabel();
			
			gamers.add(gamer);
			
//			System.out.println(userName);
			
		}
		
		
		System.out.println("loaded");
	}
}
