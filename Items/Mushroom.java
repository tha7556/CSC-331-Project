package Items;

import Game.Game;
/**
 * An Item that adds 500 points to the Scoreboard
 *
 */
public class Mushroom extends Item
{

	public Mushroom(int x, int y, Game game)
	{
		super(x, y, 40, 40, "RedMushroom.png", game);
	}

}