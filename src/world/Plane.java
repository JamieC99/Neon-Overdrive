package world;

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
		g.setColor(Color.BLACK);
		g.fillRect((int) (block2X-sizeXCut*scale), (int) (block2Y-sizeYCut*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		g.setColor(Color.WHITE);
		g.drawRect((int) (block2X-sizeXCut*scale), (int) (block2Y-sizeYCut*scale), (int) (width*scale) + 1, (int) (height*scale));
	}
}