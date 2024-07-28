package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}

	public void keyReleased(KeyEvent e)
	{

	}
	
	public void keyTyped(KeyEvent e) 
	{
		
	}
}