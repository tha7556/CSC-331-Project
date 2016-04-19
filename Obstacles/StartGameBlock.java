package Obstacles;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import Game.Game;

public class StartGameBlock extends Obstacle{

	public StartGameBlock(int x, int y, Game game){
		super(x, y,32,32,true,false,game, new ImageIcon("Images\\StartGameBlock.png"));

	}

}
