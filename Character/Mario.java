package Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Gameboard;
import Obstacles.Brick;
import Obstacles.Ground;
import Obstacles.Obstacle;
import Obstacles.QuestionBlock;



public class Mario extends Character{
	
	private ImageIcon mario;
	private int jumpStart = -100000;
	public Mario(int x, int y, Game handler) {
		super(x, y, 60, 60, true,"MarioIdleRight.png", handler);
	}
	
	//what the character will look like
	public void render(Graphics g, Gameboard c) {
		if(this.jumping){
			if(facingRight)
				setImage("MarioJumpRight.png");
			else
				setImage("MarioJumpLeft.png");
		}		
		else if(this.runningRight){
			setImage("MarioWalkRight.gif");
		}
		
		else if(this.runningLeft){
			setImage("MarioWalkLeft.gif");
		}
		
		else if(this.standingStillRight){
			setImage("MarioIdleRight.png");
		}
		
		else if(this.standingStillLeft){
			setImage("MarioIdleLeft.png");
		}
		
		
		else{
			setImage("MarioIdleRight.png");
		}
		mario.paintIcon(handler, g, x-8, y);
		g.setColor(Color.RED);
		Rectangle r = getBounds();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.GREEN);
		r = getBoundsTop();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.GREEN);
		r = getBoundsBottom();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.BLUE);
		r = getBoundsLeft();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.BLUE);
		r = getBoundsRight();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void tick() {
		x += velX;
		y += velY;
		
		//if the current x or y is beyond or below the specified bounds, reset the shape to be at the bounds with the whole shape showing
		if(x <= 0) 
		{
			System.out.println("x<0");
			x=0;
		}
		if(y <= 0)
		{
			System.out.println("y<0");
			y = 0;
		}

		if(x + width >= handler.getWidth()) 
		{
			System.out.println("off screen");
			x = handler.getWidth() - width;
		}
		if(y + height >= handler.getHeight()) 
		{
			System.out.println("off screen");
			y = handler.getHeight() - height;
		}
		
		for(Obstacle t: handler.getCurrentLevel().getCurrentArea().getObstacles()){
			if(t.isSolid())
			{
				if(t instanceof QuestionBlock){
					if(getBoundsTop().intersects(t.getBoundsBottom())){
						handler.getCurrentLevel().getCurrentArea().removeObstacle(t);
					}
				}
				if(t instanceof Ground || t instanceof Brick){
					if(getBoundsTop().intersects(t.getBounds())){
						setVelY(0);
						if(jumping){
							jumping = false;
							gravity = 0.0;
							falling = true;
						}
					}
					if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(0);
						if(falling){
							falling = false;
						}
					}
					else{
						if(!falling && !jumping){
							gravity = 0.0;
							falling = true;
						}
					}
					if(getBoundsLeft().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX()+t.getWidth();
					}
					if(getBoundsRight().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX()-t.getWidth();
					}
				}
			}
		}
		
		for(Enemy enemy: handler.getCurrentLevel().getCurrentArea().getEnemies()){
			
				if(getBoundsBottom().intersects(enemy.getBoundsTop())){
					playSound("Jump.wav");
					jumping = true;
					falling = false;
					enemy.die();
					break;
				}
				if(getBoundsLeft().intersects(enemy.getBoundsRight())){
					die();
					break;
				}
				if(getBoundsRight().intersects(enemy.getBoundsLeft())){
					die();
					break;
				}
			
		}
		
		if(jumping){
			gravity -= 0.3;
			setVelY((int) -gravity);
			if(gravity <= 0.0){
				jumping = false;
				falling = true;
			}
		}
		
		if(falling){
			gravity += 0.3;
			setVelY((int) gravity);
		}
		
		
	}
	public void move(Set<Integer> activeKeys)
	{
		if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart + 55 < handler.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				gravity = 8.0;
				jumpStart = handler.getLoopNumber();
			}
		}
		else if(activeKeys.contains(KeyEvent.VK_SPACE) && activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart + 55 < handler.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				gravity = 8.0;
				jumpStart = handler.getLoopNumber();
				runningLeft = true;
				runningRight = false;
				standingStillLeft = true;
				standingStillRight = false;
				facingRight = false;
			}
		}
		else if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && activeKeys.contains(KeyEvent.VK_RIGHT)&& jumpStart + 55 < handler.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				gravity = 8.0;
				jumpStart = handler.getLoopNumber();
				runningRight = true;
				runningLeft = false;
				standingStillLeft = false;
				standingStillRight = true;
				facingRight = true;
			}
		}
		else if(activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT))
		{
			runningLeft = true;
			runningRight = false;
			standingStillLeft = true;
			standingStillRight = false;
			facingRight = false;
			setVelX(-5);
		}
		else if(activeKeys.contains(KeyEvent.VK_RIGHT) && !activeKeys.contains(KeyEvent.VK_LEFT))
		{
			runningRight = true;
			runningLeft = false;
			standingStillRight = true;
			standingStillLeft = false;
			facingRight = true;
			setVelX(5);
		}
		
		else if(!activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && !activeKeys.contains(KeyEvent.VK_SPACE))
		{
			runningLeft = false;
			runningRight = false;
			setVelX(0);
		}
	}
	public void playSound(String sound)
	{
		 try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	}
	public void setImage(String fileName)
	{
		mario = new ImageIcon(fileName);
	}
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

}
