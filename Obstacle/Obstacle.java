package Obstacles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.Game;



public class Obstacle 
{
	protected int x, y;
	protected int width;
	protected int height;
	protected boolean solid;
	protected boolean visible = true;;
	protected int velX, velY;
	protected Game handler;
	protected ImageIcon image;

	public Obstacle(int x, int y, int width, int height, boolean solid,Game handler, ImageIcon image ){
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.height = height;
		this.solid = solid;
		this.handler = handler;
		this.image = image;
	}
	
	public void render(Graphics g, Component c)
	{
		if(g != null)
		{
			image.paintIcon(c, g, x, y);
			g.setColor(Color.red);
			g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
		}
		
	}
	

	public void die(){
		handler.getCurrentLevel().getCurrentArea().removeObstacle(this);
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

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	public boolean isVisible()
	{
		return this.visible;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	//Collision Detection
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	//Methods  
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+10,getY()-2,width,5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+10,getY()+height,width,5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX(),getY()+ 10, 5, height-20);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+width-5,getY()+ 10, 5, height-20);
	}
}
