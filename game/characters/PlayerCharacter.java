package characters;

import main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerCharacter extends Character implements KeyListener
{
	private float velx, vely;
	private float speed = 2;
	
	public PlayerCharacter(int x, int y, String name)
	{
		super(x, y, name);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x-16, y-16, 32, 32);
	}
	
	public void tick()
	{
		// Add velocity for movement
		x += velx;
		y += vely;
		
		// Clamp player's position
		if (x <= Handler.minWorldPos()) x = Handler.minWorldPos();
		if (x >= Handler.maxWorldPos()) x = Handler.maxWorldPos();
		if (y <= Handler.minWorldPos()) y = Handler.minWorldPos();
		if (y >= Handler.maxWorldPos()) y = Handler.maxWorldPos();
	}
	
	// Key Input
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) vely = -speed;
		if (key == KeyEvent.VK_S) vely = speed;
		if (key == KeyEvent.VK_A) velx = -speed;
		if (key == KeyEvent.VK_D) velx = speed;
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) vely = 0;
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) velx = 0;
	}
	
	public void keyTyped(KeyEvent e) {}
}