package se.tetris.blocks;

import java.awt.Color;

public class LBlock extends Block {
	
	public LBlock() {
		shape = new int[][] { 
			{1, 1, 1},
			{1, 0, 0}
		};
		color = Color.PINK;
		colorBlind = new Color(165, 148, 159);
		numOfBlockType = 4;
	}
	
	public int[] getBlock() {
	    return shape[r];
	}
}
