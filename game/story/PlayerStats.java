package story;

public class PlayerStats 
{
	private static int playerHealth = 100;
	private static int playerMoneyInHand;
	private static int playerMoneyInBank;
	
	public static int GetPlayerHealth() { return playerHealth; }
	public static int GetPlayerMoneyInHand() { return playerMoneyInHand; }
	public static int GetPlayerMoneyInBank() { return playerMoneyInBank; }
	
	public static void AdjustPlayerMoneyInHand(int amount) { playerMoneyInHand += amount; }
	public static void AdjustPlayerMoneyInBank(int amount) { playerMoneyInBank += amount; }
}