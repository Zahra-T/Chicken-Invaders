package game.swing;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

public class GamePictures extends Loadable{
	private static GamePictures gamePictures;
	HashMap <String, Image> pictures;
	
	public static GamePictures getInstance() throws IOException
	{
		if(gamePictures == null) {
			gamePictures = new GamePictures();
		}
		return gamePictures;
	}
	
	public GamePictures() throws IOException
	{
		initialize();
	}
	
	private void initialize() throws IOException
	{
		pictures = new HashMap();
		
		pictures.put("background", loadJPGImage("background"));
		
		pictures.put("startPanel", loadJPGImage("startPanel"));
		
		
		pictures.put("chicken1", loadPNGImage("chicken1"));
		
		pictures.put("chicken2", loadPNGImage("chicken2"));
		
		pictures.put("chicken3", loadPNGImage("chicken3"));
		
		pictures.put("chicken4", loadPNGImage("chicken4"));
		
		pictures.put("giant1", loadPNGImage("giant1"));
		
		pictures.put("rocket", loadPNGImage("rocket"));
		
		pictures.put("redBullet", loadPNGImage("redBullet"));
		
		pictures.put("bombItem", loadPNGImage("BombItem"));
		
		pictures.put("leftDown", loadPNGImage("leftDown"));
		
		pictures.put("leftUp", loadPNGImage("leftUp"));
		
		pictures.put("userPanel", loadPNGImage("userPanel"));
		
		pictures.put("menuPanel", loadJPGImage("menuPanel"));
		
	}
	
	public Image get(String name)
	{
		return pictures.get(name);
	}


}

	