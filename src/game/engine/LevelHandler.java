package game.engine;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import game.enemy.chickenGroup.ChickenGroup;

public class LevelHandler extends Thread{
	
	private Scanner scanner;
	private Gson gson;
	private Level level;
	private boolean flag;
	
	public LevelHandler()
	{
		
	}
	
	@Override 
	public void run()
	{
		if(flag)
		{
			flag = false;
			nextLevel();
		}
	}
	
	private void nextLevel()
	{
		Level level = gson.fromJson(scanner.next(), Level.class);
		
		
	}
	

}

class Level
{
	
	ArrayList<ChickenGroup> chickenGroups;
	
	public void start()
	{
		for(ChickenGroup c : chickenGroups)
		{
			
		}
		
	}
	
}
