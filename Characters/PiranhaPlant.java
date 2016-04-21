package Characters;

import Game.Game;

/**
 * 
 * An Enemy that hides in Pipes and pops out
 * @author Jacob
 *
 */
public class PiranhaPlant extends Enemy
{
	private double speed;
	private int starty, start;
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
			speed -= 6;
		}
		if(speed < 1)
			speed += 2;
		start = game.getLoopNumber();
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
		if(game.getLoopNumber() - start > 300)
		{
			recalculateSpeed();
			start = game.getLoopNumber();
		}
	}
	/**
	 * Sets Speed to a random number
	 */
	public void recalculateSpeed()
	{
		speed = Math.random()*10;
		if(speed > 6){
			speed -= 6;
		}
		if(speed < 1)
			speed += 2;
	}

}
