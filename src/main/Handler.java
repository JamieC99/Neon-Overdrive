package main;

import input.ScreenButton;
import world.Building;
import world.Plane;
import characters.PlayerCharacter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Handler
{
	private static LinkedList<GameObject> objectList = new LinkedList<>();
	private static LinkedList<Character> characterList = new LinkedList<>();
	private static ArrayList<ScreenButton> buttonList = new ArrayList<>();
	
	public static PlayerCharacter player;
	
	public static boolean modifyingList = false;
	public static boolean showBounds = false;
	
	public static void initialize()
	{
		// Check each pixel of the buildingLayout image and spawn
		// the desired object based on the pixel colour
		// Red = Building, blue = plane
		BufferedImage cityImage;
		int cityImageWidth, cityImageHeight;
		try
		{
			File input = new File("resources/buildingLayout.png");
			cityImage = ImageIO.read(input);
			cityImageWidth = cityImage.getWidth();
			cityImageHeight = cityImage.getHeight();
			Random rand = new Random();
			
			for (int i = 0; i < cityImageHeight; i++)
			{
				for (int j = 0; j < cityImageWidth; j++)
				{
					Color c = new Color(cityImage.getRGB(j, i));
					
					// Create building
					int height = rand.nextInt(1, 4);
					if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0)
						Handler.addObject(new Building(j * 128 + 128, i * 128 + 128, 256, 256, height));
					
					// Create plane
					if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255)
						Handler.addObject(new Plane(j * 64 + 64, i * 64 + 64, 256, 256, 4));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		player = new PlayerCharacter(0, 0, "Player");
	}
	
	public static void paintComponent(Graphics g)
	{
		// Sort game objects
		for (GameObject object : objectList)
		{
			object.calculateDepth();
		}
		
		modifyingList = true;
		Collections.sort(objectList);
		Collections.sort(characterList);
		modifyingList = false;
		
		player.paintComponent(g);
		
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
		
		player.tick();
	}
	
	public static void collideWithObject()
	{
		
	}
	
	public static int minWorldPos() { return 768; }
	public static int maxWorldPos() { return 5375; }
	
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
}