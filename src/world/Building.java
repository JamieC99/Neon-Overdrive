package world;

import main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Building extends GeomObject
{
	
	private BufferedImage image;
	private BufferedImage buildingImage;
	
	public Building(int x, int y, int width, int height, int roofHeight)
	{
		super(x, y, width, height, roofHeight);
		
		try
		{
			buildingImage = ImageIO.read(new File("resources/building.png"));
		}
		catch(Exception e)
		{
			
		}
	}

	float topH = 0;
	float bottomH = 0;
	
	List<BufferedImage> imageSlices = new ArrayList<>();
	
	public void paintComponent(Graphics g)
	{
		/*
		// NORTH WALL
		if (Handler.player.getY() < y-heightHalf)
		{
			g.setColor(Color.BLUE);
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, x+widthHalf},                      new int[] { (int) (topY-heightHalf*scale), y-heightHalf, y-heightHalf },                  3); // NORTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },    new int[] { (int) (topY-heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // NORTH WALL TOP
		}
		
		// SOUTH WALL
		if (Handler.player.getY() > y+heightHalf)
		{
			g.setColor(Color.CYAN);
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, x+widthHalf },                     new int[] { (int) (topY+heightHalf*scale), y+heightHalf, y+heightHalf},                   3); // SOUTH WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },    new int[] { (int) (topY+heightHalf*scale), y+heightHalf, (int) (topY+heightHalf*scale) }, 3); // SOUTH WALL TOP
		}
		// EAST WALL
		if (Handler.player.getX() > x+widthHalf)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x+widthHalf,                  x+widthHalf, (int) (topX+widthHalf*scale) },     new int[] { y+heightHalf,                  y-heightHalf, (int) (topY+heightHalf*scale) }, 3); // EAST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX+widthHalf*scale), x+widthHalf, (int) (topX+widthHalf*scale) },     new int[] { (int) (topY+heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // EAST WALL TOP
		}
		
		// WEST WALL
		if (Handler.player.getX() < x-widthHalf)
		{
			g.setColor(Color.MAGENTA);
			g.fillPolygon(new int[] { x-widthHalf,                  x-widthHalf, (int) (topX-widthHalf*scale) },     new int[] { y+heightHalf,                  y-heightHalf, (int) (topY+heightHalf*scale) }, 3); // WEST WALL BOTTOM
			g.fillPolygon(new int[] { (int) (topX-widthHalf*scale), x-widthHalf, (int) (topX-widthHalf*scale) },     new int[] { (int) (topY+heightHalf*scale), y-heightHalf, (int) (topY-heightHalf*scale) }, 3); // WEST WALL TOP
		}
		*/
		
		
		
		
		if (Handler.player.getX() > x+widthHalf)
		{
			int imageResolution = 32;
			
			int roofEdge = (int) (topX+widthHalf*scale);
			int floorEdge = (int) (x + widthHalf);
			int edgeXDiff = Math.abs(floorEdge - roofEdge);
			int edgeYDiffTop = (int) (topY - heightHalf * scale) - (y - heightHalf);
			int edgeYDiffBottom = (int) (topY + heightHalf * scale) - (y + heightHalf);
			
			if (edgeXDiff > 1)
			{
				image = new BufferedImage(edgeXDiff, buildingImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				Graphics2D g2d = image.createGraphics();
				g2d.drawImage(buildingImage, 0, 0, edgeXDiff, image.getHeight(), null);
				g2d.dispose();
				
				int	segments = 1;
				if (edgeXDiff >= imageResolution)
					segments = (edgeXDiff / imageResolution);
				
				// Middle lines
				for (int i = 0; i < edgeXDiff; i += segments)
				{
					if (i > 0)
						g.drawImage(extractSubImage(image, i, 0, 1, image.getHeight()), roofEdge + (i - segments),
								(int) (topY - heightHalf * scale) - (int) topH,
								segments,
								(int) (height * scale) - (int) bottomH + (int) topH, null);
					
					topH = ((float) i / edgeXDiff) * edgeYDiffTop;
					bottomH = ((float) i / edgeXDiff) * edgeYDiffBottom;
				}
				
				g.drawImage(extractSubImage(image, image.getWidth() - segments, 0, segments, image.getHeight()), (x + widthHalf) - segments, y - heightHalf, segments, height, null);
			}
		}
		
		
		
		// ROOF BLOCK
		g.setColor(Color.WHITE);
		g.fillRect((int) (topX-widthHalf*scale), (int) (topY-heightHalf*scale), (int) (width*scale) + 1, (int) (height*scale) + 1);
		
		if (Handler.showBounds)
		{
			g.setColor(Color.RED);
			g.drawRect((int) getBounds().getX(), (int) getBounds().getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight());
		}
	}
	
	
	
	
	public BufferedImage extractSubImage(BufferedImage original, int x, int y, int width, int height)
	{
		int[] pixels = new int[width * height];
		original.getRGB(x, y, width, height, pixels, 0, width);
		
		BufferedImage subImage = new BufferedImage(width, height, original.getType());
		subImage.setRGB(0, 0, width, height, pixels, 0, width);
		
		return subImage;
	}
}