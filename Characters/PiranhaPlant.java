package Characters;

import Game.Game;

/**
 * An Enemy that hides in Pipes and pops out
 *
 */
public class PiranhaPlant extends Enemy
{
	private int starty;
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public PiranhaPlant(int x, int y, Game game) {
		super(x, y, 32, 64, true,"Images\\PiranhaPlant.png", game);
		starty = y;
		setVelY(2);
	}
	
	public void tick() {
		if(getY()==starty-64){
			setVelY(2);
		}
		if(getY()==starty+64){
			setVelY(-2);
		}
		y+=velY;

	}

}
