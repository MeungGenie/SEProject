package se.tetris.main;

import se.tetris.component.Board;
import se.tetris.component.Start;
import se.tetris.component.Score;

public class Tetris {

	public static void main(String[] args) {
		Start startView = new Start();
		startView.setSize(400, 600);
		startView.setVisible(true);
	}
}