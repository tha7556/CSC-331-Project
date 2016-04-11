package Character;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;
import Game.Gameboard;
import Obstacles.Obstacle;




public class Enemy extends Character{

	public Enemy(int x, int y, int width, int height, boolean solid,String imageName, Game handler) {
		super(x, y, width, height, solid, imageName, handler);
		setVelX(1);
		
	}

	@Override
	public void render(Graphics g, Gameboard c) {
		this.image.paintIcon(c, g, x, y);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
	public void die(){
		handler.getCurrentLevel().getCurrentArea().removeCharacter(this);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		for(Obstacle t: handler.getCurrentLevel().getCurrentArea().getObstacles()){
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
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-velX);
					x = t.getX()-t.getWidth();
				}
			
		}
		if(falling){
			gravity += 0.2;
			setVelY((int) gravity);
		}
		for(Enemy coEnemies: handler.getCurrentLevel().getCurrentArea().getEnemies()){
			
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
		
		if(x <= 0){
			x = 0;
			setVelX(1);
		}
		if(x + width >= handler.getWidth()){
			setVelX(-1);
			x = handler.getWidth() - (width+1);
		}
		
	}
	
}
