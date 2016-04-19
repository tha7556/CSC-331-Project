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
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public QuestionBlock(int x, int y,Game game)
	{

		super(x, y,32,32,true,false,game, new ImageIcon("Question Block.png"));
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
	}
	public boolean hasCoin() {
		return coin;
	}
	public boolean hasMushroom(){
		return mushroom;
	}
	public boolean hasLife(){
		return life;
	}
}
