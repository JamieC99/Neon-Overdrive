package main;  

import input.ScreenButton;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Handler
{
	private static LinkedList<GameObject> objectList = new LinkedList<>();
	private static ArrayList<ScreenButton> buttonList = new ArrayList<>();
	
	public static boolean modifyingList = false;
	public static boolean showBounds = false;
	
	private static float worldX, worldY;
	private static float worldXShift, worldYShift;
	
	private static float direction;
	private static float turnRate;
	private static float speed;
	private static float acceleration;
	
	private static boolean driving;
	private static String steeringDirection = "";
	
	public static void paintComponent(Graphics g)
	{
		// Sort game objects
		for (GameObject object : objectList)
		{
			object.calculateDepth();
		}
		
		modifyingList = true;
		Collections.sort(objectList);
		modifyingList = false;
		
		// Draw game objects
		for (GameObject object : objectList)
		{
			object.paintComponent(g);
		}
	}
	
	public static void paintButtons(Graphics g)
	{
		for (ScreenButton button : buttonList)
		{
			button.paintComponent(g);
		}
	}
	
	public static void tick()
	{
		if (!modifyingList)
		{
			for (int i = 0; i < objectList.size(); i++)
			{
				if (!modifyingList)
				{
					GameObject object = objectList.get(i);
				
					object.tick();
				}
			}
		}
		
		control();
	}
	
	private static void control()
	{
		// Rotation and world positioning
		worldXShift = (float) (Math.cos(Math.toRadians(direction-90)) * acceleration);
		worldYShift = (float) (Math.sin(Math.toRadians(direction-90)) * acceleration);
		
		worldX -= worldXShift;
		worldY += worldYShift;
		direction += turnRate;
		
		// Reset rotation
		if (direction <= 0)	direction += 360;
		if (direction >= 360) direction = 0;
		
		if (acceleration >= 4) acceleration = 4;
		else if (acceleration <=- 4) acceleration =- 4;
		
		if (driving)
			acceleration += speed / 100;
		else
		{
			if (acceleration > 0.1f) acceleration -= 0.01f;
			else if (acceleration <- 0.1f) acceleration += 0.01f;
			else acceleration = 0;
		}
		
		if (steeringDirection == "right") turnRate =- (acceleration / 5);
		else if (steeringDirection == "left") turnRate = (acceleration / 5);
		else turnRate = 0;
	}
	
	public static void collideWithObject()
	{
		//acceleration =- acceleration;
	}
	
	public static Rectangle getPlayerBounds()
	{
		return new Rectangle(Window.getWindowWidth() / 2 - 16, Window.getWindowHeight() / 2 - 32, 32, 64);
	}
	
	public static void addObject(GameObject object)
	{
		objectList.add(object);
	}
	
	public static void removeObject(GameObject object)
	{
		objectList.remove(object);
	}
	
	public static LinkedList<GameObject> getObjectList()
	{
		return objectList;
	}
	
	/** Return the world's x position */
	public static float getWorldX()
	{
		return worldX;
	}
	/** Return the world's y position */
	public static float getWorldY()
	{
		return worldY;
	}
	
	public static float getWorldXShift()
	{
		return worldXShift;
	}
	
	public static float getWorldYShift()
	{
		return worldYShift;
	}
	
	public static void driveForwards()
	{
		driving = true;
		speed =- 2;
	}
	
	public static void driveBackwards()
	{
		driving = true;
		speed = 2;
	}
	
	public static void stop()
	{
		driving = false;
		speed = 0;
	}
	
	public static void turnLeft()
	{
		steeringDirection = "left";
	}
	
	public static void turnRight()
	{
		steeringDirection = "right";
	}
	
	public static void centerTurn()
	{
		steeringDirection = "";
	}
	
	public static float getDirection()
	{
		return direction;
	}
}