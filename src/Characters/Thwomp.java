package Characters;

import Game.Game;
import Obstacles.Brick;
import Obstacles.Ground;
import Obstacles.Obstacle;
import Obstacles.Pipe;
import Obstacles.QuestionBlock;

/**
 * An Enemy that stays up high until the player comes near, then drops down
 *@author Jacob
 */
public class Thwomp extends Enemy
{
	/**
	 * 
	 * @param x The x location on the Gameboard
	 * @param y The y location on the Gameboard
	 * @param game The instance of the Game
	 */
	public Thwomp(int x, int y, Game game) {
		super(x, y, 64, 96, true,"Images\\Thwomp.png", game);
		setVelY(2);
	}
	
	public void tick() {
		y += velY;
		if(getY() < 0)
		{
			y = 0;
			setVelY(-velY);
		}
		if(getY()+getHeight() > game.getHeight())
		{
			y = game.getHeight()-getHeight();
			setVelY(-velY);
		}
		if(getX()>game.getMario().getX()&&getX()+64<game.getMario().getX()+32){
			setVelY(5);
		}
		for(Obstacle t: game.getCurrentLevel().getCurrentArea().getObstacles()){
			if(t.isSolid())
			{
				if(t instanceof Ground || t instanceof Brick || t instanceof Pipe || t instanceof QuestionBlock){
					if(getBoundsTop().intersects(t.getBounds())){
						setVelY(2);
							
						}
					if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(-2);
					}
				}
			}
		}
		
		for(Enemy enemy: game.getCurrentLevel().getCurrentArea().getEnemies()){
			if(enemy.isSolid())
			{			
				if(getBoundsBottom().intersects(enemy.getBoundsTop())){
						setVelY(-2);
					}
					
				if(getBoundsTop().intersects(enemy.getBoundsBottom())){
							setVelY(2);
					}
				}
			}	
		}
}

