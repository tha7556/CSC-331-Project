package Characters;

import Game.Game;

/**
 * An Enemy that hides in Pipes and pops out
 *
 */
public class PiranhaPlant extends Enemy
{
	private double speed;
	private int starty;
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public PiranhaPlant(int x, int y, Game game) {
		super(x, y, 32, 64, true,"Images\\PiranhaPlant.png", game);
		speed = Math.random()*10;
		if(speed > 6){
			speed -= 5;
		}
		
		starty = y;
		setVelY(2);
	}
	
	public void tick() {
		y+=velY;
		if(getY() <= starty-64){
			setVelY((int)speed);
		}
		if(getY() >= starty+64){
			setVelY(-(int)speed);
		}
	}

}
