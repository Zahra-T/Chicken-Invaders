package game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import game.engine.Game;
import game.gamer.Gamer;

public class UserLabel extends JLabel{
//	private String userName;
	private Gamer gamer;
	//	private boolean isChoosed;

	public UserLabel(Gamer gamer)
	{
//		this.userName = userName;
		this.gamer = gamer;
		initialize();

	}

	private void initialize()
	{
		//		isChoosed = false;
		setText(gamer.getUserName());
		this.setPreferredSize(new Dimension(100, 50));//How?
		setFont(new Font("Serif", Font.BOLD, 40));
		setForeground(Color.white);
		setHorizontalAlignment(JLabel.CENTER);

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {


					try {
						UserLabel.this.setGamer();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				//TODO fale others
				//				for()
				//					if()
			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				UserLabel.this.setBorder(BorderFactory.createLineBorder(Color.white));

			}


			@Override
			public void mouseExited(MouseEvent e) {
				if(!gamer.isChoosed())
				{
					UserLabel.this.setBorder(null);
				}

			}
		});
	}
	
	private void setGamer() throws IOException
	{
		for(Gamer gamer : UserPanel.getPanel().getGamers())
		{
				if(gamer.isChoosed())
				{
					gamer.isChoosed(false);
				}
		}
		this.gamer.isChoosed(true);
		
		Game.setGamer(this.gamer);
		
		Menu.getPanel().getUserLabel().setText("Hello, "+ this.gamer.getUserName()+"!");
		Menu.getPanel().repaint();
	}

}
