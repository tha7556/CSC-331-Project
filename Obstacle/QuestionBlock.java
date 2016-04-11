package Obstacles;

import javax.swing.ImageIcon;

import Game.Game;

public class QuestionBlock extends Obstacle
{

	public QuestionBlock(int x, int y,Game game)
	{
		super(x, y,53,53,true,game, new ImageIcon("Question Block.png"));
	}

}
