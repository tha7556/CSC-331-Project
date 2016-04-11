package Character;

import Game.Game;

/**
 * An Enemy that stays up high until the player comes near, then drops down
 *
 */
public class Thwomp extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Thwomp(int x, int y, Game game) {
		super(x, y, 16, 27, true,"Null", game);
		
	}
	

}
