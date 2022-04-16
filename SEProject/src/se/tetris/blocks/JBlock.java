package se.tetris.blocks;

import java.awt.Color;

public class JBlock extends Block {
	
	public JBlock() {
		shape = new int[][] { 
				{1, 1, 1},
				{0, 0, 1}
		};
		color = Color.BLUE;
<<<<<<< HEAD
		numOfBlockType = 4;
	}
	
	public int[] getBlock() {
	    return shape[r];
	}
}
=======
	}
}
>>>>>>> 6891bcb2e52f8b9c764a909bcdf61c63d8836986
