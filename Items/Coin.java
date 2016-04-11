package Items;
/**
 *Coin Item that can be picked up by Mario and adds to the score on the Scoreboard
 */
public class Coin extends Item
{
	/**
	 * Creates a Coin in the specified location on the Gameboard
	 * @param x The X coordinate of the coin
	 * @param y The Y coordinate of the coin
	 */
	public Coin(int x, int y)
	{
		super(x, y, 60, 60, "Currently Null");
	}

}
