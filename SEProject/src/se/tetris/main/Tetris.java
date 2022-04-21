package se.tetris.main;

import se.tetris.component.Board;
import se.tetris.component.Start;
import se.tetris.data.*;
import se.tetris.component.Score;

public class Tetris {
	protected String path = System.getProperty("user.dir");
    protected String url = "jdbc:sqlite:"+path+"/src/se/tetris/data/lib/tetris.db"; 

	public static void main(String[] args) {
		
		Start startView = new Start();
		startView.setSize(400, 600);
		startView.setVisible(true);
		
		DBConnectionManager data = new DBConnectionManager();
		
		DBCalls dataCalls = new DBCalls();
		
		data.connect();
		data.createNewTable();
		
//		
//		dataCalls.getWindowSetting();
//		dataCalls.getLevelSetting();
//		dataCalls.getColorSetting();
		
//		
//		dataCalls.get10ItemScoreData();
		
	}
}