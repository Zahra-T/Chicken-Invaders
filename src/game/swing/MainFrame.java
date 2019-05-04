package game.swing;

import javax.swing.*;

public class MainFrame extends JFrame {
	private static MainFrame frame;
	
    public static MainFrame getFrame()
    {
    	if(frame == null)
    	{
    		frame = new MainFrame();
    	}
    	
    	return frame;
    }
    
    private MainFrame() 
    {
    	initialize();
    }
    
    private void initialize()
    {
    	setTitle("Swing");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0,0, 1920, 1030);
       
    }
    

}
