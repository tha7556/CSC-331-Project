package Characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Game;
import Game.Gameboard;



/**
 * Extends the {@link Character} class to create Enemies
 * @author Jacob
 *
 */
public class Enemy extends Character{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param width width of border collision rectangle (typically width of the image)
	 * @param height height of border collision rectangle (typically height of the image)
	 * @param solid True if border collision should be calculated 
	 * @param imageName The filename of the {@link Character}'s ImageIcon
	 * @param game The instance of the Game
	 */
	protected int dieStart = -1;
	protected boolean dead = false;
	public Enemy(int x, int y, int width, int height, boolean solid,String imageName, Game game) {
		super(x, y, width, height, solid, imageName, game);
		setVelX(1);
		
	}
	@Override
	public void render(Graphics g, Gameboard c) {
		this.image.paintIcon(c, g, x, y);		
	}

	/**
	 * 
	 * @return The Rectangle used in border collision calculations
	 */
	public Rectangle getBounds(){
		return new Rectangle(getX()+5,getY()+2,width-5,height+5);
	}
	@Override
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+5,getY(),width-5,5);
	}
	@Override
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+5,getY()+height,width-5,5);
	}
	@Override
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()+2,getY()+10, 5, height-20);
	}
	@Override
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()-5+width,getY()+10, 5, height-20);
	}
	@Override
	public void tick() {
		
	}
	
}
