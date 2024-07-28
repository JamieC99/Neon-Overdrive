package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

import world.*;

public class Main
{
	private Window window;
	
	private BufferedImage cityImage;
	private int cityImageWidth, cityImageHeight;
	
	private void initialize()
	{
		// Check each pixel of the buildingLayout image and spawn
		// the desired object based on the pixel colour
		// Red = Building, blue = plane
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
						Handler.addObject(new Building(j * 64 + 64, i * 64 + 64, 128, 128, height));
					
					// Create plane
					if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255)
						Handler.addObject(new Plane(j * 64 + 64, i * 64 + 64, 128, 128, 4));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Main()
	{
		initialize();
		
		window = new Window();
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