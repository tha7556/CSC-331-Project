package Obstacles;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.Game;
import Game.Gameboard;

/**
 * An object that triggers the next Area when crossed
 *
 */
public class FinishLine extends Obstacle
{
	private int barX, barY, barVelY;
	private ImageIcon bar;
	private boolean barVisible = true;
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public FinishLine(int x, int y, Game game)
	{
		super(x,y,160,384,true,true, game, new ImageIcon("Images\\FinishLine.png"));
		barX = x+33;
		barY = y+50;
		barVelY = 3;
		bar = new ImageIcon("Images\\FinishBar.png");
	}
	/**
	 * Used in collision detection with the bar
	 * @return The bounds Rectangle of the bar
	 */
	public Rectangle getBarBounds()
	{
		return new Rectangle(barX+5,barY+6,width/2-10,20);
	}
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x+(width/2)-30,y,width/2,height);
	}
	/**
	 * Moves the bar every time the Game loops, goes in the opposite direction when it gets to the top or bottom 
	 */
	public void tick()
	{
		barY += barVelY;
		if(barY <= y+30 || barY >= y+height-50)
			barVelY = -barVelY;
	}
	/**
	 * Draws the Finish Line as well as its bar
	 */
	@Override
	public void render(Graphics g, Gameboard c)
	{
		super.render(g, c);
		if(barVisible)
		{
			bar.paintIcon(c, g, barX, barY);
			g.drawRect((int)getBarBounds().getX(), (int)getBarBounds().getY(), (int)getBarBounds().getWidth(), (int)getBarBounds().getHeight());
		}
	}
	/**
	 * 
	 * @param v True if the bar should be visible, false otherwise
	 */
	public void setBarVisiblity(boolean v)
	{
		barVisible = v;
	}
}
