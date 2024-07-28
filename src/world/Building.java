package world;

import main.Handler;

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
		if (Handler.player.getY() < y-heightHalf)
		{
			g.setColor(Color.BLUE);
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, x+widthHalf},                      new int[] { (int) (topY-heightHalf*scale), y-heightHalf, y-heightHalf },                  3); // NORTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },    new int[] { (int) (topY-heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // NORTH WALL TOP
		}
		
		// SOUTH WALL
		if (Handler.player.getY() > y+heightHalf)
		{
			g.setColor(Color.CYAN);
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, x+widthHalf },                     new int[] { (int) (topY+heightHalf*scale), y+heightHalf, y+heightHalf},                   3); // SOUTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },    new int[] { (int) (topY+heightHalf*scale), y+heightHalf, (int) (topY+heightHalf*scale) }, 3); // SOUTH WALL TOP
		}
		// EAST WALL
		if (Handler.player.getX() > x+widthHalf)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x+widthHalf,                  x+widthHalf, (int) (topX+widthHalf*scale) },     new int[] { y+heightHalf,                  y-heightHalf, (int) (topY+heightHalf*scale) }, 3); // EAST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX+widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },     new int[] { (int) (topY+heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // EAST WALL TOP
		}
		
		// WEST WALL
		if (Handler.player.getX() < x-widthHalf)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x-widthHalf,                  x-widthHalf, (int) (topX-widthHalf*scale) },     new int[] { y+heightHalf,                  y-heightHalf, (int) (topY+heightHalf*scale) }, 3); // WEST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, (int) (topX-widthHalf*scale) },     new int[] { (int) (topY+heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // WEST WALL TOP
		}
		
		// ROOF BLOCK
		g.setColor(Color.BLACK);
		g.fillRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		g.setColor(Color.WHITE);
		g.drawRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		if (Handler.showBounds)
		{
			g.setColor(Color.RED);
			g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		}
	}
}