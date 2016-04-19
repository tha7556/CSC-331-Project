package Items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.Game;
import Game.Gameboard;
/**
 * Abstract Item class used to make coins extra lives, 
 * 
 *
 */
public abstract class Item 
{
	protected int x, y, width, height, velX, velY;
	protected ImageIcon image;
	protected boolean visible, alive;
	protected Game game;
	/**
	 * 
	 * @param x X location on the Gameboard
	 * @param y Y location on the Gameboard
	 * @param width width of border collision rectangle (typically width of the image)
	 * @param height height of border collision rectangle (typically height of the image)
	 * @param imageFile the image representing the Item
	 */
	public Item(int x, int y, int width, int height, String imageFile, Game game)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = new ImageIcon(imageFile);
		this.visible = true;
		this.alive = true;
		this.game = game;
	}
	/**
	 * 
	 * @return True if the Item is visible on the screen
	 */
	public boolean isVisible()
	{
		return visible;
	}
	/**
	 * 
	 * @return True if the Item has not fallen off of the screen or been used by Mario
	 */
	public boolean isAlive()
	{
		return alive;
	}
	/**
	 * 
	 * @return The ImageIcon representing the Item on the Gameboard
	 */
	public ImageIcon getImage()
	{
		return image;
	}
	/**
	 * 
	 * @return The X coordinate of the Item on the Gameboard
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * 
	 * @return The Y coordinate of the Item on the Gameboard
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * 
	 * @return The velocity of the Item as it moves along the X axis
	 */
	public int getVelX()
	{
		return velX;
	}
	/**
	 * 
	 * @return The velocity of the Item as it moves along the Y axis
	 */
	public int getVelY()
	{
		return velY;
	}
	/**
	 * 
	 * @return The width of the border collision Rectangle, and usually the ImageIcon
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * 
	 * @return The height of the border collision Rectangle, and usually the ImageIcon
	 */
	public int getHeight()
	{
		return height;
	}
	/**
	 * 
	 * @param x The new X coordinate on the Gameboard
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	/**
	 * 
	 * @param y The new Y coordinate on the Gameboard
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	/**
	 * 
	 * @param vel The new velocity as the Item moves on the X axis
	 */
	public void setVelX(int vel)
	{
		this.velX = vel;
	}
	/**
	 * 
	 * @param vel The new velocity as the Item moves on the Y axis
	 */
	public void setVelY(int vel)
	{
		this.velY = vel;
	}
	/**
	 * 
	 * @param v True if the Item is meant to be visible on the Gameboard
	 */
	public void setVisible(boolean v)
	{
		visible = v;
	}
	/**
	 * 
	 * @param a True if the Item has not fallen off of the Gameboard or been used by Mario
	 */
	public void setAlive(boolean a)
	{
		alive = a;
	}
	/**
	 * 
	 * @return The Rectangle used in border collision calculations with Mario
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
	/**
	 * Draws the Item on the Gameboard
	 * @param g The Graphics object
	 * @param c The Gameboard to draw on
	 */
	public void render(Graphics g, Gameboard c)
	{
		if(g != null)
		{
			image.paintIcon(c, g, x, y);
			g.setColor(Color.red);
			g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
		}
		
	}
	public void die() {
		game.getCurrentLevel().getCurrentArea().removeItem(this);		
	}
}
