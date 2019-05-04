package game.swing;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.gamer.Gamer;

public class AddingPanel extends JPanel{

	private JTextField userName;
	private String resource = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";

	public AddingPanel(int x, int y) throws IOException
	{
		initialize(x, y);
	}

	private void initialize(int x, int y) throws IOException
	{
		Color blue = new Color(100,100,230); 
		this.setLayout(null);
		this.setBounds(x, y, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));


				
		userName = new JTextField();
		userName.setBackground(blue);
		userName.setBorder(BorderFactory.createLineBorder(Color.white));
		userName.setBounds(50,50, 400, 80);
		userName.setHorizontalAlignment(JLabel.CENTER);
		userName.setForeground(Color.white);
		userName.setFont(new Font("Serif", Font.BOLD, 40));

		this.add(userName);

		setButtons();

		this.setVisible(true);


	}

	public void setButtons() throws IOException
	{
		Button ok = new Button("ok", 20, 200, 220, 60);
		ok.addActionListener((e)->{
			try {
				String name = userName.getText();
				if(!isChoosed(name))
				{
					UserPanel.getPanel().addUser(name);
					StartPanel.getPanel().remove(this);
					StartPanel.getPanel().enablePanel();

					StartPanel.getPanel().revalidate();
					//					StartPanel.getPanel().repaint();
				}
				else
				{
					//TODO show message, alredy choosed.
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		this.add(ok,1,0);

		Button cancel = new Button("cancel", 250, 200, 220, 60);
		cancel.addActionListener((e)->{
			try {
				StartPanel.getPanel().remove(this);
				StartPanel.getPanel().enablePanel();
				StartPanel.getPanel().revalidate();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		this.add(cancel,1,0);


	}

	public void addUser(String userName) throws IOException
	{

		UserPanel.getPanel().addUser(userName);
	}

	public boolean isChoosed(String userName) throws IOException
	{
		
		ArrayList <Gamer>list = UserPanel.getPanel().getGamers();
		for(Gamer gamer: list)
		{
			if(gamer.getUserName().equals(userName))
			{
				return true;
			}
		}
		return false;
	}





}
