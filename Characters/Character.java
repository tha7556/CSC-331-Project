package Characters;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Gameboard;



/**
 * An abstract class that Mario and the Enemies extend
 * @author Jacob
 */
public abstract class Character {
	protected int x, y;
	protected int width, height;

	protected boolean solid = true;;
	protected int velX, velY;
	protected Game game;
	protected ImageIcon image;
	
	public boolean runningRight = false;
	public boolean runningLeft = false;
	public boolean standingStillRight = true;
	public boolean standingStillLeft = false;
	public boolean facingRight = true;
	public boolean jumping = false;
	public boolean falling = true;
	public double gravity = 0.0;
	
	protected boolean alive = true;
	protected boolean visible = true;
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
	public Character(int x, int y, int width, int height, boolean solid,String imageName, Game game){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.game = game;
		File f = new File(imageName);
		if(!f.exists())
			throw new RuntimeException("Image File not found: " + imageName);
		else 
			this.image = new ImageIcon(imageName);
		
	}
	/**
	 * Abstract method that displays the {@link Character} on the Gameboard
	 * @param g The Graphics object
	 * @param c The Gameboard to draw on
	 */
	public abstract void render(Graphics g, Gameboard c);
	/**
	 * Abstract method that is called every time the game loops and is where movement happens
	 */
	public abstract void tick();
	/**
	 * Removes the {@link Character} from the Gameboard
	 */
	public void die(){
		game.getCurrentLevel().getCurrentArea().removeCharacter(this);
	}
	
	/**
	 * 
	 * @return The x coordinate of the {@link Character} on the Gameboard
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @return The Height of the Character
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * 
	 * @param height The new Height of the Character
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * 
	 * @return The width of the Character
	 */
	public int getWidth()
	{
		return width;
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
	 * @return The y coordinate of the {@link Character} on the Gameboard
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y The new y coordinate value
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return True if border collision should be calculated for the {@link Character}
	 */
	public boolean isSolid() {
		return solid;
	}
	/**
	 * 
	 * @param s True if border collision is intended to be calculated for the {@link Character}
	 */
	public void setSolid(boolean s)
	{
		solid = s;
	}
	/**
	 * 
	 * @param v True if the {@link Character} should be displayed on the Gameboard
	 */
	public void setVisible(boolean v)
	{
		visible = v;
	}
	/**
	 * 
	 * @return True if the Character is Visible
	 */
	public boolean isVisible()
	{
		return visible;
	}
	/**
	 * 
	 * @param velX The new velocity of the {@link Character} on the X axis
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}
	/**
	 * 
	 * @param velY The new velocity of the {@link Character} on the Y axis
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
	/**
	 * 
	 * @return The velocity of the {@link Character} on the X axis
	 */
	public int getVelX(){
		return velX;
	}
	/**
	 * 
	 * @return The velocity of the {@link Character} on the Y axis
	 */
	public int getVelY(){
		return velY;
	}
	/**
	 * 
	 * @return The Rectangle used in border collision calculations
	 */
	public Rectangle getBounds(){
		return new Rectangle(getX()+5,getY()+2,width-5,height+5);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the top part of the {@link Character}
	 */
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+10,getY(),width-15,5);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the bottom part of the {@link Character}
	 */
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+10,getY()+height+2,width-15,6);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the left part of the {@link Character}
	 */
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()+5,getY()+10, 5, height-15);
	}
	/**
	 * 
	 * @return The border collision Rectangle of the right part of the {@link Character}
	 */
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()-5+width,getY()+10, 5, height-15);
	}
	/**
	 * 
	 * @return True if the {@link Character} is still alive
	 */
	public boolean isAlive()
	{
		return alive;
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
	 * Plays a sound effect
	 * @param sound The String representation of the sound File
	 */
	public void playSound(String sound)
	{
		try 
		{
			 File f = new File(sound);
			 if(!f.exists())
			 {
				 throw new RuntimeException("Music File not found: " + sound);
			 }
			 else
			 {
				 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
			     Clip clip = AudioSystem.getClip();
			     clip.open(audioInputStream);
			     clip.start();
			 }
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Moves the {@link Character} to the new Location
	 * @param x The new X coordinate on the Gameboard
	 * @param y The new Y coordinate on the Gameboard
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

}
