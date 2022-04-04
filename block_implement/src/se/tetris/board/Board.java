package se.tetris.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
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
	public static final char BORDER_CHAR = ' ';
	
	private JTextPane tetrisArea;
	private JTextPane nextArea;
	private JPanel panel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel scorePanel;
	private JPanel levelPanel;
	private int[][] board;
	private int[][] nextBoard;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet stylesetBr;
	private SimpleAttributeSet stylesetNx;
	private Timer timer;
	private Block curr;
	private Block next;
	int x = 3; //Default Position.
	int y = 0;
	int nextX = 1;
	int nextY = 1;
	int score = 0;
	int level = 0;
	
	private static final int initInterval = 1000;
	
	public Board() {
		super("SeoulTech SE tetris");
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
		
		scorePanel = new JPanel();
		EtchedBorder scoreBorder = new EtchedBorder();
		scorePanel.setBorder(scoreBorder);
		scorePanel.setPreferredSize(new Dimension(150, 50));
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
		JLabel levelLb1 = new JLabel("Level");
		levelLb1.setForeground(Color.darkGray);
		levelLb1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel levelLb2 = new JLabel(Integer.toString(level));
		levelLb2.setForeground(Color.BLUE);
		levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
		levelPanel.add(levelLb1);
		levelPanel.add(Box.createVerticalStrut(5));
		levelPanel.add(levelLb2);
		
		//JButton pause = new JButton();
		
		
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
		
		//Create the first block and draw.
		curr = getRandomBlockNormal();
		next = getRandomBlockNormal();
		
		//Document default style.
		stylesetBr = new SimpleAttributeSet();
		StyleConstants.setFontSize(stylesetBr, 20);
		StyleConstants.setFontFamily(stylesetBr, "Courier New");
		StyleConstants.setBold(stylesetBr, true);
		StyleConstants.setForeground(stylesetBr, Color.WHITE);
		StyleConstants.setAlignment(stylesetBr, StyleConstants.ALIGN_CENTER);
		
		//Document default style.
		stylesetNx = new SimpleAttributeSet();
		StyleConstants.setFontSize(stylesetNx, 25);
		StyleConstants.setFontFamily(stylesetNx, "Courier New");
		StyleConstants.setBold(stylesetNx, true);
		StyleConstants.setForeground(stylesetNx, next.getColor());
		StyleConstants.setAlignment(stylesetNx, StyleConstants.ALIGN_CENTER);		
		
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
		StyledDocument doc = tetrisArea.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
		StyleConstants.setForeground(styles, curr.getColor());
		for(int j=0; j<curr.height(); j++) {
			int rows = y+j == 0 ? 0 : y+j-1;
			int offset = rows * (WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, curr.width(), styles, true);
			for(int i=0; i<curr.width(); i++) {
				board[y+j][x+i] = curr.getShape(i, j);
				
			}
		}
	}
	
	private void placeNext() {
		StyledDocument doc = nextArea.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
		StyleConstants.setForeground(styles, next.getColor());
		for(int j = 0; j < next.height(); j++) {
			int rows = y+j == 0 ? 0 : y+j-1;
			int offset = rows * 8 + x + 1;
			doc.setCharacterAttributes(offset, next.width(), styles, true);
			for(int i=0; i<next.width(); i++) {
				nextBoard[nextY+j][nextX+i] = next.getShape(i, j);
			}
		}
	}
	
	private void eraseCurr() {
		for(int i=x; i<x+curr.width(); i++) {
			for(int j=y; j<y+curr.height(); j++) {
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
		if(y < HEIGHT - curr.height()) y++;
		else {
			placeBlock();
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
	
	public void rotate() {
		curr.rotate();
	}
	
	public void drawBoard() {
		StringBuffer sb = new StringBuffer();
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
		sb.append("\n");
		for(int i=0; i < board.length; i++) {
			sb.append(BORDER_CHAR);
			for(int j=0; j < board[i].length; j++) {
				if(board[i][j] == 1) {
					sb.append("бс");
				} else {
					sb.append(" ");
				}
			}
			sb.append(BORDER_CHAR);
			sb.append("\n");
		}
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
		tetrisArea.setText(sb.toString());
		StyledDocument doc = tetrisArea.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), stylesetBr, false);
		tetrisArea.setStyledDocument(doc);
	}
	
	public void drawNext() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for(int i=0; i < nextBoard.length; i++) {
			for(int j=0; j < nextBoard[i].length; j++) {
				if(nextBoard[i][j] == 1) {
					sb.append("бс");
				} else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		nextArea.setText(sb.toString());
		StyledDocument doc = nextArea.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), stylesetNx, false);
		nextArea.setStyledDocument(doc);
	}
	
	
	
	public void reset() {
		this.board = new int[20][10];
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
				eraseCurr();
				curr.rotate();
				drawBoard();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
}
