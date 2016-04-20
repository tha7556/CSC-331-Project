package Characters;


import Game.Game;
import Obstacles.Obstacle;
/**
 * A basic Enemy 
 *
 */
public class Koopa extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Koopa(int x, int y, Game game) {
		super(x, y, 32, 32, true,"Images\\GoombaWalkRight.gif", game);
		
	}
	@Override
	public void die(){
		if(!alive)
		{
			game.getCurrentLevel().getCurrentArea().removeCharacter(this);			
		}
		else
		{
			solid = false;
			dead = true;
			dieStart = game.getLoopNumber();
			this.setImage("Images\\Poof.gif");
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
					x = t.getX()+t.getWidth();
					setImage("Images\\GoombaWalkRight.gif");

				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-velX);
					x = t.getX()-40;
					setImage("Images\\GoombaWalkLeft.gif");

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
					setImage("Images\\GoombaWalkLeft.gif");

				}
				if(getBoundsLeft().intersects(coEnemies.getBoundsRight())){
					setVelX(-(getVelX()));
					setImage("Images\\GoombaWalkRight.gif");
				}
				if(getBoundsBottom().intersects(coEnemies.getBoundsTop())){
					jumping = true;
					falling = false;
				}
			}
				
			
		}
		
		if(x <= 0){
			x = 0;
			setImage("Images\\GoombaWalkRight.gif");
			setVelX(-velX);
		}
		if(x + width >= game.getWidth()){
			setVelX(-velX);
			setImage("Images\\GoombaWalkLeft.gif");
			x = game.getWidth() - (width+1);
		}
		
	}

}
