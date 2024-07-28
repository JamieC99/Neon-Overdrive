package characters;

import java.awt.Graphics;

public class Character 
{
	protected int x, y;
	protected String name;
	
	public Character(int x, int y, String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public String getName() {return name;}
	
	public void tick() {}
	public void paintComponent(Graphics g) {}
}