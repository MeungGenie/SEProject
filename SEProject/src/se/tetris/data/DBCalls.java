package se.tetris.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import se.tetris.component.*;

public class DBCalls extends DBConnectionManager {
	DBConnectionManager mananger = new DBConnectionManager();
	
	String path = System.getProperty("user.dir");
	String url = "jdbc:sqlite:" + path + "/src/se/tetris/data/lib/tetris.db";
	
	public static Vector StdScoreData;
	public static ArrayList<ScoreItem> StdScoreList = new ArrayList<ScoreItem>();
	public static ArrayList<ScoreItem> ItemScoreList = new ArrayList<ScoreItem>();

	public void InsertScoreData(String name, int score, int type, int mode) {
		String sql = "insert into Score(mode, type, name, score) values(?, ?, ?, ?)";

		try {
			Connection conn = DriverManager.getConnection(url);
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mode);
			pstmt.setInt(2, type);
			pstmt.setString(3, name);
			pstmt.setInt(4, score);
			
			int r = pstmt.executeUpdate(); 
			// pstmt.excuteQuery() : select 
			// pstmt.excuteUpdate() : insert, update, delete .. 
			
			
			System.out.println("변경된 row : " + r);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllStdScoreData() {
		String sql = "select * from Score WHERE mode=0 ORDER BY score;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ArrayList<ScoreItem> scoreList = new ArrayList<ScoreItem>();

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				ScoreItem score = new ScoreItem();
				// score
				int smode = Integer.valueOf(rs.getString("mode"));
				int slevel = Integer.valueOf(rs.getString("type"));
				int sscore = Integer.valueOf(rs.getString("score"));
				String sname = rs.getString("name");

				score.setMode(smode);
				score.setLevel(slevel);
				score.setNickName(sname);
				score.setScore(sscore);
				score.setRank(++count);
				
				scoreList.add(score);
			}

			for (int i = 0; i < scoreList.size(); i++) {
				System.out.println("랭킹 : " + scoreList.get(i).getRank());
				System.out.println("이름 : " + scoreList.get(i).getNickName());
				System.out.println("점수 : " + scoreList.get(i).getScore());
				System.out.println("레벨 : " + scoreList.get(i).getLevel());
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllItemScoreData() {
		String sql = "select * from Score WHERE mode=1 ORDER BY score;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ArrayList<ScoreItem> scoreList = new ArrayList<ScoreItem>();

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				ScoreItem score = new ScoreItem();
				// score
				int smode = Integer.valueOf(rs.getString("mode"));
				int slevel = Integer.valueOf(rs.getString("type"));
				int sscore = Integer.valueOf(rs.getString("score"));
				String sname = rs.getString("name");

				score.setMode(smode);
				score.setLevel(slevel);
				score.setNickName(sname);
				score.setScore(sscore);
				score.setRank(++count);
			}

			for (int i = 0; i < scoreList.size(); i++) {
				System.out.println("랭킹 : " + scoreList.get(i).getRank());
				System.out.println("이름 : " + scoreList.get(i).getNickName());
				System.out.println("점수 : " + scoreList.get(i).getScore());
				System.out.println("레벨 : " + scoreList.get(i).getLevel());
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Vector getStdScoreData() {
		StdScoreData.clear();
		
		String sql = "select * from Score WHERE mode=0 ORDER BY score DESC limit 10;";

		try {
			
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			
			while (rs.next()) {
				
				Vector in = new Vector<String>();
				
				String smode = rs.getString("mode");
				String slevel = rs.getString("type");
				String sscore = rs.getString("score");
				String sname = rs.getString("name");
				
				in.add(smode);
				in.add(slevel);
				in.add(sscore);
				in.add(sname);
				
				StdScoreData.add(in);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return StdScoreData;
	}

	public void get10StdScoreData(int type) {
		StdScoreList.clear();
		
		String sql = "select * from Score WHERE mode=0 AND type="+ type +" ORDER BY score DESC limit 10;";

		try {
			
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);


			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				
				ScoreItem score = new ScoreItem();
				// score
				int smode = Integer.valueOf(rs.getString("mode"));
				int slevel = Integer.valueOf(rs.getString("type"));
				int sscore = Integer.valueOf(rs.getString("score"));
				String sname = rs.getString("name");

				score.setMode(smode);
				score.setLevel(slevel);
				score.setNickName(sname);
				score.setScore(sscore);
				
				
				score.setRank(++count);
				StdScoreList.add(score);
				
//				System.out.println("Item Score List : "+StdScoreList);
			}

			for (int i = 0; i < ItemScoreList.size(); i++) {
//				System.out.println("랭킹 : " + StdScoreList.get(i).getRank());
//				System.out.println("이름 : " + StdScoreList.get(i).getNickName());
//				System.out.println("점수 : " + StdScoreList.get(i).getScore());
//				System.out.println("레벨 : " + StdScoreList.get(i).getLevel());
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void get10ItemScoreData(int type) {
		ItemScoreList.clear();
		
		String sql = "select * from Score WHERE mode=1 AND type="+ type +" ORDER BY score DESC limit 10;";

		try {
			
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				
				ScoreItem score = new ScoreItem();
				// score
				int smode = Integer.valueOf(rs.getString("mode"));
				int slevel = Integer.valueOf(rs.getString("type"));
				int sscore = Integer.valueOf(rs.getString("score"));
				String sname = rs.getString("name");

				score.setMode(smode);
				score.setLevel(slevel);
				score.setNickName(sname);
				score.setScore(sscore);
				score.setRank(++count);
				
				ItemScoreList.add(score);
				
			}

			for (int i = 0; i < ItemScoreList.size(); i++) {
//				System.out.println("랭킹 : " + ItemScoreList.get(i).getRank());
//				System.out.println("이름 : " + ItemScoreList.get(i).getNickName());
//				System.out.println("점수 : " + ItemScoreList.get(i).getScore());
//				System.out.println("레벨 : " + ItemScoreList.get(i).getLevel());
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void refreshScoreData() {
		String sql = "DELETE FROM Score;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void UpdateWindowSetting(int Code) {
		String sql = "";

		if (Code >= 0 && Code < 3) {
			sql = "UPDATE StInit set type = " + String.valueOf(Code) + " where id = 1;";
		} else {
			System.out.println("Window Setting : Code Error");
		}

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public int getWindowSetting() {
		int Result = 0;

		String sql = "select type from StInit Where id = 1;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("Get Window Setting : " + count);

			Result = count;

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return Result;
	}

	public void UpdateColorSetting(int Code) {
		String sql = "";

		if (Code >= 0 && Code < 2) {
			sql = "UPDATE StInit set type = " + String.valueOf(Code) + " where id = 2";
		} else {
			System.out.println("Color Setting : Code Error");
		}

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public int getColorSetting() {
		int Result = 0;

		String sql = "select type from StInit Where id = 2;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("Get Color Setting : " + count);

			Result = count;

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return Result;
	}

	public void UpdateLevelSetting(int Code) {
		String sql = "";

		if (Code >= 0 && Code < 3) {
			sql = "UPDATE StInit set type = " + String.valueOf(Code) + " where id = 3";
		} else {
			System.out.println("Level Setting : Code Error");
		}

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public int getLevelSetting() {
		int Result = 0;

		String sql = "select type from StInit Where id = 3;";

		try {
			Connection conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;

			while (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("Get Level Setting : " + count);

			Result = count;

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return Result;
	}

}
