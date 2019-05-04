package game.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Button extends JButton{
	private Icon [] icon;
	private String buttonName;
	//	private int x;
	//	private int y;
	//	private int width;
	//	private int height;
	private int which;
	private boolean stayLight;
	private ButtonPictures buttonPictures;
	public Button(String buttonName, int x, int y, int width, int height) throws IOException
	{

		this.buttonName = buttonName;
		this.setBounds(x, y, width, height);
		
		//		this.x = x;
		//		this.y = y;
		//		this.width = width;
		//		this.height = height;
		initialize();
	}
	private void initialize() throws IOException{
		this.buttonPictures = ButtonPictures.getInstance();
		this.stayLight = false;
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		icon = new ImageIcon[2];
		icon[0] = new ImageIcon(buttonPictures.get(buttonName));
		icon[1] = new ImageIcon(buttonPictures.get(buttonName+2));	

		this.setIcon(icon[0]);

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(!Button.this.getStayLight()) {
					Button.this.setIcon(icon[0]);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Button.this.setIcon(icon[1]);
			}


			@Override
			public void mouseExited(MouseEvent e) {
				Button.this.setIcon(icon[0]);
			}
		});






		//		this.addMouseListener((e)->{
		//			
		//		});

	}

	public void setStayLight(boolean b)
	{
		stayLight = b;
	}
	public boolean getStayLight()
	{
		return stayLight;
	}
	//	@Override
	//	public void mouseIn()
	//	{
	//		
	//	}

	//	@Override
	//	public void paintComponent(Graphics g)
	//	{
	//		super.paintComponent(g);
	//		g.drawImage(bufferedImage[0], x, y, null);
	//	}
}
