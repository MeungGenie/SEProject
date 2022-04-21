package se.tetris.blocks;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class LRemoveBlock extends Block {
	
	Block blockShape;
	int itemType = 2;
	int [] itemCoor = new int [2];
	ArrayList<int[]> coordinates = new ArrayList<int[]>();
	
	
	public LRemoveBlock() {
		
	}
	
	public LRemoveBlock(Block input) {
		blockShape = input;
		coorColl();
		setItemCoor();
	}
	
	public Block getLRBlock() {
		return blockShape;
	}
	
	
	public void coorColl() {
		for (int i = 0; i < blockShape.height(); i++) {
			for (int j = 0; j < blockShape.width(); j++) {
				if (blockShape.getShape(j, i) == 1) {
					coordinates.add(new int [] {i,j});
					System.out.println(Arrays.toString(new int [] {i,j}));
				}
			}
		}
	}
	

	public void setItemCoor() {
		Random rnd = new Random(System.currentTimeMillis());
		int item = rnd.nextInt(coordinates.size());
		int y = coordinates.get(item)[0];
		int x = coordinates.get(item)[1];
		System.out.println(item + " "+ y + " " + x);
		blockShape.shape[y][x] = itemType;
	}
	
	
}