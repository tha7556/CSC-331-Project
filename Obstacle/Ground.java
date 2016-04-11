package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;

public class Ground extends Obstacle
{
	public Ground(int x, int y, Game game)
	{
		super(x,y,59,52,true,game,new ImageIcon("Ground.png"));
	}
}
