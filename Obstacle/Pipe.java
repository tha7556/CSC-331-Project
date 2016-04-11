package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;

/**
 * An object that when Mario uses it, a different Area is displayed 
 *
 */
public class Pipe extends Obstacle
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Pipe(int x, int y, Game game)
	{
		super(x,y,64,54,true,true, game, new ImageIcon("Null"));
	}
}
