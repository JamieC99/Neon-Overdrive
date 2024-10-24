package main;

import input.*;
import story.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel
{
	private static final long serialVersionUID = 42623233425813311L;
	
	private static JFrame frame = new JFrame("Neon Overdrive");
	
	private final static int WIDTH = 1296, HEIGHT = 759; // 1280 + 16, 720 + 39
	private int viewportX, viewportY;
	private static float frameScale = 1f;
	
	Image mapImage = new ImageIcon("resources/map.png").getImage();
	
	public Window()
	{
		frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(this);
		frame.addKeyListener(new KeyInput());
		frame.addKeyListener(Handler.player);
		frame.addMouseListener(new MouseInput());
		frame.addMouseMotionListener(new MouseInput());
		
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Set background
		g.setColor(new Color(0, 148, 255));
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
		// Scale and translation
		frameScale = 1.0f * frame.getWidth() / WIDTH;
		g2d.scale(frameScale, frameScale);
		g2d.translate(viewportX + WIDTH / 2, viewportY + HEIGHT / 2);
		
		// Draw city
		g2d.drawImage(mapImage, 0, 0, null);
		
		// Draw game objects
		Handler.paintComponent(g2d);
		
		if (Handler.showBounds)
		{
			g2d.setColor(Color.RED);
			g2d.drawRect(Handler.getPlayerBounds().x, Handler.getPlayerBounds().y, 32, 64);
		}
		
		/* UI */
		g2d.translate(-(viewportX + WIDTH / 2), -(viewportY + HEIGHT / 2));
		
		g2d.setColor(new Color(0, 0, 0, 0.5f));
		g2d.fillRect(0, 0, 128, 64);
		
		g2d.setColor(Color.RED);
		g2d.drawString("Health: " + Integer.toString(PlayerStats.GetPlayerHealth()), 16, 24);
		
		g2d.setColor(Color.GREEN);
		g2d.drawString("Cash:   " + Integer.toString(PlayerStats.GetPlayerMoneyInHand()), 16, 40);
	}
	
	public void tick()
	{
		repaint();
		
		// Make the camera follow the player
		viewportX = -Handler.player.getX();
		viewportY = -Handler.player.getY();
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
	
	public static float getFrameScale()
	{
		return frameScale;
	}
}