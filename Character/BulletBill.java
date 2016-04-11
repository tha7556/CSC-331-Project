package Character;

import Game.Game;

/**
 * A giant Enemy that flies across the Screen
 *
 */
public class BulletBill extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public BulletBill(int x, int y, Game game) {
		super(x, y, 16, 27, true,"Null", game);
		
	}
	

}
