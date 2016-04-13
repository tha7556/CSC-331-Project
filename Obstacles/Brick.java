package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;
/**
 * A breakable Obstacle 
 *
 */
public class Brick extends Obstacle
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Brick(int x, int y, Game game)
	{
		super(x,y,64,54,true,true, game, new ImageIcon("Brick.png"));
	}
}
