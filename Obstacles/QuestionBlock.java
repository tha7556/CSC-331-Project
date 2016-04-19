package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;
import Items.Coin;
import Items.ExtraLife;
import Items.Item;
import Items.Mushroom;
/**
 * Unbreakable Obstacle that when hit, turns brown and dispenses an Item
 *
 */
public class QuestionBlock extends Obstacle
{
	private Item item;
	private boolean dispensed = false;
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public QuestionBlock(int x, int y,Game game)
	{

		super(x, y,32,32,true,false,game, new ImageIcon("Images\\Question Block.png"));
		double a = Math.random();
		if(a > .4){
			item = new Coin(x+5,y-30,game);
		}
		else if(a > 0.1){
			item = new Mushroom(x,y-30,game);
		}
		else{
			item = new ExtraLife(x,y-30,game);
		}
		item.setVisible(false);
	}
	@Override
	public void die()
	{
		if(!dispensed)
		{
			this.setImage("Images\\Brown Block.png");
			this.item.setVisible(true);
			this.dispensed = true;
		}
	}
	/**
	 * 
	 * @return The Item in the Question Block
	 */
	public Item getItem()
	{
		return item;
	}
	/**
	 * 
	 * @return True if the Item has been dispensed, false otherwise
	 */
	public boolean isDispensed()
	{
		return dispensed;
	}
}
