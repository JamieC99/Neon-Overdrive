package main;

import input.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel
{
	private static final long serialVersionUID = 42623233425813311L;
	
	private static JFrame frame = new JFrame("Neon Overdrive");
	private final static int WIDTH = 1296, HEIGHT = 759;
	
	private AffineTransform transform;
	private static float frameScale;
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public Window()
	{
		frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(this);
		frame.addKeyListener(new KeyInput());
		frame.addMouseListener(new MouseInput());
		frame.addMouseMotionListener(new MouseInput());
		
		frame.setCursor(frame.CROSSHAIR_CURSOR);
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		// Set background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
		frameScale = 1.0f * frame.getWidth() / WIDTH;
		g2d.scale(frameScale, frameScale);
		
		// Enable rotation
		rotateOn();
		g2d.transform(transform);
		
		// Draw city
		g2d.drawImage(new ImageIcon("resources/City.png").getImage(), (int) Handler.getWorldX(), (int) Handler.getWorldY(), null);
		
		// Disable rotation
		rotateOff();
		g2d.transform(transform);
		
		// Draw player's car
		g.drawImage(new ImageIcon("resources/sprites/carSprite.png").getImage(), WIDTH / 2 - 16, HEIGHT / 2 - 32, 32, 64, null);
	
		// Enable rotation
		rotateOn();
		g2d.transform(transform);
		
		// Draw game objects
		Handler.paintComponent(g2d);  
		
		// Disable rotation
		rotateOff();
		g2d.transform(transform);
		
		if (Handler.showBounds)
		{
			g2d.setColor(Color.RED);
			g2d.drawRect(Handler.getPlayerBounds().x, Handler.getPlayerBounds().y, 32, 64);
		}
		
		Handler.paintButtons(g);
		
		// Bottom border
		g.setColor(Color.BLACK);
		g.fillRect(0, HEIGHT - 39, WIDTH, (int) (frame.getHeight() / frameScale));
	}
	
	private void rotateOn()
	{
		transform = AffineTransform.getTranslateInstance(0, 0);
		transform.rotate(Math.toRadians(Handler.getDirection()), WIDTH / 2, HEIGHT / 2);
	}
	
	private void rotateOff()
	{
		transform = AffineTransform.getTranslateInstance(0, 0);
		transform.rotate(Math.toRadians(-Handler.getDirection()), WIDTH / 2, HEIGHT / 2);
	}
	
	/** Return the width of the window */
	public static int getWindowWidth()
	{
		return WIDTH;
	}
	
	/** Return the height of the window */
	public static int getWindowHeight()
	{
		return HEIGHT;
	}
	
	/** Return the center of the screen on the x axis */
	public static float cameraX()
	{
		return WIDTH / 2 - Handler.getWorldX() / WIDTH;
	}
	
	/** Return the center of the screen on the y axis */
	public static float cameraY()
	{
		return HEIGHT / 2 - Handler.getWorldY() / HEIGHT;
	}
	
	public static float getFrameScale()
	{
		return frameScale;
	}
}