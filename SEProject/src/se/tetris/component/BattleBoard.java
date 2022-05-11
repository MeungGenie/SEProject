package se.tetris.component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import se.tetris.component.*;

public class BattleBoard extends JFrame {
	
	private InnerBoard player1;
	private InnerBoard2 player2;
	private JPanel panel;
	
	public BattleBoard() {
		player1 = new InnerBoard();
		player2 = new InnerBoard2();
		
		panel = new JPanel();
		
		panel.add(player1);
		panel.add(player2);
		
		add(panel);
	}
}
