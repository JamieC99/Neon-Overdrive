package world;

import main.GameObject;
import main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GeomObject extends GameObject
{
	/** x position of the top of the object */
	protected float block2X;
	/** y position of the top of the object */
	protected float block2Y;
	/** Half of the object's width */
	protected int sizeXCut;
	/** Half of the object's height */
	protected int sizeYCut;
	/** Number divided to get the height of the object */
	protected int roofHeight = 2;
	/** The scale of the object. Controls perspective */
	protected float scale;
	
	public GeomObject(int x, int y, int width, int height, int roofHeight)
	{
		super(x, y);
		
		this.width = width;
		this.height = height;
		this.roofHeight = roofHeight;
		
		sizeXCut = width / 2;
		sizeYCut = height / 2;
		
		if (roofHeight == 1) scale = 2f;
		if (roofHeight == 2) scale = 1.5f;
		if (roofHeight == 3) scale = 1.375f;
		if (roofHeight == 4) scale = 1.25f;
	}

	public abstract void paintComponent(Graphics g);
	
	public void update()
	{
		block2X = (x - xDistToScreen/roofHeight);
		block2Y = (y - yDistToScreen/roofHeight);
		
		if (getBounds().intersects(Handler.getPlayerBounds()))
		{
			Handler.collideWithObject();
		}
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x - width / 2, y - height / 2, width, height);
	}
}