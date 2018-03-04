package org.myemail.controller;

import org.myemail.service.*;
import org.myemail.domain.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileReader;
import java.io.*;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.text.DateFormat;
import java.util.Date;  
import java.util.Collections;
public class Controller{
	static MailService s=new MailService();
	public static void main(String ... args) throws Exception {
	
		System.out.println("WELCOME TO EMAIL SERVICES");
		Menu m=new Menu();
		
		while(true){
			m.welcomeMenu();
			int op=(Integer)GetInput.getInput("Enter Your Option: ",Integer.class);
			MailUser mu=null;
			switch(op){
			case 1:	 //NEW USER
						mu=newUser();
					break;
			case 2:   //LOGIN
					mu=login();
					if(mu==null){
						System.out.println("USERNAME OR PASSWORD IS WRONG ");
						System.out.println("TRY AGAIN!");			
					}
					else{
						DashboardController.dashboard(mu);
					}
		
					break;
			case 3:  //EXIT
					System.exit(0);
			}
			
		}
	}
	
	public static MailUser login(){
		MailUser mu =null;
		mu=MailUser.getUser(
							String.valueOf(GetInput.getInput("Enter ur Name: ", String.class)),
							String.valueOf(GetInput.getInput("Enter ur Password: ",String.class))
							);
		mu=s.authenticateUser(mu);	
		return mu;
	}
	public static MailUser newUser(){
		MailUser mu =null;
		System.out.println("Enter Your Details");
		mu=MailUser.getUser(
							String.valueOf(GetInput.getInput("Enter ur Name: ", String.class)),
							String.valueOf(GetInput.getInput("Enter ur Password: ",String.class)),
							(Integer)GetInput.getInput("Enter ur Phone: ",Integer.class),
							String.valueOf(GetInput.getInput("Enter ur Email: ",String.class))
							);
		s.createUser(mu);
		return mu;
						// Scanner reader=new Scanner(System.in);
						
						// System.out.println("Name: ");
						/*int id=reader.nextInt();*/
						// String name=reader.nextLine();
						
						// System.out.println("Password: ");
						// String password=reader.nextLine();
						
						// System.out.println("Phone No: ");
						// int pno=reader.nextInt();
						
						// System.out.println("Email Id: ");
						// String emailId=reader.nextLine();
						
						
						// mu=MailUser.getUser(name,password,pno,emailId);
						// s.createUser(mu);
						
						/*MailUser.generate(
							String.valueOf(getInput("Enter ur Name: ", String.class)),
							String.valueOf(getInput("Enter ur Password: ",String.class)),
							Integer.valueOf(getInput("Enter ur Phone: ",Integer.class))
							String.valueOf(getInput("Enter ur Email: ",String.class))),
							
						);
						
						MailUser.build()
							.username(getInput("Enter ur Name: "))
							.password(getInput("Enter ur password: "))
							.email(getInput("Enter ur email: "))
							.phonenumber(getInput("Enter ur ph: "));
						*/
	}
	
			
}
