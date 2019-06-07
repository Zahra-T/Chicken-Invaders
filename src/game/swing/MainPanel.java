package game.swing;

import game.PaintLoop;
import game.engine.Game;
import game.gamer.Gamer;

import javax.imageio.ImageIO;
import javax.swing.*;

import Logger.Logger;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JLayeredPane {
//	public static MainPanel panel;
	Logger logger = Logger.getLogger();
	private Game game;
	private Gamer gamer;
	private Achievement achievement;
	private TemperaturePanel tempLabel;

	private PaintLoop paintLoop;
	private EscPanel escPanel;

//	public static MainPanel getPanel() throws IOException, InterruptedException
//	{
//		if(panel == null) {
//			panel = new MainPanel();
//		}
//		return panel;
//	}

	public MainPanel() throws IOException, InterruptedException {
		initialize();
	}

	private void initialize() throws IOException, InterruptedException
	{
		this.setVisible(false);

		setBounds(0, 0, 1920, 1030);
		game = new Game(this);//Game.getGame();  
		game.setWidth(1920);
		game.setHeight(1030);
		this.gamer = Game.getGamer();
		achievement = new Achievement(gamer);
		this.add(achievement,2,0);
		tempLabel = new TemperaturePanel(gamer.getRocket());
		this.add(tempLabel,2,0);

		//		this.requestFocus();
		//		this.setFocusable(true);
		setFocusable(true);
		requestFocusInWindow();

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(game.getRocket().isMoving()) {
					game.getRocket().setX(e.getX());
					game.getRocket().setY(e.getY());
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if(game.getRocket().isMoving()) {
					game.getRocket().setX(e.getX());
					game.getRocket().setY(e.getY());
//					logger.debug("mouse: "+e.getX()+" "+e.getY());
				}
			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				gamer.shelik();
				
				if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
				{

					try {
						game.throwBomb();
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

//			int decreaseCoolDown = 0;
//			try {
//				//thread
//				{
//					if(decreaseCoolDown < 0) {
//						decreaseCoolDown = 20;
//						while(decreaseCoolDown-- > 0) {
////							attackCoolDown --;
//							Thread.sleep(1);
//						}
//					}
//					else {
//						decreaseCoolDown=20;
//					}	
//				}
//			}
//			catch (Exception ex) {
//				
//			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				int modifiers = e.getModifiers();
				if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
					gamer.getRocket().getShelik().setMousePress(true);
					
//					Thread t = new Thread(new Runnable() {
//
//
//						@Override
//						public void run() {
//							while(true)
//							{
//								while(isShelik())
//								{
//									try {
//										//										Thread.sleep(500);
//									
//											game.shelik();
//											temperature.increase(5);
//											temperature.repaint();
//											
//											if(temperature.overHeat())
//											{
//												break;
//											}
//										
////										if(temperature.overHeat())
////										{
////											lockShelik = true;
////											//										here
////											Thread t2 = new Thread(new Runnable() {
////
////												@Override
////												public void run() {
////													
////													try {
////														Thread.sleep(4000);
////													} catch (InterruptedException e) {
////														// TODO Auto-generated catch block
////														e.printStackTrace();
////													}
////													lockShelik = false;
////												}
////
////											});
////											t2.start();
////										}
//
//										Thread.sleep(200);
//									}
//									catch(Exception x)
//									{}
//								}
//								while(!isShelik())
//								{
//									temperature.decrease(8);
//									temperature.repaint();
//
//									try {
//										Thread.sleep(200);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//
//								}
//								try {
//									Thread.sleep(100);
//								} catch (InterruptedException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						}
//					});
//					t.start();
//				}
				}


			}

			@Override
			public void mouseReleased(MouseEvent e) {
				gamer.getRocket().getShelik().setMousePress(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("ID:"+e.getID());
				System.out.println("CODE:"+e.getKeyCode());
				//				if(arg0.getID().equals())
				//				{
				//					
				//				}
				//if(e.getCode() == 27)
				if(e.getKeyCode() == 27)
				{

					try {
						escPanel = new EscPanel(MainPanel.this);
						MainPanel.this.add(escPanel, 3, 0);
						//						MainPanel.getPanel().disablePanel();
						MainPanel.this.pauseGame();
						//						StartPanel.getPanel().add(escPanel, 4, 0);
						//						StartPanel.getPanel().setEnabled(false);
						MainFrame.getFrame().validate();
						MainFrame.getFrame().repaint();


					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//		g.drawImage(background, 0, 0, null);
		//		try {
		game.paint((Graphics2D) g);
//		achievement.repaint();
//		tempLabel.repaint();
	}
	
	public void repaintFunction()
	{
		this.repaint();
		achievement.repaint();
		tempLabel.repaint();
	}

	public void moveGame() {
		game.move();
	}

	public void kill()
	{
		game.kill();
	}
	
	public void removeOutTirs()
	{
		game.removeOutTirs();
	}

	public void startGame()
	{
		paintLoop = new PaintLoop(this);
		paintLoop.start();
		
		gamer.comeEnemies();
//		gamer.setEnemy();
	}

	public void pauseGame() throws InterruptedException, IOException
	{
		//		paintLoop.wait();
		//		Thread.sleep(1000000);
		paintLoop.setRunning(false);
		gamer.getRocket().setMoving(false);

	}

	public void continueGame() throws IOException, InterruptedException
	{
		paintLoop.setRunning(true);
		gamer.getRocket().setMoving(true);
		MainPanel.this.remove(escPanel);
		//		MainFrame.getFrame().validate();
		//		MainFrame.getFrame().repaint();
	}

	public PaintLoop getPaintLoop() {
		return paintLoop;
	}

//	public static void setNull()
//	{
//		panel = null;
//	}

	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	//	@Override
	//	public void keyPressed(KeyEvent e) {
	//		// TODO Auto-generated method stub
	//		System.out.println("ID:"+e.getID());
	//		System.out.println("CODE:"+e.getKeyCode());
	////		if(arg0.getID().equals())
	////		{
	////			
	////		}
	//		
	//	}
	//
	//	@Override
	//	public void keyReleased(KeyEvent arg0) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void keyTyped(KeyEvent arg0) {
	//		// TODO Auto-generated method stub
	//		
	//	}


}
