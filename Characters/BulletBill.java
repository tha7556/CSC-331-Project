package Characters;

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
		super(x, y, 32, 32, true,"Images\\Bullet.png", game);
		setVelX(-4);		
	}
	@Override
	public void tick() {
		if(dead) //Death Animation
		{
			if(game.getLoopNumber() - dieStart > 10)
			{
				alive = false;
				die();
			}
			return;
		}
		x += velX;

	}

}
