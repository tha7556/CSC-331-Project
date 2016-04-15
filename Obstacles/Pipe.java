package Obstacles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.Area;
import Game.Game;
import Game.Gameboard;

/**
 * An object that when Mario uses it, a different Area is displayed 
 *
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
		super(x,y,100,99,true,true, game, new ImageIcon("Pipe.png"));
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
		return new Rectangle(getX()+40,getY()-4,width/6,5);
	}
	@Override
	public void render(Graphics g, Gameboard c)
	{
		super.render(g,c);
		g.setColor(Color.YELLOW);
		Rectangle r = getBoundsMiddle();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
	}
}
