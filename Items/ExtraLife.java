package Items;
/**
 *Extra Life Item that can be picked up by Mario and gives him 1 more life
 */
public class ExtraLife extends Item
{
	/**
	 * Creates an ExtraLife in the specified location on the Gameboard
	 * @param x The X coordinate of the coin
	 * @param y The Y coordinate of the coin
	 */
	public ExtraLife(int x, int y)
	{
		super(x, y, 60, 60, "Currently Null");
	}

}