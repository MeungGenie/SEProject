package se.tetris.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import se.tetris.data.DBCalls;

class tabViewBox extends JFrame {

	public tabViewBox() {
		setTitle("SeoulTech SE Tettris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);

		setBackground(Color.white);
		display();
	}

	public void display() {
		JTabbedPane tabView = new JTabbedPane();

		stdTable stdScoreView = new stdTable();
		itemTable itemScoreView = new itemTable();
		tabView.addTab("기본 모드 랭킹", stdScoreView);
		tabView.addTab("아이템 모드 랭킹", itemScoreView);

		add(tabView);
	}
}

class stdTable extends JPanel {
	DBCalls dataCalls = new DBCalls();
	stdTable() {
		dataCalls.get10StdScoreData();
		
		String[] title = { "랭킹", "모드", "닉네임", "점수" };
		
		ArrayList<ScoreItem> list = new ArrayList<ScoreItem>();
		String[][] data = {};
		

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = String.valueOf(list.get(i).getRank());

			if (list.get(i).getLevel() == 0) {
				data[i][1] = "Normal";
			} else if (list.get(i).getLevel() == 1) {
				data[i][1] = "Easy";
			} else if (list.get(i).getLevel() == 2) {
				data[i][1] = "Hard";
			}

			data[i][2] = list.get(i).getNickName();
			data[i][3] = String.valueOf(list.get(i).getScore());
		}

		JTable table = new JTable(data, title);
		JScrollPane scrollview = new JScrollPane(table);
		scrollview.setPreferredSize(new Dimension(350, 500));
		scrollview.setBackground(Color.white);
		add(scrollview, BorderLayout.CENTER);
		setBackground(Color.white);
	}
}

class itemTable extends JPanel {
	DBCalls dataCalls = new DBCalls();
	
	itemTable() {

		String[] title = { "랭킹", "모드", "닉네임", "점수" };
		
		ArrayList<ScoreItem> list= dataCalls.ItemScoreList;
		
		dataCalls.get10ItemScoreData();
		
		String[][] data = new String[list.size()][4] ;
		
//		System.out.println("아이템모드 : " + list);
		

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = String.valueOf(list.get(i).getRank());

//			System.out.println("아이템모드 데이터 랭크: " + data[i][0]);
			if (list.get(i).getLevel() == 0) {
				data[i][1] = "Normal";
				
			} else if (list.get(i).getLevel() == 1) {
				data[i][1] = "Easy";
			} else if (list.get(i).getLevel() == 2) {
				data[i][1] = "Hard";
			}

			data[i][2] = list.get(i).getNickName();
			data[i][3] = String.valueOf(list.get(i).getScore());
		}
		
//		System.out.println("아이템모드 데이터: " + data);
		
		JTable table = new JTable(data, title);
		JScrollPane scrollview = new JScrollPane(table);
		scrollview.setPreferredSize(new Dimension(350, 500));
		scrollview.setBackground(Color.white);
		add(scrollview, BorderLayout.CENTER);
		setBackground(Color.white);
	}
}

public class Score extends tabViewBox {

	

	public Score() {

		JLabel Title = new JLabel("SeoulTech SE Tettris Score");
		Title.setFont(new Font("Serif", Font.BOLD, 17));
		Title.setHorizontalAlignment(JLabel.CENTER);
		Title.setVerticalAlignment(JLabel.CENTER);

		JLabel Team = new JLabel("Team one");
		Team.setHorizontalAlignment(JLabel.CENTER);
		Team.setVerticalAlignment(JLabel.CENTER);

		// JPanel btnGroup = new JPanel();
		// btnGroup.setBackground(Color.white);
		// btnGroup.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		tabViewBox tabScoreView = new tabViewBox();

	}

}
