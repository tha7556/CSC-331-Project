package Character;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

import Game.Game;
import Game.Gameboard;




public abstract class Character {
	protected int x, y;
	protected int width, height;
	protected boolean solid = true;;
	protected int velX, velY;
	protected Game handler;
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

	public Character(int x, int y, int width, int height, boolean solid,String imageName, Game handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.handler = handler;
		File f = new File(imageName);
		if(!f.exists())
			throw new RuntimeException("Image File not found: " + imageName);
		else 
			this.image = new ImageIcon(imageName);
		
	}
	
	//Make these abstract so that each subclass must have different methods described by these methods
	public abstract void render(Graphics g, Gameboard c);
	public abstract void tick();
	
	public void die(){
		handler.getCurrentLevel().getCurrentArea().removeCharacter(this);
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSolid() {
		return solid;
	}
	public void setSolid(boolean s)
	{
		solid = s;
	}
	public void setVisible(boolean v)
	{
		visible = v;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	//Methods For Collision Detection
	public Rectangle getBounds(){
		return new Rectangle(getX()+5,getY()+2,width-5,height+5);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+5,getY(),width-5,5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+5,getY()+height,width-5,5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()+5,getY(), 5, height);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()-5+width,getY(), 5, height);
	}
	public boolean isAlive()
	{
		return alive;
	}

}
