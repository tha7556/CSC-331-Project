package Items;

import Game.Game;

/**
 *Extra Life Item that can be picked up by Mario and gives him 1 more life
 * @author Andy
 */
public class ExtraLife extends Item
{
	/**
	 * Creates an ExtraLife in the specified location on the Gameboard
	 * @param x The X coordinate of the coin
	 * @param y The Y coordinate of the coin
	 * @param game The instance of the Game
	 */
	public ExtraLife(int x, int y, Game game)
	{
		super(x, y, 32, 32, "Images\\GreenMushroom.png", game);
	}

}