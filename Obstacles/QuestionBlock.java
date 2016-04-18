package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;
/**
 * Unbreakable Obstacle that when hit, turns brown and dispenses an Item
 *
 */
public class QuestionBlock extends Obstacle
{
	private boolean coin;
	private boolean mushroom;
	private boolean life;
	private boolean dispensed = false;
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public QuestionBlock(int x, int y,Game game)
	{

		super(x, y,53,53,true,false,game, new ImageIcon("Question Block.png"));
		double a = Math.random();
		if(a > .4){
			coin = true;
		}
		else if(a > 0.1){
			mushroom = true;
		}
		else{
			life = true;
		}
	}
	@Override
	public void die()
	{
		this.setImage("Brown Block.png");
		this.setDispensed(true);
	}
	/**
	 * 
	 * @return True if the block contains a coin
	 */
	public boolean hasCoin() {
		return coin;
	}
	/**
	 * 
	 * @return True if the block contains a mushroom
	 */
	public boolean hasMushroom(){
		return mushroom;
	}
	/**
	 * 
	 * @return True if the block contains an extra life
	 */
	public boolean hasLife(){
		return life;
	}
	/**
	 * 
	 * @return True if the block has already been hit
	 */
	public boolean isDispensed()
	{
		return dispensed;
	}
	/**
	 * 
	 * @param d True if the block has already been hit, false otherwise
	 */
	public void setDispensed(boolean d)
	{
		dispensed = d;
	}
}
