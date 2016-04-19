package Characters;

import Game.Game;

/**
 * An Enemy that stays up high until the player comes near, then drops down
 *
 */
public class Thwomp extends Enemy
{
	private int starty;

	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Thwomp(int x, int y, Game game) {
		super(x, y, 64, 96, true,"Images\\Thwomp.png", game);
		
		starty = y-46;
		setVelY(2);
	}
	
	public void tick() {
		if(getY()==starty){
			setVelY(2);
			if(getX()>game.getMario().getX()&&getX()+64<game.getMario().getX()+32){
				setVelY(5);
			}
		}
		if(getY()==starty+256+128){
			setVelY(-2);
		}
		y+=velY;

	}
	

}
