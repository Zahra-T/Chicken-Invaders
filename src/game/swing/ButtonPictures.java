package game.swing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ButtonPictures extends Loadable{
	private static ButtonPictures buttonPictures;
	HashMap <String, Image> pictures;
	
	
	public static ButtonPictures getInstance() throws IOException
	{
		if(buttonPictures == null) {
			buttonPictures = new ButtonPictures();
		}
		return buttonPictures;
	}
	
	public ButtonPictures() throws IOException
	{
		initialize();
	}
	
	private void initialize() throws IOException
	{
		pictures = new HashMap();
		pictures.put("enter", loadPNGImage("enter"));
		
		pictures.put("addUser", loadPNGImage("addUser"));
		
		pictures.put("remove", loadPNGImage("remove"));
		
		pictures.put("removeUser", loadPNGImage("removeUser"));
		
		pictures.put("newGame", loadPNGImage("newGame"));
		
		pictures.put("resume", loadPNGImage("resume"));
		
		pictures.put("ranking", loadPNGImage("ranking"));
		
		pictures.put("setting", loadPNGImage("setting"));
		
		pictures.put("quit", loadPNGImage("quit"));
		
		pictures.put("continueGame", loadPNGImage("continueGame"));
		
		pictures.put("quitGame", loadPNGImage("quitGame"));
		
		pictures.put("plus", loadPNGImage("plus"));
		
		pictures.put("minus", loadPNGImage("minus"));
		
		pictures.put("ok", loadPNGImage("ok"));
		
		pictures.put("cancel", loadPNGImage("cancel"));
		
		pictures.put("back", loadPNGImage("back"));
		
		pictures.put("avatarSetting", loadPNGImage("avatarSetting"));
		
		pictures.put("rocketSetting", loadPNGImage("rocketSetting"));
		
		pictures.put("soundSetting", loadPNGImage("soundSetting"));
		
		
		
		pictures.put("enter2", loadPNGImage("enter (2)"));
		
		pictures.put("addUser2", loadPNGImage("addUser (2)"));
		
		pictures.put("remove2", loadPNGImage("remove (2)"));
		
		pictures.put("removeUser2", loadPNGImage("removeUser (2)"));
		
		pictures.put("newGame2", loadPNGImage("newGame (2)"));
		
		pictures.put("resume2", loadPNGImage("resume (2)"));
		
		pictures.put("ranking2", loadPNGImage("ranking (2)"));
		
		pictures.put("setting2", loadPNGImage("setting (2)"));
		
		pictures.put("quit2", loadPNGImage("quit (2)"));
		
		pictures.put("continueGame2", loadPNGImage("continueGame (2)"));
		
		pictures.put("quitGame2", loadPNGImage("quitGame (2)"));
		
		pictures.put("plus2", loadPNGImage("plus (2)"));
		
		pictures.put("minus2", loadPNGImage("minus (2)"));
		
		pictures.put("ok2", loadPNGImage("ok (2)"));
		
		pictures.put("cancel2", loadPNGImage("cancel (2)"));
		
		pictures.put("back2", loadPNGImage("back (2)"));
		
		pictures.put("avatarSetting2", loadPNGImage("avatarSetting (2)"));
		
		pictures.put("rocketSetting2", loadPNGImage("rocketSetting (2)"));
		
		pictures.put("soundSetting2", loadPNGImage("soundSetting (2)"));
	}
	
	public Image get(String name)
	{
		return pictures.get(name);
	}

	

}