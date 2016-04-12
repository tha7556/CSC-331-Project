package Character;


import Game.Game;
/**
 * A basic Enemy 
 *
 */
public class Koopa extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Koopa(int x, int y, Game game) {
		super(x, y, 32, 54, true,"KoopaWalkLeft.gif", game);
		
	}
	

}
