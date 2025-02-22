package world;

import java.awt.Color;
import java.awt.Graphics;

public class ImageSlice extends GeomObject
{
	public ImageSlice(int x, int y, int width, int height, int roofHeight)
	{
		super(x, y, width, height, roofHeight);
	}

	public void paintComponent(Graphics g)
	{
		roofHeight = 4;
		g.setColor(Color.BLACK);
		g.fillRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
		
		g.setColor(Color.WHITE);
		g.drawRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale));
	}
}