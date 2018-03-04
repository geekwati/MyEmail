package org.myemail.db;

import java.util.Properties;
import java.util.Scanner;
import java.io.FileReader;
import java.io.*;
import java.sql.*;

public class TestDdl{
	public static void main(String... args){
	Connection con=null;
		Ddl d=new Ddl();
		con=createConnection();
		d.createMailUserTable(con);
		d.createMessageTable(con);
		//d.deleteMailUserTable(con);
		//d.deleteMessageTable(con);
		
		//con.closeCon();
	}
	
	public static Connection createConnection() {
		Connection con=null;
		try{
			//System.out.println("connection creation process started");
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:file:~/db/EmailServer/myemail;MVCC=FALSE", "sa", "");
			
		/*	Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","chat","chat");*/
			System.out.println("connection created");
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