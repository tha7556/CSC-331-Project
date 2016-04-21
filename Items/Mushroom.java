package Items;

import Game.Game;
/**
 * A red Mushroom that adds points to the scoreboard and makes Mario big
 * @author Andy
 *
 */
public class Mushroom extends Item
{

	public Mushroom(int x, int y, Game game)
	{
		super(x, y, 32, 32, "Images\\Mushroom.png", game);
	}

}