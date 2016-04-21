package Obstacles;

import javax.swing.ImageIcon;
import Game.Game;
/**
 * 
 * @param x The x location on the Gameboard
 * @param y The y location on the Gameboard
 * @param game The instance of the Game
 * @author Tyler
 */
public class EndGameBlock extends Obstacle{

	public EndGameBlock(int x, int y, Game game){
		super(x, y,32,32,true,false,game, new ImageIcon("Images\\EndGameBlock.png"));

	}
	

}