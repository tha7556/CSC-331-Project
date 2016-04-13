package Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Set;


import Game.Game;
import Game.Gameboard;
import Obstacles.Brick;
import Obstacles.Ground;
import Obstacles.Obstacle;
import Obstacles.Pipe;
import Obstacles.QuestionBlock;


/**
 * The {@link Character} that the user will control
 *
 */
public class Mario extends Character{
	
	private int jumpStart = -1;
	private boolean ducking = false;
	private boolean onGround = false;
	//Pipe variables
	private boolean goingDownPipe = false;
	private boolean insidePipe = false;
	private Pipe pipe;
	private int pipeStart = -1;
	
	/**
	 * 
	 * @param x The X location on the Gameboard
	 * @param y The Y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Mario(int x, int y, Game game) {
		super(x, y, 60, 60, true,"MarioIdleRight.png", game);
	}
	@Override
	public void render(Graphics g, Gameboard c) {
		if(goingDownPipe)
		{
			setImage("MarioDownPipe.png");
		}
		else if(this.jumping){
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
		
		else if(this.standingStillRight && !ducking){
			setImage("MarioIdleRight.png");
		}
		
		else if(this.standingStillLeft && !ducking){
			setImage("MarioIdleLeft.png");
		}
		else if(facingRight && ducking)
		{
			setImage("MarioDuckRight.png");
		}
		else if(!facingRight && ducking)
		{
			setImage("MarioDuckLeft.png");
		}
		
		
		else{
			setImage("MarioIdleRight.png");
		}
		image.paintIcon(game, g, x-8, y);
		if(pipe != null)
			pipe.render(g, c);
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
		
		g.setColor(Color.BLUE);
		r = getMiddleBound();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	}
	@Override
	public void tick() {
		//Pipes
		if(goingDownPipe)
		{
			x = pipe.getX()+(pipe.getWidth()/4);
			if(game.getLoopNumber() - pipeStart < 35)
				y += 3;
			else
			{
				goingDownPipe = false;
				insidePipe = true;
			}	
			return;
		}
		//Normal Moving
		x += velX;
		y += velY;
		onGround = isOnGround();
		if(!onGround && !jumping)
			falling = true;
		else if(onGround)
			falling = false;
		Rectangle top;
		if(ducking)
			top = getMiddleBound();
		else 
			top = getBoundsTop();
		
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

		if(x + width >= game.getWidth()) 
		{
			System.out.println("off screen");
			x = game.getWidth() - width;
		}
		if(y + height >= game.getHeight()) 
		{
			System.out.println("off screen");
			y = game.getHeight() - height;
		}
		
		for(Obstacle t: game.getCurrentLevel().getCurrentArea().getObstacles()){
			if(t.isSolid())
			{
				if(t instanceof QuestionBlock){
					if(top.intersects(t.getBoundsBottom())){
						setVelY(0);
						jumping = false;
						falling = true;
						t.die();
					}
				}
				if(t instanceof Ground || t instanceof Brick || t instanceof Pipe){
					if(top.intersects(t.getBounds())){
						setVelY(0);
						if(jumping){
							jumping = false;
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
						x = getX()+5;
					}
					if(getBoundsRight().intersects(t.getBounds())){
						setVelX(0);
						x = getX()-5;
					}
				}
				if(t instanceof Pipe)
				{
					pipe = (Pipe)t;
					pipeStart = game.getLoopNumber();
					if(pipe.getBoundsMiddle().intersects(getBoundsBottom()) && ducking)
						{
							goingDownPipe = true;
							playSound("Pipe.wav");
						}
					
				}
			}
		}
		
		for(Enemy enemy: game.getCurrentLevel().getCurrentArea().getEnemies()){
			
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
	/**
	 * Uses the input from the Gameboard to move Mario
	 * @param activeKeys The Set containing the keyCodes of all of the keys currently pressed
	 */
	public void move(Set<Integer> activeKeys)
	{
		if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart + 50 < game.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				ducking = false;
				gravity = 8.0;
				jumpStart = game.getLoopNumber();
				setVelX(0);
			}
		}
		else if(activeKeys.contains(KeyEvent.VK_SPACE) && activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart + 50 < game.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				ducking = false;
				gravity = 8.0;
				jumpStart = game.getLoopNumber();
				runningLeft = true;
				runningRight = false;
				standingStillLeft = true;
				standingStillRight = false;
				facingRight = false;
			}
		}
		else if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && activeKeys.contains(KeyEvent.VK_RIGHT)&& jumpStart + 55 < game.getLoopNumber())
		{
			if(!jumping)
			{
				playSound("Jump.wav");
				jumping = true;
				ducking = false;
				gravity = 8.0;
				jumpStart = game.getLoopNumber();
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
			ducking = false;
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
			ducking = false;
			setVelX(5);
		}
		else if(activeKeys.contains(KeyEvent.VK_DOWN) && velY == 0.0)
		{
			runningRight = false;
			runningLeft = false;
			ducking = true;
			setVelX(0);
		}
		
		else if(!activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && !activeKeys.contains(KeyEvent.VK_SPACE))
		{
			runningLeft = false;
			runningRight = false;
			ducking = false;
			setVelX(0);
		}
	}
	/**
	 * 
	 * @return The border collision Rectangle of the middle part of Mario
	 */
	public Rectangle getMiddleBound()
	{
		return new Rectangle(getX()+5,getY()+30,width-5,5);
	}
	/**
	 * 
	 * @return True if Mario is on solid ground
	 */
	public boolean isOnGround()
	{
		for(Obstacle o : game.getCurrentLevel().getCurrentArea().getObstacles())
		{
			if(o.isSolid())
				if(getBoundsBottom().intersects(o.getBoundsTop()))
					return true;
		}
		return false;
	}

}
