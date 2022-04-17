package se.tetris.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import se.tetris.blocks.Block;
import se.tetris.blocks.IBlock;
import se.tetris.blocks.JBlock;
import se.tetris.blocks.LBlock;
import se.tetris.blocks.OBlock;
import se.tetris.blocks.SBlock;
import se.tetris.blocks.TBlock;
import se.tetris.blocks.ZBlock;

public class Board extends JFrame {

	private static final long serialVersionUID = 2434035659171694595L;
	
	public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final char BORDER_CHAR = 'X';
	
	private JTextPane tetrisArea;
	private JTextPane nextArea;
	private JPanel panel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel scorePanel;
	private JPanel levelPanel;
	private JLabel levelLb1;
	private JLabel levelLb2;
	private JLabel scoreLb1;
	private JLabel scoreLb2;
	private int[][] board;
	private int[][] nextBoard;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet stylesetBr;
	private SimpleAttributeSet stylesetNx;
	private SimpleAttributeSet stylesetCur;
	private StyledDocument boardDoc;
	private StyledDocument nextDoc;
	private Timer timer;
	private Block curr;
	private Block next;
	int x = 3; //Default Position.
	int y = 0;
	int nextX = 1;
	int nextY = 1;
	int score = 0;
	int level = 0;
	int check = 0;
	
	private static final int initInterval = 1000;
	
	public Board() {
		super("SeoulTech SE Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Board display setting.
		tetrisArea = new JTextPane();
		tetrisArea.setEditable(false);
		tetrisArea.setBackground(Color.BLACK);
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		tetrisArea.setBorder(border);
		
		nextArea = new JTextPane();
		nextArea.setEditable(false);
		nextArea.setBackground(Color.BLACK);
		nextArea.setBorder(border);
		nextArea.setPreferredSize(new Dimension(150, 200));
		
		scorePanel = new JPanel();
		EtchedBorder scoreBorder = new EtchedBorder();
		scorePanel.setBorder(scoreBorder);
		scorePanel.setPreferredSize(new Dimension(150, 50));
		scoreLb1 = new JLabel("Scores");
		scoreLb1.setForeground(Color.darkGray);
		scoreLb1.setAlignmentX(CENTER_ALIGNMENT);
		scoreLb2 = new JLabel(Integer.toString(score));
		JLabel scoreLb1 = new JLabel("Scores");
		scoreLb1.setForeground(Color.darkGray);
		scoreLb1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel scoreLb2 = new JLabel(Integer.toString(score));
		scoreLb2.setForeground(Color.RED);
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		scorePanel.add(scoreLb1);
		scorePanel.add(Box.createVerticalStrut(5));
		scorePanel.add(scoreLb2);
		
		
		levelPanel = new JPanel();
		levelPanel.setBorder(scoreBorder);
		levelPanel.setPreferredSize(new Dimension(150, 50));
		levelLb1 = new JLabel("Level");
		levelLb1.setForeground(Color.darkGray);
		levelLb1.setAlignmentX(CENTER_ALIGNMENT);
		levelLb2 = new JLabel(Integer.toString(level));		
		JLabel levelLb1 = new JLabel("Level");
		levelLb1.setForeground(Color.darkGray);
		levelLb1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel levelLb2 = new JLabel(Integer.toString(level));
		levelLb2.setForeground(Color.BLUE);
		levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
		levelPanel.add(levelLb1);
		levelPanel.add(Box.createVerticalStrut(5));
		levelPanel.add(levelLb2);
		
		leftPanel = new JPanel();
		leftPanel.add(tetrisArea);
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(nextArea);
		rightPanel.add(Box.createVerticalStrut(20));
		rightPanel.add(scorePanel);
		rightPanel.add(Box.createVerticalStrut(20));
		rightPanel.add(levelPanel);
		
		panel = new JPanel();
		panel.add(leftPanel);
		panel.add(rightPanel);
		
		add(panel);

		//Set timer for block drops.
		timer = new Timer(initInterval, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown();
				drawBoard();
			}
		});
		
		//Initialize board for the game.
		board = new int[HEIGHT][WIDTH];
		nextBoard = new int[4][5];
		playerKeyListener = new PlayerKeyListener();
		addKeyListener(playerKeyListener);
		setFocusable(true);
		requestFocus();
		
		//Create the first block and draw
		curr = getRandomBlockNormal();
		next = getRandomBlockNormal();
		
		//Document default style.
		stylesetBr = new SimpleAttributeSet();
		StyleConstants.setFontSize(stylesetBr, 20);
		StyleConstants.setFontFamily(stylesetBr, "Courier New");
		StyleConstants.setBold(stylesetBr, true);
		StyleConstants.setForeground(stylesetBr, Color.WHITE);
		StyleConstants.setAlignment(stylesetBr, StyleConstants.ALIGN_CENTER);
		
		stylesetCur = new SimpleAttributeSet();
		StyleConstants.setFontSize(stylesetCur, 20);
		StyleConstants.setFontFamily(stylesetCur, "Courier New");
		StyleConstants.setBold(stylesetCur, true);
		StyleConstants.setAlignment(stylesetCur, StyleConstants.ALIGN_CENTER);
			
		stylesetNx = new SimpleAttributeSet();
		StyleConstants.setFontSize(stylesetNx, 25);
		StyleConstants.setFontFamily(stylesetNx, "Courier New");
		StyleConstants.setBold(stylesetNx, true);
		StyleConstants.setAlignment(stylesetNx, StyleConstants.ALIGN_CENTER);	
		
		boardDoc = tetrisArea.getStyledDocument();
		nextDoc = nextArea.getStyledDocument();

		placeBlock();
		drawBoard();
		placeNext();
		drawNext();
	
		timer.start();
	}

	private Block getRandomBlockNormal() {
		Random rnd = new Random(System.currentTimeMillis());
		int block = rnd.nextInt(7);
		switch(block) {
		case 0:
			return new IBlock();
		case 1:
			return new JBlock();
		case 2:
			return new LBlock();
		case 3:
			return new ZBlock();
		case 4:
			return new SBlock();
		case 5:
			return new TBlock();
		case 6:
			return new OBlock();			
		}
		return new LBlock();
	}

	private Block getRandomBlockEasy() {
		double min = 1;
		double max = 100;
		double weighted = Math.random() * (max - min) + min;
		if (weighted <= (80/7 + 20))
			return new IBlock();
		else 
		{
			Random rnd = new Random(System.currentTimeMillis());
			int block = rnd.nextInt(7);
			switch(block) {
			case 0:
				return new JBlock();
			case 1:
				return new LBlock();
			case 2:
				return new ZBlock();
			case 3:
				return new SBlock();
			case 4:
				return new TBlock();
			case 5:
				return new OBlock();			
			}
			return new LBlock();
		}
	}
	
	private Block getRandomHard() {
		double min = 1;
		double max = 100;
		double weighted = Math.random() * (max - min) + min;
		if (weighted <= (120/7 - 20))
			return new IBlock();
		else 
		{
			Random rnd = new Random(System.currentTimeMillis());
			int block = rnd.nextInt(7);
			switch(block) {
			case 0:
				return new JBlock();
			case 1:
				return new LBlock();
			case 2:
				return new ZBlock();
			case 3:
				return new SBlock();
			case 4:
				return new TBlock();
			case 5:
				return new OBlock();			
			}
			return new LBlock();
		}
	}
	
	
	private void placeBlock() {
		for(int j=0; j<curr.height(); j++) {
			for(int i=0; i<curr.width(); i++) {
				if (curr.getShape(i, j) == 1)
					board[y+j][x+i] = curr.getShape(i, j);
			}
		}
	}
	
	private void placeNext() {
		for(int j = 0; j < next.height(); j++) {
			for(int i=0; i<next.width(); i++) {
				nextBoard[nextY+j][nextX+i] = next.getShape(i, j);
			}
		}
	}
	
	private void eraseCurr() {
		for(int i=x; i<x+curr.width(); i++) {
			for(int j=y; j<y+curr.height(); j++) {
				if(curr.getShape(i-x,j-y) == 1)
					board[j][i] = 0;
			}
		}
	}
	
	private void eraseNext() {
		for(int i = nextX; i < nextX + next.width(); i++) {
			for(int j=nextY; j< nextY + next.height(); j++) {
				nextBoard[j][i] = 0;
			}
		}
	}

	protected void moveDown() {
		eraseCurr();
		if (collisionCheck() == true) {
			saveBoard();
			curr = next;
			eraseNext();
			next = getRandomBlockNormal();
			placeNext();
			drawNext();
			x = 3;
			y = 0;			
		}	
		if(y < HEIGHT - curr.height()) y++;
		else {
			placeBlock();
			saveBoard();
			curr = next;
			eraseNext();
			next = getRandomBlockNormal();
			placeNext();
			drawNext();
			x = 3;
			y = 0;
		}
		placeBlock();
	}
	
	protected void moveRight() {
		eraseCurr();
		if(x < WIDTH - curr.width()) x++;
		placeBlock();
	}

	protected void moveLeft() {
		eraseCurr();
		if(x > 0) {
			x--;
		}
		placeBlock();
	}

	public void drawBoard() {
		StringBuffer sb = new StringBuffer();
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
		sb.append("\n");
		for(int i=0; i < board.length; i++) {
			sb.append(BORDER_CHAR);
			for(int j=0; j < board[i].length; j++) {
				if(board[i][j] == 1) {
					sb.append("��");
				} else {
					sb.append(" ");
				}
			}
			sb.append(BORDER_CHAR);
			sb.append("\n");
		}
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
		tetrisArea.setText(sb.toString());
		boardDoc.setCharacterAttributes(0, boardDoc.getLength(), stylesetBr, false);
		
		for(int j = 0; j < curr.height(); j++) {
			int rows = y+j == 0 ? 1 : y+j+1;
			int offset = rows * (WIDTH+3) + x + 1;
			StyleConstants.setForeground(stylesetCur, curr.getColor());
			boardDoc.setCharacterAttributes(offset, curr.width(), stylesetCur, true);
		}
	}
	
	public void drawNext() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for(int i=0; i < nextBoard.length; i++) {
			for(int j=0; j < nextBoard[i].length; j++) {
				if(nextBoard[i][j] == 1) {
					sb.append("��");
				} else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		nextArea.setText(sb.toString());
		StyleConstants.setForeground(stylesetNx, next.getColor());
		nextDoc.setParagraphAttributes(0, nextDoc.getLength(), stylesetNx, false);
	}
	
	
	public void reset() {
		board = new int[HEIGHT][WIDTH];
		nextBoard = new int[4][5];
		x = 3;
		y = 0;
		curr = getRandomBlockNormal();
		next = getRandomBlockNormal();
		placeBlock();
		drawBoard();	
		placeNext();
		drawNext();
		this.board = new int[20][10];
	}
	
	public boolean collisionCheck() {
		for (int i = 0; i < curr.height(); i++) {
			for (int j = 0; j < curr.width(); j++) {
				if (curr.getShape(j, i) == 1 && i + y < 19) {
					int check = board[i + y + 1][j + x];
					if (check == 1) {
						return true;
					}
				}
			}
		}
	
		return false;
	}
	
	
	public void saveBoard() {
		for (int i = 0; i < curr.height(); i++) {
			for (int j = 0; j < curr.width(); j++) {
				if (curr.getShape(j, i) == 1) {
					board[y + i][j + x] = 1;
				}
			}
		}
	}
	
	
	protected void blockRotate() {
	      eraseCurr();
	      curr.rotate();
	      placeBlock();
	   }

	public class PlayerKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				moveDown();
				drawBoard();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				drawBoard();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				drawBoard();
				break;
			case KeyEvent.VK_UP:
	            blockRotate();
	            drawBoard();
				break;
			case KeyEvent.VK_ESCAPE:
				timer.stop();
				String[] stopOption = {"Restart", "Play", "Exit"};
				int choice = JOptionPane.showOptionDialog(null, "What Do You Want?", "Stop", 0, 0, null, stopOption,stopOption[1]);
				switch(choice) {
					case 0:
						int confirm1 = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (confirm1 == 0) {
							reset();
							score = 0;
							level = 0;
							timer.restart();
						}
						else {
							timer.start();
						}
						break;
					case 1:
						timer.start();
						break;
					case 2:
						int confirm2 = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (confirm2 == 0) {
							dispose(); //or save score and move to score board.
						}
						else {
							timer.start();
						}
						break;
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	//max - 30, default - 20,  
	public void setSize(int size) {
		StyleConstants.setFontSize(stylesetBr, size);
		StyleConstants.setFontSize(stylesetCur, size);
		StyleConstants.setFontSize(stylesetNx, size+5);
		drawBoard();
		drawNext();
	}
	
	
	//max - (200, 60), default - (150, 50) 
	public void setRtSize(int xSize, int ySize) {
		scorePanel.setPreferredSize(new Dimension(xSize, ySize));
		levelPanel.setPreferredSize(new Dimension(xSize, ySize));
		nextArea.setPreferredSize(new Dimension(xSize, ySize * 4));
	}
	
	//max - 17, default - nothing, 
	public void setLbSize(int size) {
		scoreLb1.setFont(new Font(null, Font.BOLD, size));
		scoreLb2.setFont(new Font(null, Font.BOLD, size));
		levelLb1.setFont(new Font(null, Font.BOLD, size));
		levelLb2.setFont(new Font(null, Font.BOLD, size));
	}
	
}
