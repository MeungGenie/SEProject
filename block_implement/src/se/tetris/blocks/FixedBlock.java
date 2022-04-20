package se.tetris.blocks;

public class FixedBlock extends ItemBlock {
	
	public FixedBlock() {
		itemType = 4;
		setFItemCoor();
	}
	
	public FixedBlock(Block input) {
		super(input);
		itemType = 4;
		setFItemCoor();
	}
	
	public void setFItemCoor() {
		for(int i = 0; i<coordinates.size(); i++) {
			blockShape.shape[coordinates.get(i)[0]][coordinates.get(i)[1]] = itemType;
		}
	}
}