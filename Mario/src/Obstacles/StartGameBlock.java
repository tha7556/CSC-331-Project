package Obstacles;


import javax.swing.ImageIcon;

import Game.Game;
/**
 * Block to hit to start the Game
 * @author Jacob
 *
 */
public class StartGameBlock extends Obstacle{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public StartGameBlock(int x, int y, Game game){
		super(x, y,32,32,true,false,game, new ImageIcon("Images\\StartGameBlock.png"));

	}

}
