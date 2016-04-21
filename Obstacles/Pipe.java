package Obstacles;


import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.Area;
import Game.Game;

/**
 * An object that when Mario uses it, a different Area is displayed 
 * @author Tyler
 */
public class Pipe extends Obstacle
{
	private Area area;
	private Pipe linkedPipe;
	
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 * @param area The Area that the Pipe links to
	 */
	public Pipe(int x, int y, Game game, Area area)
	{
		super(x,y,64,64,true,true, game, new ImageIcon("Images\\Pipe.png"));
		this.area = area;
	}
	/**
	 * Alternate Constructor with null Area
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Pipe(int x, int y, Game game)
	{
		this(x,y,game,null);
	}
	/**
	 * 
	 * @return The Area that the Pipe links to
	 */
	public Area getArea()
	{
		return area;
	}
	/**
	 * 
	 * @return The Pipe that this one links to
	 */
	public Pipe getLinkedPipe()
	{
		return linkedPipe;
	}
	/**
	 * Links this Pipe with another
	 * @param p The Pipe to be linked with
	 */
	public void setLinkedPipe(Pipe p)
	{
		this.linkedPipe = p;
	}
	/**
	 * Changes the linked Area to a different one
	 * @param newArea The new Area to be linked to
	 */
	public void setArea(Area newArea)
	{
		this.area = newArea;
	}
	/**
	 * Finds the border Rectangle where Mario can use the Pipe
	 * @return Rectangle representing the upper middle of the Pipe
	 */
	public Rectangle getBoundsMiddle()
	{
		return new Rectangle(getX()+27,getY()-4,width/6,5);
	}
}
