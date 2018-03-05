package org.myemail.db;

import java.sql.*;
import java.util.Properties;
//import java.io.FileReader;
//import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
//import java.lang.IllegalArgumentException;
public class DBConnection{

	public static Connection createConnection() {
		Connection con=null;
		Properties config=new Properties();
		try{//CurrentClass.class.getClassLoader().getResourceAsStream
			InputStream stream=DBConnection.class.getClassLoader().getResourceAsStream("dbcon.properties");
			//new FileReader(getClass().getResourceAsStream("dbcon.properties"));
			//this.getClass().getResourceAsStream("dbcon.properties");
			//new FileReader("dbcon.properties");
			//f:/project/corejava7_8/test/EmailServer1/resources/
			config.load(stream);
		}
		//getResourceAsStream throughs NullPointerException
		//NullPointerException is a RunTimeException -no nedd to catch it but still I'm Catching it
		catch(NullPointerException e){
			System.out.println("Error occured during reading properties file");
			e.printStackTrace();
		}
		//config.load() throughs IOException-compileTimeException
		catch(IOException e){
			System.out.println("Error occured during reading properties file");
			e.printStackTrace();
		}
		//IOException - if an error occurred when reading from the input stream.
		//IllegalArgumentException - if the input stream contains a malformed Unicode escape sequence.
		//IllegalArgumentException is a RunTimeException- no need to catch but still I'm catch it
		// catch(IllegalArgumentException e){
		// 	System.out.println("properties file contains a malformed unicode escape sequence ");
		// 	e.printStackTrace();
		// }
		
		try{
			//System.out.println("connection creation process started");
			Class.forName(config.getProperty("Driver"));
			con=DriverManager.getConnection(
				config.getProperty("ConUrl"),config.getProperty("UserName"),config.getProperty("Password"));
			//System.out.println("connection created");
			// Class.forName("org.h2.Driver");
			// con=DriverManager.getConnection("jdbc:h2:file:~/db/EmailServer/myemail;AUTO_SERVER=TRUE", "sa", "");
			// //System.out.println("connection created");
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","chat","chat");*/
			
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
						
			