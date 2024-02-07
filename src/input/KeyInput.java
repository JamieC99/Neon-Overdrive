package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Handler;

public class KeyInput implements KeyListener
{
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) Handler.driveForwards();
		if (key == KeyEvent.VK_S) Handler.driveBackwards();
		if (key == KeyEvent.VK_A) Handler.turnRight();
		if (key == KeyEvent.VK_D) Handler.turnLeft();
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) Handler.stop();
		if (key == KeyEvent.VK_S) Handler.stop();
		if (key == KeyEvent.VK_A) Handler.centerTurn();
		if (key == KeyEvent.VK_D) Handler.centerTurn();
	}
	
	public void keyTyped(KeyEvent e) 
	{
		
	}
}