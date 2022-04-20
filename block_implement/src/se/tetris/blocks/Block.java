package se.tetris.blocks;

import java.awt.Color;

public abstract class Block {
		
	protected int[][] shape;
	protected Color color;
	protected Color colorBlind;

	public Block() {
		shape = new int[][]{ 
			{1, 1}, 
			{1, 1},
		};
		color = Color.YELLOW;
	}
	
	public int getShape(int x, int y) {
		return shape[y][x];
	}
	
	public Color getColor() {
		return color;
	}
	
	public Color getColorBlind() {
		return colorBlind;
	}
	
	public void rotate() {
		int[][] rotate = new int [shape[0].length][shape.length];
		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate[i].length; j++) {
				rotate[i][j] = shape[shape.length-1-j][i];
			}
		}
		shape = rotate;
	}
	
	public int height() {
		return shape.length;
	}
	
	public int width() {
		if(shape.length > 0)
			return shape[0].length;
		return 0;
	}
	
	public void setShape(int [][] inputShape) {
		shape = inputShape;
	}
}