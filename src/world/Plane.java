package world;

import main.Handler;

import java.awt.Color;
import java.awt.Graphics;

public class Plane extends GeomObject
{
	public Plane(int x, int y, int width, int height, int roofHeight)
	{
		super(x, y, width, height, roofHeight);
	}

	public void paintComponent(Graphics g)
	{
		// ROOF BLOCK
		g.setColor(Color.BLACK);
		g.fillRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		g.setColor(Color.WHITE);
		g.drawRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		if (Handler.showBounds)
		{
			g.setColor(Color.RED);
			g.drawRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight());
		}
	}
}