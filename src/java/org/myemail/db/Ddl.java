package org.myemail.db;

import java.util.Properties;
import java.util.Scanner;
import java.io.FileReader;
import java.io.*;
import java.sql.*;

public class Ddl{
	void createMailUserTable(Connection con){
		try{
				Statement smt=con.createStatement();
				smt.execute("create table MailUser(userId number(5) Primary Key,userName varchar2(20),password varchar2(20), phoneNo number(10),emailId varchar2(30))");
				smt.execute("create sequence MailUser_Sequence start with 1 increment by 1 maxvalue 50");
				System.out.println("Table MailUser Created");
			}
		catch(SQLException e){
				System.out.println("Table MailUser not created");
				System.out.println(e);
				
			}
	}
	void deleteMailUserTable(Connection con){
		try{
			
			Statement smt=con.createStatement();
			smt.execute("drop table MailUser");
			smt.execute("drop sequence MailUser_sequence");
			System.out.println("Table MailUser Deleted");
		}
			catch(SQLException e){
				System.out.println("error while deleting Table MailUser");
			}
	}
	void createMessageTable(Connection con){
		try{
				Statement smt=con.createStatement();
				smt.execute("create table Message(mId number(5),mBody varchar2(50),mSubject varchar2(20), fromUserId number(5) references MailUser(UserId),toUserId Number(5) references MailUser(UserId),dateTime date,deleted number(1))");
				smt.execute("create sequence Message_Sequence start with 1 increment by 1 maxvalue 500");
				System.out.println("Table Message Created");
			}
		catch(SQLException e){
				System.out.println("Table Message not created");
				System.out.println(e);
				
			}
	}
	void deleteMessageTable(Connection con){
		try{
			Statement smt=con.createStatement();
			smt.execute("drop table Message");
			smt.execute("drop sequence Message_sequence");
			System.out.println("Table Message Deleted");
		}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("error while deleting Table Message");
			}
	}
}