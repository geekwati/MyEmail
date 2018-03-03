package org.myemail.db;

import java.sql.*;
//import java.util.Properties;
//import java.io.FileReader;
//import java.io.FileNotFoundException;
public class DBConnection{

	public static Connection createConnection() {
		Connection con=null;
		try{
			//System.out.println("connection creation process started");
			
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:file:~/db/myemail;AUTO_SERVER=TRUE", "sa", "");
		}
			
			catch(Exception e){
				
				System.out.println("Connection is not created" );
				e.printStackTrace();
				
			}
	
	return con;
	}
	void closeCon(Connection con){
		try{
				con.close();
				System.out.println("Connection closed");
			}
			
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("error while closing connection");
		}
	}
}
		// Properties property=new Properties();
		// try{
			// FileReader reader=new FileReader("dbcon.properties");
			// property.load(reader);
		// }
		// catch(FileNotFoundException e){
			// System.out.println("FileNotFound");
		// }
		// switch(op){
				// case 1: 
					
					// con=createConnection(property.getProperty("oracleDriver"),property.getProperty("oracleConUrl"),
						// property.getProperty("oracleUserName"),property.getProperty("oraclePassword"));
					// break;
				// case 2:
					// con=createConnection(property.getProperty("mysqlDriver"),property.getProperty("mysqlConUrl"),
						// property.getProperty("mysqlUserName"),property.getProperty("mysqlPassword"));
					// break;
				// case 3:
					// con=createConnection(property.getProperty("h2Driver"),property.getProperty("h2ConUrl"),
						// property.getProperty("h2UserName"),property.getProperty("h2Password"));
					// break;
				// case 4: return con;
			// }
			// return con;
	// }
			
	// Connection createConnection(String Driver,String ConUrl,String UserName,String Password){
		// Connection con=null;
		//try{
			//System.out.println("connection creation process started");
			//Class.forName(Driver);
			//con=DriverManager.getConnection(ConUrl,UserName,Password);
						
			