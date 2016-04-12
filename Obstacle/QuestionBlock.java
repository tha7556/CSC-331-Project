package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;
/**
 * Unbreakable Obstacle that when hit, turns brown and dispenses an Item
 *
 */
public class QuestionBlock extends Obstacle
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public QuestionBlock(int x, int y,Game game)
	{
		super(x, y,53,53,true,false,game, new ImageIcon("Question Block.png"));
	}
	@Override
	public void die()
	{
		this.setImage("Brown Block.png");
	}

}
