package main;


public class Main
{
	private Window window;
	
	public Main()
	{
		Handler.initialize();
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
		window.tick();
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}