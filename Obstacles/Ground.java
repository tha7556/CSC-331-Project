package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;
/**
 * An unbreakable Obstacle that represents the bottom of the Area
 *
 */
public class Ground extends Obstacle
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Ground(int x, int y, Game game)
	{
		super(x,y,32,32,true,false,game,new ImageIcon("Images\\Ground.png"));
	}
}
