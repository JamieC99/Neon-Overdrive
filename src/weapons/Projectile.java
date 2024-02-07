package weapons;

import input.MouseInput;
import main.GameObject;
import main.Handler;
import main.Window;
import world.Building;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject
{
	private float speed;
	private float direction;
	private float x, y;
	
	public Projectile(int x, int y, int speed)
	{
		super(x, y);
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		width = 4;
		height = 4;
		
		// Calculate the projectile's direction
		direction = (float) Math.atan2(MouseInput.getMouseY() - Window.cameraY() * Window.getFrameScale(), 
				MouseInput.getMouseX() - Window.cameraX() * Window.getFrameScale());
		// Convert the direction to degrees and offset by the screen's direction
		direction = (float) Math.toDegrees(direction) - Handler.getDirection();
		
		depth = 999999999;
	}

	public void paintComponent(Graphics g)
	{
		// Draw bullet
		g.setColor(Color.CYAN);
		g.fillRect((int) x - width / 2, (int) y - height / 2, width, height);
		
		// Draw bounding box
		if (Handler.showBounds)
		{
			g.setColor(Color.RED);
			g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		}
	}
	
	public void update()
	{
		x += (float) (Math.cos(Math.toRadians(direction)) * speed) - Handler.getWorldXShift();
		y += (float) (Math.sin(Math.toRadians(direction)) * speed) + Handler.getWorldYShift();
		
		for (int i = 0; i < Handler.getObjectList().size(); i++)
		{
			GameObject object = Handler.getObjectList().get(i);
			
			if (object instanceof Building)
				if (getBounds().intersects(object.getBounds()))
					destroy();
		}
	}
	
	public void calculateDepth()
	{
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x - width / 2, (int) y - height / 2, width, height);
	}
	
	private void destroy()
	{
		Handler.modifyingList = true;
		Handler.removeObject(this);
		Handler.modifyingList = false;
	}
}