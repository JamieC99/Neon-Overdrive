package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject implements Comparable<GameObject>
{
	protected int x, y, width, height;
	/** Initial x position */
	protected int xOrigin;
	/** Initial y position */
	protected int yOrigin;
	/** The x distance from the object to the center of the screen */
	protected float xDistToScreen;
	/** The y distance from the object to the center of the screen */
	protected float yDistToScreen;
	/** The true distance from the object to the center of the screen */
	protected int depth;
	
	public GameObject(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		xOrigin = x;
		yOrigin = y;
	}
	
	public abstract void paintComponent(Graphics g);
	
	/** Calculate the position of the object and call it's update method */
	public void tick()
	{
		x = (int) (xOrigin + Handler.getWorldX());
		y = (int) (yOrigin + Handler.getWorldY());
		
		xDistToScreen = (Window.cameraX() - x);
		yDistToScreen = (Window.cameraY() - y);
		
		update();
	}
	
	/** Update method which sub classes override to implement their own functionality */
	protected void update()
	{
		
	}
	
	/** Return the depth of an object (It's distance from the center of the screen) */
	public int getDepth()
	{
		return depth;
	}
	
	/** Calculate the object's distance to the center of the screen */
	public void calculateDepth()
	{
		depth = (int) Math.abs(Math.sqrt(xDistToScreen * xDistToScreen + yDistToScreen * yDistToScreen));
	}
	
	public Rectangle getBounds()
	{
		return null;
	}
	
	/** Compare objects based on their depth */
    public int compareTo(GameObject otherObject)
	{
        if (this.depth < otherObject.depth)
        {
            return 1;
        }
        else if (this.depth > otherObject.depth)
        {
            return -1;
        }
        return 0;
    }
}