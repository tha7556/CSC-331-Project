package Characters;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Set;


import Game.Game;
import Game.Gameboard;
import Items.Coin;
import Items.ExtraLife;
import Items.Item;
import Items.Mushroom;
import Obstacles.Brick;
import Obstacles.EndGameBlock;
import Obstacles.FinishLine;
import Obstacles.Ground;
import Obstacles.Obstacle;
import Obstacles.Pipe;
import Obstacles.QuestionBlock;
import Obstacles.RestartGameBlock;
import Obstacles.StartGameBlock;


/**
 * The {@link Character} that the user will control
 *
 */
public class Mario extends Character{
	
	private int jumpStart = -1;
	private boolean ducking = false;
	private boolean onGround = false;
	private boolean dying = false;
	//Pipe variables
	private boolean goingDownPipe = false;
	private boolean goingUpPipe = false;
	private boolean insidePipe = false;
	private Pipe pipe;
	private int pipeStart = -1;
	//finish variables
	private boolean ending = false;
	private int endStart = -1;
	//Big variables
	private boolean big;
	private int invincible;
	
	/**
	 * 
	 * @param x The X location on the Gameboard
	 * @param y The Y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Mario(int x, int y, Game game) {
		super(x, y, 32, 32, true,"Images\\MarioIdleRight.png", game);
	}
	@Override
	public void render(Graphics g, Gameboard c) {
		if(!big){
			if(goingDownPipe || goingUpPipe)
			{
				setImage("Images\\MarioDownPipe.png");
			}
			else if(this.jumping){
				if(facingRight)
					setImage("Images\\MarioJumpRight.png");
				else
					setImage("Images\\MarioJumpLeft.png");
			}
			else if(!this.onGround)
			{
				if(facingRight)
					setImage("Images\\MarioFallRight.png");
				else
					setImage("Images\\MarioFallLeft.png");
			}
			else if(this.runningRight){
				setImage("Images\\MarioWalkRight.gif");
			}
			
			else if(this.runningLeft){
				setImage("Images\\MarioWalkLeft.gif");
			}
			
			else if(this.standingStillRight && !ducking){
				setImage("Images\\MarioIdleRight.png");
			}
			
			else if(this.standingStillLeft && !ducking){
				setImage("Images\\MarioIdleLeft.png");
			}
			else if(facingRight && ducking)
			{
				setImage("Images\\MarioDuckRight.png");
			}
			else if(!facingRight && ducking)
			{
				setImage("Images\\MarioDuckLeft.png");
			}
			
			
			else{
				setImage("Images\\MarioIdleRight.png");
			}
		}
		else{
			if(goingDownPipe || goingUpPipe)
			{
				setImage("Images\\BigMarioDownPipe.png");
			}
			else if(this.jumping){
				if(facingRight)
					setImage("Images\\BigMarioJumpRight.png");
				else
					setImage("Images\\BigMarioJumpLeft.png");
			}
			else if(!this.onGround)
			{
				if(facingRight)
					setImage("Images\\BigMarioFallRight.png");
				else
					setImage("Images\\BigMarioFallLeft.png");
			}
			else if(this.runningRight){
				setImage("Images\\BigMarioWalkRight.gif");
			}
			
			else if(this.runningLeft){
				setImage("Images\\BigMarioWalkLeft.gif");
			}
			
			else if(this.standingStillRight && !ducking){
				setImage("Images\\BigMarioIdleRight.png");
			}
			
			else if(this.standingStillLeft && !ducking){
				setImage("Images\\BigMarioIdleLeft.png");
			}
			else if(facingRight && ducking)
			{
				setImage("Images\\BigMarioDuckRight.png");
			}
			else if(!facingRight && ducking)
			{
				setImage("Images\\BigMarioDuckLeft.png");
			}
			
			
			else{
				setImage("Images\\BigMarioIdleRight.png");
			}
		}
		if(!big){
			image.paintIcon(game, g, x-8, y);
		}
		else{
			image.paintIcon(game, g, x, y+8);
		}
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
		
		//Finish Line
		if(ending)
		{
			if(game.getLoopNumber() - endStart > 300)
				game.getCurrentLevel().setFinished(true);
			
			return;
		}	
		//Pipes
		if(goingDownPipe) //Going down Pipe animation
		{
			x = pipe.getX()+(pipe.getWidth()/6);
			if(game.getLoopNumber() - pipeStart < 35)
				y += 3;
			else
			{
				goingDownPipe = false;
				insidePipe = true;
			}	
			return;
		}
		if(insidePipe) //Switch to linked Level
		{
			Pipe p = pipe;
			this.game.getCurrentLevel().loadSpecificLevel(pipe.getArea(), pipe.getLinkedPipe().getX(),pipe.getLinkedPipe().getY()+10);
			goingUpPipe = true;
			pipe = p.getLinkedPipe();
			pipeStart = game.getLoopNumber();
			return;
		}
		if(goingUpPipe)
		{
			if(game.getLoopNumber() - pipeStart == 20) //Waits 20 loops then plays sound
				playSound("Pipe.wav");
			else if(game.getLoopNumber() - pipeStart > 20) //After the 20 loops, move Mario
			{
				x = pipe.getX()+(pipe.getWidth()/6);
				if(game.getLoopNumber() - pipeStart < 47)
					y -= 3;
				else
				{
					goingUpPipe = false;
					pipeStart = -1;
				}	
			}
			return;
			
		}
		//Normal Moving
		invincible-=1;
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
	if(!dying && !ending){	
		if(x <= 0) 
		{
			try
			{
				this.game.getCurrentLevel().loadPreviousLevel();
			}
			catch(RuntimeException e)
			{
				x = 0;
			}
		}
		if(y <= 0)
		{
			y = 0;
		}

		if(x + width >= game.getWidth()) 
		{
			try
			{
				this.game.getCurrentLevel().loadNextLevel();
			}
			catch(RuntimeException e)
			{
				x = game.getWidth()-width;
			}
			
		}
		if(y + height >= game.getHeight()) 
		{
			y = game.getHeight() - height;
		}
		
		for(Obstacle t: game.getCurrentLevel().getCurrentArea().getObstacles()){
			if(t.isSolid())
			{
				if(t instanceof StartGameBlock){
					if(top.intersects(t.getBoundsBottom())){
						game.getCurrentLevel().setCurrentIndex(1);
						game.getCurrentLevel().setCurrentArea(1);
						setVelY(0);
						jumping = false;
						falling = true;
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
				
				if(t instanceof EndGameBlock){
					if(top.intersects(t.getBoundsBottom())){
						System.exit(0);
						setVelY(0);
						jumping = false;
						falling = true;
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
				
				if(t instanceof RestartGameBlock){
					if(top.intersects(t.getBoundsBottom())){
						game.getCurrentLevel().setCurrentIndex(0);
						game.getCurrentLevel().setCurrentArea(0);
						setVelY(0);
						jumping = false;
						falling = true;
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
				
				if(t instanceof QuestionBlock){
					if(top.intersects(t.getBounds())){
						setVelY(0);
						jumping = false;
						falling = true;
						t.die();
					}
				}
				if(t instanceof Ground || t instanceof Brick || t instanceof Pipe || t instanceof QuestionBlock){
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
					Pipe p = (Pipe)t;
					if(p.getArea() != null && p.getLinkedPipe() != null &&  p.getBoundsMiddle().intersects(getBoundsBottom()) && ducking)
						{
							pipe = p;
							pipeStart = game.getLoopNumber();
							goingDownPipe = true;
							playSound("Pipe.wav");
						}
					
				}
				if(t instanceof FinishLine)
				{
					FinishLine f = (FinishLine)t;
					if(getBounds().intersects(f.getBounds()))
					{
						if(getBounds().intersects(f.getBarBounds()))
						{
							System.out.println("You hit the Bar!!!");
							f.setBarVisiblity(false);
						}
						ending = true;
						game.getCurrentLevel().stopMusic();
						playSound("Audio\\Finish.wav");
						endStart = game.getLoopNumber();
					}
				}
			}
		}
		
		for(Enemy enemy: game.getCurrentLevel().getCurrentArea().getEnemies()){
			if(enemy.isSolid())
			{			
				if(getBoundsBottom().intersects(enemy.getBoundsTop())){
					if(enemy instanceof Koopa || enemy instanceof BulletBill){
						playSound("Audio\\Jump.wav");
						jumping = true;
						falling = false;
						enemy.die();
						game.getScoreboard().addScore();
						break;
					}
					else{
						die();
						break;
					}
				}
				if(getBoundsLeft().intersects(enemy.getBoundsRight())){
					die();
					break;
				}
				if(getBoundsRight().intersects(enemy.getBoundsLeft())){
					die();
					break;
				}
				if(getBoundsTop().intersects(enemy.getBoundsBottom()))
				{
					die();
					break;
				}
			}
			
		}
		//Items in general
		for(Item item: game.getCurrentLevel().getCurrentArea().getItems()){
			if(item.isAlive()){
				if(getBounds().intersects(item.getBounds())){
					item.die();
					if(item instanceof Coin){
						game.getScoreboard().addCoin();
						playSound("Audio\\Coin.wav");
					}
					if(item instanceof Mushroom){
						game.getScoreboard().addScore();
						playSound("Audio\\Mushroom.wav");
						big=true;
						setHeight(64);
						y-=32;
					}
					if(item instanceof ExtraLife){
						game.getScoreboard().addLife();
						game.getScoreboard().addScore();
						playSound("Audio\\ExtraLife.wav");
					}
				}
			}
		}
		//Items from QuestionBlocks
	    for(Obstacle o : game.getCurrentLevel().getCurrentArea().getObstacles())
	    {
	    	if(o instanceof QuestionBlock)
	    	{
	    		QuestionBlock q = (QuestionBlock)o;
	    		Item item = q.getItem();
	    		
	    		if(item.isAlive() && item.isVisible()){
					if(getBounds().intersects(item.getBounds())){
						item.die();
						if(item instanceof Coin){
							game.getScoreboard().addCoin();
							playSound("Audio\\Coin.wav");
						}
						if(item instanceof Mushroom){
							game.getScoreboard().addScore();
							playSound("Audio\\Mushroom.wav");
							big=true;
							setHeight(64);
							y-=32;
						}
						if(item instanceof ExtraLife){
							game.getScoreboard().addLife();
							game.getScoreboard().addScore();
							playSound("Audio\\ExtraLife.wav");
						}
					}
				}
	    	}
	    }
	}	
		
		if(jumping){
			gravity -= 0.25;
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
		
		if(dying){
			if(game.getScoreboard().getLives() > 0){
				if(y >= game.getHeight()-20) 
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					game.getScoreboard().takeLife();
					dying = false;
					game.getCurrentLevel().playMusic();
					setVelY(0);
					reset(30,450);
				}
			}
			
			if(game.getScoreboard().getLives() == 0){
				game.setLevelIndex(0);
				game.getCurrentLevel().setCurrentIndex(5);
				game.getCurrentLevel().setCurrentArea(5);
			}
		}
	}
	/**
	 * Uses the input from the Gameboard to move Mario
	 * @param activeKeys The Set containing the keyCodes of all of the keys currently pressed
	 */
	public void move(Set<Integer> activeKeys)
	{
		if(!dying && !ending){
			if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart < game.getLoopNumber())
			{
				if(!jumping && onGround)
				{
					playSound("Audio\\Jump.wav");
					jumping = true;
					ducking = false;
					gravity = 8.0;
					jumpStart = game.getLoopNumber();
				}
				else{
				}
			}
			else if(activeKeys.contains(KeyEvent.VK_SPACE) && activeKeys.contains(KeyEvent.VK_LEFT) && !activeKeys.contains(KeyEvent.VK_RIGHT) && jumpStart < game.getLoopNumber())
			{
				if(!jumping && onGround)
				{
					playSound("Audio\\Jump.wav");
					jumping = true;
					ducking = false;
					gravity = 8.0;
					jumpStart = game.getLoopNumber();
					runningLeft = true;
					runningRight = false;
					standingStillLeft = true;
					standingStillRight = false;
					facingRight = false;
					setVelX(-5);
	
				}
				else{
					ducking = false;
					runningLeft = true;
					runningRight = false;
					standingStillLeft = true;
					standingStillRight = false;
					facingRight = false;
					setVelX(-5);
	
				}
			}
			else if(activeKeys.contains(KeyEvent.VK_SPACE) && !activeKeys.contains(KeyEvent.VK_LEFT) && activeKeys.contains(KeyEvent.VK_RIGHT)&& jumpStart < game.getLoopNumber())
			{
				if(!jumping && onGround)
				{
					playSound("Audio\\Jump.wav");
					jumping = true;
					ducking = false;
					gravity = 8.0;
					jumpStart = game.getLoopNumber();
					runningRight = true;
					runningLeft = false;
					standingStillLeft = false;
					standingStillRight = true;
					facingRight = true;
					setVelX(5);
	
				}
				else{
					ducking = false;
					runningRight = true;
					runningLeft = false;
					standingStillLeft = false;
					standingStillRight = true;
					facingRight = true;
					setVelX(5);
	
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
	}
	/**
	 * 
	 * @return The border collision Rectangle of the middle part of Mario
	 */
	public Rectangle getMiddleBound()
	{
		return new Rectangle(getX()+5,getY()+height/2,width-5,5);
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
			{
				if(getBoundsBottom().intersects(o.getBoundsTop()))
					return true;
			}
		}
		return false;
	}
	/**
	 * Resets Mario's variables and puts him at the specified location
	 * @param x The x coordinate value on the Gameboard
	 * @param y The y coordinate value on the Gameboard
	 */
	public void reset(int x, int y)
	{
		jumpStart = -1;
		ducking = false;
		onGround = false;
		goingDownPipe = false;
		insidePipe = false;
		pipe = null;
		pipeStart = -1;
		ending = false;
		endStart = -1;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void die(){
		if(invincible<0){
			if(!big){
				game.getCurrentLevel().stopMusic();
				playSound("Audio\\death.wav");
				dying = true;
				jumping = true;
				gravity = 6.0;
				setVelX(0);
				if(getY() >= game.getHeight()) 
				{
					System.out.println("Past");
					y = game.getHeight() - height;
				}
			}
			else{
				playSound("Audio\\Powerdown.wav");
				big=false;
				invincible = 100;
				setHeight(32);
				y+=32;
			}
		}
	}
	/**
	 * 
	 * @return True if the Level is ending
	 */
	public boolean isEnding()
	{
		return ending;
	}
	/**
	 * 
	 * @return True if Mario is big
	 */
	public boolean isBig()
	{
		return big;
	}

}