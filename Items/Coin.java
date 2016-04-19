package Items;

import Game.Game;

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
	public Coin(int x, int y, Game game)
	{
		super(x, y, 32, 32, "Coin.gif", game);
	}

}
