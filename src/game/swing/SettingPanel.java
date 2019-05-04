package game.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import game.engine.Game;
import game.gamer.Gamer;

public class SettingPanel extends JLayeredPane{
	
	private JLabel background;
	private JLabel settingLabel;
	private GamePictures gamePictures;


	public SettingPanel() throws IOException
	{
		initialize();
	}

	private void initialize() throws IOException
	{
		gamePictures = GamePictures.getInstance();
		this.setLayout(null);
		background = new JLabel(new ImageIcon(gamePictures.get("startPanel")));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);

//		settingLabel = new JLabel("Setting");
//		settingLabel.setFont(new Font("Serif", Font.BOLD, 40));
//		settingLabel.setForeground(Color.white);
//		settingLabel.setHorizontalAlignment(JLabel.CENTER);
//		settingLabel.setBounds(900, 100, 200, 40);
//		this.add(settingLabel, 2, 0);
		

		setButtons();
	}
	
	private void setButtons() throws IOException
	{
		Button avatar = new Button("avatarSetting", 660, 430, 600, 100);
		avatar.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(new AvatarSetting());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				

			}catch(Exception x)
			{
			}
		});
		this.add(avatar, 2, 0);
		
		Button rocketSetting = new Button("rocketSetting", 660, 540, 600, 100);
		rocketSetting.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(new RocketSetting());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();

			}catch(Exception x)
			{

			}
		});
		this.add(rocketSetting, 2, 0);
		

		
		Button soundSetting = new Button("soundSetting", 660, 650, 600, 100);
		soundSetting.addActionListener((e)->{
			try {
				this.add(new SoundSetting(), 3, 0);
				this.setEnabled(false);
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				
				

			}catch(Exception x)
			{

			}
		});
		this.add(soundSetting,2 , 0);
		

		Button back = new Button("back", 1480, 870, 380, 100);
		back.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(Menu.getPanel());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				

			}catch(Exception x)
			{

			}
		});
		this.add(back, 2, 0);
		
	}
	
	private void toMenu() throws IOException
	{
//		here
		MainFrame.getFrame().add(Menu.getPanel());
		MainFrame.getFrame().remove(this);
		MainFrame.getFrame().validate();
		MainFrame.getFrame().repaint();
	}
	
	

}

class SoundSetting extends JPanel
{
	
	public SoundSetting() throws IOException
	{
		initialize();
	}
	
	private void initialize() throws IOException
	{
		this.setLayout(null);
		this.setBounds(720, 250, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		setButtons();
		this.setVisible(true);
	}

	private void setButtons() throws IOException {
		Button minus = new Button("minus", 50, 100, 200, 100);
		minus.setStayLight(true);
		minus.addActionListener((e)->{
			try {
				if(StartPanel.getPanel().getVolume() > 0.1) {
				StartPanel.getPanel().setVolume(StartPanel.getPanel().getVolume() - 0.1);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		this.add(minus,2,0);
		Button plus = new Button("plus", 250, 50, 200, 200);
		plus.setStayLight(true);
		plus.addActionListener((e)->{
				//TODO save info.
				try {
					if(StartPanel.getPanel().getVolume() < 0.1) {
					StartPanel.getPanel().setVolume(StartPanel.getPanel().getVolume() + 0.1);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		});
		this.add(plus,2,0);
	}
	

}

class AvatarSetting extends JPanel
{
	private JLabel background;
	
	public AvatarSetting() throws IOException
	{
		initialize();
	}
	
	private void initialize() throws IOException
	{
		this.setLayout(new GridLayout());
		background = new JLabel(new ImageIcon(GamePictures.getInstance().get("startPanel")));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);
		setButtons();
		this.setVisible(true);
	}

	private void setButtons() throws IOException {
		Button back = new Button("back", 1480, 870, 380, 100);
		back.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(new SettingPanel());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				

			}catch(Exception x)
			{

			}
		});
		this.add(back, 2, 0);
	}
	
	
}

class RocketSetting extends JPanel
{
	private JLabel background;
	
	public RocketSetting() throws IOException
	{
		initialize();
	}
	
	private void initialize() throws IOException
	{
		this.setLayout(new GridLayout());
		background = new JLabel(new ImageIcon(GamePictures.getInstance().get("startPanel")));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);
		setButtons();
		this.setVisible(true);
	}

	private void setButtons() throws IOException {
		Button back = new Button("back", 1480, 870, 380, 100);
		back.addActionListener((e)->{
			try {

				MainFrame.getFrame().remove(this);
				MainFrame.getFrame().add(new SettingPanel());
				MainFrame.getFrame().validate();
				MainFrame.getFrame().repaint();
				

			}catch(Exception x)
			{

			}
		});
		this.add(back, 2, 0);
	}
	
	
}
