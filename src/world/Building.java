package world;

import main.Handler;
import main.Window;

import java.awt.Color;
import java.awt.Graphics;

public class Building extends GeomObject
{
	public Building(int x, int y, int width, int height, int roofHeight)
	{
		super(x, y, width, height, roofHeight);
	}

	public void paintComponent(Graphics g)
	{
		// NORTH WALL
		if (Window.cameraY() < y-sizeYCut)
		{
			g.setColor(Color.BLUE);
			g.fillPolygon(new int[] { (int) (block2X-sizeXCut*scale), x-sizeXCut, x+sizeXCut},                      new int[] { (int) (block2Y-sizeYCut*scale), y-sizeYCut, y-sizeYCut },                     3); // NORTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (block2X-sizeXCut*scale), x+sizeXCut, (int) (block2X+sizeXCut*scale) }, new int[] { (int) (block2Y-sizeYCut*scale), y-sizeYCut, (int) (block2Y-sizeYCut*scale) }, 3); // NORTH WALL TOP
		}
		
		// SOUTH WALL
		if (Window.cameraY() > y+sizeYCut)
		{
			g.setColor(Color.CYAN);
			g.fillPolygon(new int[] { (int) (block2X-sizeXCut*scale), x-sizeXCut, x+sizeXCut },                     new int[] { (int) (block2Y+sizeYCut*scale), y+sizeYCut, y+sizeYCut},                      3); // SOUTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (block2X-sizeXCut*scale), x+sizeXCut, (int) (block2X+sizeXCut*scale) }, new int[] { (int) (block2Y+sizeYCut*scale), y+sizeYCut, (int) (block2Y+sizeYCut*scale) }, 3); // SOUTH WALL TOP
		}
		// EAST WALL
		if (Window.cameraX() > x+sizeXCut)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x+sizeXCut,                     x+sizeXCut, (int) (block2X+sizeXCut*scale) },  new int[] { y+sizeYCut,                     y-sizeYCut, (int) (block2Y+sizeYCut*scale) }, 3); // EAST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (block2X+sizeXCut*scale), x+sizeXCut, (int) (block2X+sizeXCut*scale) },  new int[] { (int) (block2Y+sizeYCut*scale), y-sizeYCut, (int) (block2Y-sizeYCut*scale) }, 3); // EAST WALL TOP
		}
		
		// WEST WALL
		if (Window.cameraX() < x-sizeXCut)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x-sizeXCut,                     x-sizeXCut, (int) (block2X-sizeXCut*scale) },  new int[] { y+sizeYCut,                     y-sizeYCut, (int) (block2Y+sizeYCut*scale) }, 3); // WEST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (block2X-sizeXCut*scale), x-sizeXCut, (int) (block2X-sizeXCut*scale) },  new int[] { (int) (block2Y+sizeYCut*scale), y-sizeYCut, (int) (block2Y-sizeYCut*scale) }, 3); // WEST WALL TOP
		}
		
		// ROOF BLOCK
		g.setColor(Color.BLACK);
		g.fillRect((int) (block2X-sizeXCut*scale), (int) (block2Y-sizeYCut*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		g.setColor(Color.WHITE);
		g.drawRect((int) (block2X-sizeXCut*scale), (int) (block2Y-sizeYCut*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		if (Handler.showBounds)
		{
			g.setColor(Color.RED);
			g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		}
	}
}