package Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Game;
import Game.Gameboard;
import Obstacles.Obstacle;



/**
 * Extends the {@link Character} class to create Enemies
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
	private int dieStart = -1;
	private boolean dead = false;
	public Enemy(int x, int y, int width, int height, boolean solid,String imageName, Game game) {
		super(x, y, width, height, solid, imageName, game);
		setVelX(1);
		
	}
	@Override
	public void render(Graphics g, Gameboard c) {
		this.image.paintIcon(c, g, x, y);
		g.setColor(Color.BLACK);
		g.setColor(Color.RED);
		Rectangle r = getBounds();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.GREEN);
		r = getBoundsTop();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.GREEN);
		r = getBoundsBottom();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.CYAN);
		r = getBoundsLeft();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		g.setColor(Color.BLUE);
		r = getBoundsRight();
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
	}
	@Override
	public void die(){
		if(!alive)
		{
			game.getCurrentLevel().getCurrentArea().removeCharacter(this);			
		}
		else
		{
			dead = true;
			dieStart = game.getLoopNumber();
			this.setImage("Poof.gif");
		}
	}
	@Override
	public void tick() {
		if(dead) //Death Animation
		{
			if(game.getLoopNumber() - dieStart > 10)
			{
				alive = false;
				die();
			}
			return;
		}
		x += velX;
		y += velY;
		for(Obstacle t: game.getCurrentLevel().getCurrentArea().getObstacles()){
			if(!t.isSolid()) break;
				if(getBoundsBottom().intersects(t.getBounds())){
					gravity = 0.0;
					falling = false;
					setVelY(0);
				}
				else{
					falling = true;
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(-velX);
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-velX);
				}
			
		}
		if(falling){
			gravity += 0.2;
			setVelY((int) gravity);
		}
		for(Enemy coEnemies: game.getCurrentLevel().getCurrentArea().getEnemies()){
			if(coEnemies.isSolid())
			{
			
				if(getBoundsRight().intersects(coEnemies.getBoundsLeft())){
					setVelX(-(getVelX()));
				}
				if(getBoundsLeft().intersects(coEnemies.getBoundsRight())){
					setVelX(-(getVelX()));
				}
				if(getBoundsBottom().intersects(coEnemies.getBoundsTop())){
					jumping = true;
					falling = false;
				}
			}
				
			
		}
		
		if(x <= 0){
			x = 0;
			setVelX(1);
		}
		if(x + width >= game.getWidth()){
			setVelX(-1);
			x = game.getWidth() - (width+1);
		}
		
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
	
}
