package Items;

import Game.Game;

/**
 *Extra Life Item that can be picked up by Mario and gives him 1 more life
 */
public class ExtraLife extends Item
{
	/**
	 * Creates an ExtraLife in the specified location on the Gameboard
	 * @param x The X coordinate of the coin
	 * @param y The Y coordinate of the coin
	 */
	public ExtraLife(int x, int y, Game game)
	{
		super(x, y, 40, 40, "GreenMushroom.png", game);
	}

}