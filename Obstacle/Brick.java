package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;

public class Brick extends Obstacle
{
	public Brick(int x, int y, Game game)
	{
		super(x,y,64,54,true, game, new ImageIcon("Brick.png"));
	}
}
