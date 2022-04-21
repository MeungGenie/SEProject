package se.tetris.data;

import java.sql.*;

public class DBCalls extends DBConnectionManager{
	DBConnectionManager mananger = new DBConnectionManager();
    String path = System.getProperty("user.dir");
    String url = "jdbc:sqlite:"+path+"/src/se/tetris/data/lib/tetris.db"; 

	
	
	public boolean checkInitData() {
		Connection conn = null;
		boolean Result = false;
		
        String checkSql = "select count(*) from StInit;";
        
        if(mananger.connect() != null) {
        	try {
            	conn = DriverManager.getConnection(url);
            	
                Statement stmt = conn.createStatement();  
                
                System.out.println(checkSql); 
                stmt.execute(checkSql); 
        	}catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }finally {  
                try {  
                    if (conn != null) { 
                        conn.close();  
                    }  
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());  
                }  
            }

        }else {
        	
        }
        
        return Result;
	}
	
	public void insertInitData() {
		String sql = "CREATE TABLE IF NOT EXISTS StInit (\n"  
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
                + " code text NOT NULL DEFAULT '',\n" //window, Color, Level
                + " type integer NOT NULL DEFAULT 0\n"
                + ");"; 
        
        String initsql = "INSERT INTO StInit(code) VALUES('Window'), ('Color'), ('Level');";
        
	}
	
	
	public void InsertInitData() {
        String path = System.getProperty("user.dir");
        
        // SQLite connection string  
        String url = "jdbc:sqlite:"+path+"/src/se/tetris/data/lib/tetris.db"; 
        
        String sql = "";
        
        try{  
            Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
	}
	
    public static void createNewTable() {  
        String path = System.getProperty("user.dir");
        
        // SQLite connection string  
        String url = "jdbc:sqlite:"+path+"/src/se/tetris/data/lib/tetris.db"; 

        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS StInit (\n"  
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
                + " code text NOT NULL DEFAULT '',\n" //window, Color, Level
                + " type integer NOT NULL DEFAULT 0\n"
                + ");";
        

        
        try{  
            Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        
        // SQL statement for creating a new table  
        sql = "CREATE TABLE IF NOT EXISTS StGameKey (\n"  
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
                + " code text NOT NULL DEFAULT '',\n"  
                + " key text NOT NULL DEFAULT ''\n"  
                + ");";  
        try{  
            Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        
        sql = "CREATE TABLE IF NOT EXISTS Score (\n"  
                + " id integer PRIMARY KEY AUTOINCREMENT,\n" 
                + " mode integer Not NULL DEFAULT 0,\n" 
                + " type integer Not NULL DEFAULT 0,\n" 
                + " name text NOT NULL DEFAULT '',\n"  
                + " score integer NOT NULL DEFAULT 0\n"
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);
            
            Statement stmt = conn.createStatement();    
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        

    } 
	
}
