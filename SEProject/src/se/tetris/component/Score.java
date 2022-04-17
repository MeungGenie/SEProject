package se.tetris.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

class ScoreItem {
	
	public int ranking;
	public int level;
	public String nickname;
	public long Scroe;
	
	public ScoreItem(int r, int l, String n, long s) {
		Scroe = s;
		
		setNickName(n);
		setRank(r);
		setLevel(l);
	}
	public void setScore(int score)
	{
		this.Scroe=score;
	}
	
	public long getScore()
	{
		return Scroe;
	}
	public void setRank(int rank) 
	{
		this.ranking = rank;
	}
	
	public int getRank() 
	{
		return ranking;
	}
	
	public void setLevel(int level) 
	{
		this.level = level;
	}
	
	public int getLevel() 
	{
		return level;
	}
	public void setNickName(String name) 
	{
		this.nickname = name;
	}
	
	public String getNickName() 
	{
		return nickname;
	}
	
	public void clearScore() 
	{
		//설정에서 초기화
	}
	
}


public class Score extends JFrame  {
	
	

	
	public Score() {
		setTitle("SeoulTech SE Tettris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(12,1,0,10);

		Container scoreView = getContentPane();
		
		scoreView.setLayout(grid);
		
		scoreView.setBackground(Color.WHITE);
		
		JLabel Title = new JLabel("SeoulTech SE Tettris Score");
		Title.setFont(new Font("Serif",Font.BOLD,17));
		Title.setHorizontalAlignment(JLabel.CENTER);
		Title.setVerticalAlignment(JLabel.CENTER);
		
		JLabel Team = new JLabel("Team one");
		Team.setHorizontalAlignment(JLabel.CENTER);
		Team.setVerticalAlignment(JLabel.CENTER);
		
		//JPanel btnGroup = new JPanel();
		//btnGroup.setBackground(Color.white);
		//btnGroup.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		
		ScoreItem[] SL = {  new ScoreItem(1,10,"과1",10), new ScoreItem(2,5,"과2",9),
							new ScoreItem(3,2,"과3",8), new ScoreItem(4,1,"과4",7),
							new ScoreItem(5,2,"과5",6), new ScoreItem(6,1,"과6",5),
							new ScoreItem(7,2,"과7",4), new ScoreItem(8,3,"과8",3),
							new ScoreItem(9,1,"과9",2), new ScoreItem(10,1,"과10",1)};
		
		scoreView.add(Title);
		
		
		for(int i=0; i<SL.length; i++) {
			
			JPanel scoreBoardN = new JPanel();
			JLabel rankN = new JLabel(Integer.toString(SL[i].ranking));
			JLabel nickN = new JLabel(SL[i].nickname);
			JLabel scoreN = new JLabel( Long.toString(SL[i].Scroe));
			
			scoreBoardN.add(rankN);
			scoreBoardN.add(nickN);
			scoreBoardN.add(scoreN);
			
			scoreView.add(scoreBoardN);	
		}
		
		

		
		scoreView.add(Team);
		
	}
	
	
	

}
