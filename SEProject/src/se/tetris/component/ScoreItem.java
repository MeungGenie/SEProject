package se.tetris.component;

import java.util.Date;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import se.tetris.data.DBCalls;

public class ScoreItem {
	
	DBCalls dataCalls = new DBCalls();

	public int ranking;
	public int mode;
	public int level;
	public String nickname;
	public long Scroe;
	public String date;

	public ScoreItem() {

	}

	public ScoreItem(int r, int l, String n, long s) {
		Scroe = s;

		setNickName(n);
		setRank(r);
		setLevel(l);
	}

	public void setScore(int score) {
		this.Scroe = score;
	}

	public long getScore() {
		return Scroe;
	}

	public void setRank(int rank) {
		this.ranking = rank;
	}

	public int getRank() {
		return ranking;
	}

	public void setMode(int mode) {
		/// Level Info
		/// 0 : Nomal
		/// 1 : Item
		this.mode = mode;
	}

	public int getMode() {
		return level;
	}

	public void setLevel(int level) {
		/// Level Info
		/// 0 : Nomal
		/// 1 : Easy
		/// 2 : Hard
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setNickName(String name) {
		this.nickname = name;
	}

	public String getNickName() {
		return nickname;
	}

	public void clearScore() {
		// �������� �ʱ�ȭ
	}

	public void showDialog(int sc, int mode, int level) {
		int result = JOptionPane.showConfirmDialog(null, "������ ����Ǿ����ϴ�! ������ �����Ͻðھ��?�ƴϿ��� Ŭ���� ���α׷��� ����˴ϴ�.", "��������",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == 0) {
			getScoreInfo(sc, mode, level);
		} else {
			System.exit(0);
		}
	}

	public void getScoreInfo(int sc, int mode, int level) {
		String name = JOptionPane.showInputDialog(null, "������ ��ϵ� �̸��� �Է����ּ���.","���ھ� ���",JOptionPane.OK_CANCEL_OPTION);
		System.out.println("�̸� : " +name);
		System.out.println("���� : " +sc);
		System.out.println("��� : " +mode);
		System.out.println("���� : " +level);
		
		//String name, int score, int type, int mode
		dataCalls.InsertScoreData(name, sc, level, mode);
	}

}