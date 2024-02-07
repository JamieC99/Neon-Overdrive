package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import world.*;

public class Main
{
	private Window window;
	//public static LevelEditor editor;
	
	private BufferedImage cityImage;
	private int cityImageWidth, cityImageHeight;
	
	private void initialize()
	{
		// City boundaries
		//Handler.addObject(new Building(1024, 0, 2048, 1, 4)); // North wall
		//Handler.addObject(new Building(1024, 2048, 2048, 1, 4)); // South wall
		//Handler.addObject(new Building(2048, 1024, 1, 2048, 4)); // East wall
		//Handler.addObject(new Building(0, 1024, 1, 2048, 4)); // West wall
		
		// Check each pixel of the buildingLayout image and spawn
		// the desired object based on the pixel colour
		// Red = Building, blue = plane
		try
		{
			File input = new File("resources/buildingLayout.png");
			cityImage = ImageIO.read(input);
			cityImageWidth = cityImage.getWidth();
			cityImageHeight = cityImage.getHeight();
			
			for (int i = 0; i < cityImageHeight; i++)
			{
				for (int j = 0; j < cityImageWidth; j++)
				{
					Color c = new Color(cityImage.getRGB(j, i));
					
					// Create building
					if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0)
						Handler.addObject(new Building(j * 64 + 64, i * 64 + 64, 128, 128, 1));
					
					// Create plane
					if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255)
						Handler.addObject(new Plane(j * 64 + 64, i * 64 + 64, 128, 128, 2));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/*
		Handler.addObject(new Building(384, 320, 128, 128, 1));
		Handler.addObject(new Building(320, 640, 64, 64, 3));
		Handler.addObject(new Building(448, 640, 128, 32, 2));
		
		Handler.addObject(new Building(896, 320, 64, 128, 4));
		
		Handler.addObject(new Building(768, 448, 32, 64, 2));
		Handler.addObject(new Building(896, 448, 128, 32, 1));
		*/
	}
	
	public Main()
	{
		initialize();
		
		window = new Window();
		//editor = new LevelEditor();
		run();
	}
	
	private void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 144.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1)
			{
				tick();
				delta--;
			}
		}
	}
	
	private void tick()
	{
		Handler.tick();
		window.repaint();
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}