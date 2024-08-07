package input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener
{
	private static float mouseX, mouseY;
	
	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX()-8;
		mouseY = e.getY()-28;
	}

	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX()-8;
		mouseY = e.getY()-28;
	}
	
	public static float getMouseX()
	{
		return mouseX;
	}
	
	public static float getMouseY()
	{
		return mouseY;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}
	
	public static Rectangle getMouseBounds()
	{
		return new Rectangle((int) mouseX, (int) mouseY, 4, 4);
	}
}