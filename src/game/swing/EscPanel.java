package game.swing;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.engine.Game;

public class EscPanel extends JPanel{
	private String resource = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	private MainPanel mainPanel;
	
	public EscPanel(MainPanel mainPanel) throws IOException
	{
		this.mainPanel = mainPanel;
		initialize();
	}

	private void initialize() throws IOException
	{
		Color blue = new Color(100,100,230); 
		this.setLayout(null);
		this.setBounds(720, 250, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));

		setButtons();

		this.setVisible(true);
	}


	public void setButtons() throws IOException
	{
		Button continueButton = new Button("continueGame", 50, 40, 400, 100);
		continueButton.addActionListener((e)->{
			try {
				mainPanel.continueGame();
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		this.add(continueButton,1,0);

		Button quitButton = new Button("quitGame", 50, 160, 400, 100);
		quitButton.addActionListener((e)->{
				//TODO save info.
				try {
					this.toMenu();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				



		});
		this.add(quitButton,1,0);
	}
	
	private void toMenu() throws IOException, InterruptedException
	{
		Menu menu = new Menu();
//		here
		MainFrame.getFrame().add(menu);
		MainFrame.getFrame().remove(mainPanel);
//		MainPanel.setNull();
		Game.getGamer().getRocket().setMoving(true);
//		Menu.getPanel().playSound();
		MainFrame.getFrame().validate();
		MainFrame.getFrame().repaint();
	}
}
