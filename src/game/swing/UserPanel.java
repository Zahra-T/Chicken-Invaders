package game.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import game.engine.Game;
import game.gamer.Gamer;

public class UserPanel extends JLayeredPane{
//	private String resource = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	private ArrayList<Gamer> gamers;
	private ArrayList<JLabel> gamerLabels;

	private static UserPanel panel;

	public static UserPanel getPanel() throws IOException
	{
		if(panel == null)
		{
			panel = new UserPanel();
		}

		return panel;
	}

	private UserPanel() throws IOException
	{
		initialize();
	}

	private void initialize() throws IOException
	{
		setBounds(500, 200, 950, 500);
		setLayout(new GridLayout());


		setVisible(true);
		gamers = Game.getGamers();
		
		gamerLabels = new ArrayList();
		System.out.println("here7"+gamers.size());
		for(Gamer gamer: gamers)
		{
			UserLabel userLabel = gamer.getUserLabel();
			add(userLabel);
			gamerLabels.add(userLabel);
			System.out.println("here8");
		}
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					UserPanel.getPanel().setBorder(BorderFactory.createLineBorder(Color.white));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}


			@Override
			public void mouseExited(MouseEvent e) {
				try {
					UserPanel.getPanel().setBorder(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public void addUser(String name) throws IOException
	{
		Gamer gamer = new Gamer(name);
		gamers.add(gamer);

		UserLabel userLabel = gamer.getUserLabel();
//		JLabel label = new JLabel("Hello");
//		label.setForeground(Color.white);
//		this.add(label);
		
		add(userLabel);
		gamerLabels.add(userLabel);
	}


	public void removeUser(String name)
	{
		int gamer = findUser(name);

		gamers.remove(gamer);

		this.remove(gamerLabels.get(gamer));
		gamerLabels.remove(gamer);	



	}

	private int findUser(String name)
	{
		for(int i = 0; i<gamers.size(); i++)
		{
			Gamer gamer = gamers.get(i);
			if(gamer.getUserName().equals(name))
			{
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Gamer> getGamers() {
		return gamers;
	}




}
