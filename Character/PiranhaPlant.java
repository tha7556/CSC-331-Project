package Character;

import Game.Game;

/**
 * An Enemy that hides in Pipes and pops out
 *
 */
public class PiranhaPlant extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public PiranhaPlant(int x, int y, Game game) {
		super(x, y, 16, 27, true,"Null", game);
		
	}
	

}
