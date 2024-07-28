package input;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import main.Handler;

public class ScreenButton 
{
	private int x, y;
	private String function;
	
	public ScreenButton(int x, int y, String function)
	{
		this.x = x;
		this.y = y;
		this.function = function;
	}
	
	public String getFunction()
	{
		return function;
	}
	
	public void paintComponent(Graphics g)
	{
		if (function == "drive forwards")
			g.drawImage(new ImageIcon("resources/interface/arrowUpButton.png").getImage(), x - 32, y - 64, null);
		if (function == "drive backwards")
			g.drawImage(new ImageIcon("resources/interface/arrowDownButton.png").getImage(), x - 32, y - 64, null);
	}
	
	public void function()
	{
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x-32, y - 64, 64, 128);
	}
}