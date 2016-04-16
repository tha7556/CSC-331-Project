package Obstacles;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

import Characters.Character;
import Game.Game;
import Game.Gameboard;


/**
 * Abstract Obstacle Class used to make Obstacles that Mario can interact with
 *
 */
public abstract class Obstacle 
{
	protected int x, y;
	protected int width;
	protected int height;
	protected boolean solid;
	protected boolean breakable;
	protected boolean visible = true;;
	protected int velX, velY;
	protected Game handler;
	protected ImageIcon image;
	/**
	 * 
	 * @param x X location on the Gameboard
	 * @param y Y location on the Gameboard
	 * @param width width of border collision rectangle (typically width of the image)
	 * @param height height of border collision rectangle (typically height of the image)
	 * @param solid True if Characters cannot walk through it 
	 * @param breakable True if Mario can break it
	 * @param game The instance of the Game
	 * @param image The ImageIcon representing the Obstacle
	 */
	public Obstacle(int x, int y, int width, int height, boolean solid,boolean breakable, Game game, ImageIcon image ){
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.height = height;
		this.solid = solid;
		this.handler = game;
		this.image = image;
	}
	/**
	 * Draws the Obstacle on the Gameboard
	 * @param g The Graphics Object
	 * @param c The Gameboard to draw on
	 */
	public void render(Graphics g, Gameboard c)
	{
		if(g != null)
		{
			image.paintIcon(c, g, x, y);
			g.setColor(Color.RED);
			g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
		}
		
	}
	
	/**
	 * Destroys the Obstacle
	 */
	public void die(){
		handler.getCurrentLevel().getCurrentArea().removeObstacle(this);
	}
	public ImageIcon getImage()
	{
		return this.image;
	}
	/**
	 * 
	 * @param imageName The String representation of the File where the new Image is 
	 */
	public void setImage(String imageName)
	{
		File f = new File(imageName);
		if(!f.exists())
		{
			throw new RuntimeException("Image File not found: " + imageName);
		}
		else 
			this.image = new ImageIcon(imageName);
	}
	/**
	 * 
	 * @return The X coordinate of the Obstacle on the Gameboard
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x The new X coordinate value
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return The Y coordinate of the Obstacle on the Gameboard
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y The new Y coordinate value
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return True if Characters can not walk through it
	 */
	public boolean isSolid() {
		return solid;
	}
	/**
	 * 
	 * @return True if Mario can destroy it
	 */
	public boolean isBreakable()
	{
		return breakable;
	}
	/**
	 * 
	 * @param solid True if Characters can walk through it
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	/**
	 * 
	 * @param visible True if the Obstacle is meant to be visible on the Gameboard
	 */
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	/**
	 * 
	 * @return True if the Obstacle is visible
	 */
	public boolean isVisible()
	{
		return this.visible;
	}
	/**
	 * 
	 * @param velX The new velocity of the Obstacle on the X axis
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}
	/**
	 * 
	 * @param velY The new velocity of the obstacle on the Y axis
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	/**
	 * 
	 * @return The Rectangle used in border collision calculations with {@link Character}s
	 */
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
	/**
	 * 
	 * @return The width of the border collision Rectangle, and usually the ImageIcon
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * 
	 * @param width The new width of the border collision Rectangle
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * 
	 * @return The height of the border collision Rectangle, and usually the ImageIcon
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * 
	 * @param height The new height of the border collision Rectangle
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * 
	 * @return The border collision Rectangle of the top part of the Obstacle
	 */
	public Rectangle getBoundsTop(){
		return new Rectangle(getX(),getY()-2,width,10);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the bottom part of the Obstacle
	 */
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+10,getY()+height,width,5);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the left part of the Obstacle
	 */
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX(),getY()+ 10, 5, height-20);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the right part of the Obstacle
	 */
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+width-5,getY()+ 10, 5, height-20);
	}
}
