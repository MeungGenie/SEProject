package se.tetris.blocks;

import java.awt.Color;

public class ZBlock extends Block {
	
	public ZBlock() {
		this.shape = new int[][] {
				{0, 1},
				{1, 1},
				{1, 0}
		};
		color = Color.RED;
		colorBlind = new Color(99, 106, 141);
		numOfBlockType = 2;
	}
	
	public int[] getBlock() {
	    return shape[r];
	}
}
