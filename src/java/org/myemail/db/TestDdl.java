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
		con=DBConnection.createConnection();
		//d.createMailUserTable(con);
		//d.createMessageTable(con);
		//d.deleteMailUserTable(con);
		//d.deleteMessageTable(con);
		
		//DBConnection.closeCon(con);
	}
}