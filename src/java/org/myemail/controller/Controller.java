package org.sample.myemail.controller;

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
	static Services s=new Services();
	public static void main(String ... args) throws Exception {
	
		System.out.println("WELCOME TO EMAIL SERVICES");
		Menu m=new Menu();
		
		while(true){
			m.welcomeMenu();
			 boolean queryMenu=true;
			System.out.println("Enter your Option");
			Scanner scan=new Scanner(System.in);
			int op=scan.nextInt();
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
						queryMenu=false;
					}
					else
						System.out.println("Hello "+mu.userName);
		
					break;
			case 3:  //EXIT
					System.exit(0);
			}
			while(queryMenu){
				m.queryMenu();
				System.out.println("YOU WANT TO CHECK");
				op=scan.nextInt();
				switch(op){
				case 1: //COMPOSE MAIL
					composeMail(mu);
					break;
				case 2://INBOX
					// /*show all incoming message of user where column ToUser is equal to authnticated user
					// and also where deleted column is false*/
					inbox(mu);
					break;
				case 3://SENT MAIL
					// /*show all sent msg i.e. where column FromUser is equal to authenticated user*/
					sentBox(mu);
					break;
				case 4://TRUSH
					// /*where ToUser is authenticated user and deleted column is true*/
					break;
				case 5://LOGOUT
					//s.stopServer(); 
					queryMenu=false;
				}
			}
		}
	}
	public static void printMessage(String flag,List<Message> l){
		Integer i=0;
		System.out.println("No.    SUBJECT      BODY      USERNAME       DATE       TIME");
		//String name=null;
		MailUser mu=null;
		Collections.sort(l,Collections.reverseOrder());
		//Collections.sort(l,Message.subjectCompare);
		for(Message msg:l){
			
			if(flag.equals("inbox")){
				/*print message*/
				mu=s.authenticateUser(mu.getUser(msg.fromUserId));
				System.out.printf("%d   ||%s     ||%s     ||%s     ||%s",++i,msg.mSubject,msg.mBody,mu.userName,msg.dateTime);
				System.out.println();
			}	
			/*To Do display name of user instead of its id*/
			if(flag.equals("sentBox")){
				/*print message*///msg.toUserId
				mu=s.authenticateUser(mu.getUser(msg.toUserId));
				System.out.printf("%d   ||%s     ||%s     ||%s     ||%s",++i,msg.mSubject,msg.mBody,mu.userName,msg.dateTime);
				System.out.println();
			}
		}
		if(i==0){
			System.out.printf("NO MESSAGE IN YOUR %s",flag);
			System.out.println();
		}
			
	}
	public static void inbox(MailUser fromUser){
		//Messsge msg=Message.getMessage(fromUser.userId);
		
		//msg.getMessage()
		List<Message> l=s.getMessages("inbox",fromUser);
		System.out.println("YOUR INBOX:");
		printMessage("inbox",l);
		
	}
	public static void sentBox(MailUser fromUser){
		//Messsge msg=Message.getMessage(fromUser.userId);
		
		//msg.getMessage()
		List<Message> l=s.getMessages("sentBox",fromUser);
		System.out.println("YOUR SENTBOX:");
		printMessage("sentBox",l);
	}
	
	public static Object getInput(String inputMessage, Class clazz){
		Scanner reader=new Scanner(System.in);
		System.out.println(inputMessage);
		Object ob=null;
		if(clazz.equals(String.class)){
			String str=reader.nextLine();
			ob=str;
		}
		if(clazz.equals(Integer.class)){
			Integer n=reader.nextInt();
			ob=(Integer)n;
		}
		return ob;
	}
	public static void composeMail(MailUser fromUser){
		MailUser mu1=new MailUser();
		System.out.println("TO: ");
		List<MailUser> l=s.recipientUsers(mu1);
		/*List of all users of MailUser table except logined user*/
		Integer nOfUsers=printRecipientUsers(l,fromUser);
		System.out.println("total recipient"+nOfUsers);
		//System.out.println("select recipient");
		//Scanner reader2=new Scanner(System.in);
		Integer r=(Integer)getInput("select recipient: ",Integer.class);//reader2.nextInt();
		MailUser toUser=getRecipientUser(nOfUsers,r,fromUser,l);
		System.out.println("recipient is "+toUser.userName);
		//System.out.println("SUBJECT: ");						
		String subject=String.valueOf(getInput("SUBJECT: ", String.class));//reader2.nextLine();
		//System.out.println("BODY: ");
		String body=String.valueOf(getInput("BODY: ", String.class));//reader2.nextLine();
		Message msg=new Message();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		//System.out.println(formatter.format(date));
		/* string date formatter.format(date)*/
		/*maintain argument list of getMessage()*/
		msg=Message.getMessage(body,subject,fromUser.userId,toUser.userId,formatter.format(date) );
		
		s.createMessage(msg);
					
					
	}
	public static MailUser getRecipientUser(Integer nOfUsers,Integer selectedUserN,MailUser fromUser,List<MailUser> l){
		if(selectedUserN>nOfUsers)
			return null;
		Integer i=0;
		for(MailUser mUser:l){
		if((!mUser.equals(fromUser))&&(++i==selectedUserN))
			return mUser;
		}
		return null;
	}
	public static Integer printRecipientUsers(List<MailUser> l,MailUser fromUser){
		Integer i=0;
		Collections.sort(l);//implicitly calls MailUser class compareTo() method
		//MailUser mu=new MailUser();
		for(MailUser mUser:l){
			if(!mUser.equals(fromUser)){
				System.out.println(++i+" "+mUser.userName);
			}
		}
		return i;			 
	}
	public static MailUser login(){
		MailUser mu =null;
		// Scanner reader1=new Scanner(System.in);
		// System.out.println("Name: ");
		// String name1=reader1.nextLine();
		// System.out.println("Password: ");
		// String password1=reader1.nextLine();
		//mu=MailUser.getUser(name1,password1);
		mu=MailUser.getUser(
							String.valueOf(getInput("Enter ur Name: ", String.class)),
							String.valueOf(getInput("Enter ur Password: ",String.class))
							);
		mu=s.authenticateUser(mu);	
		return mu;
	}
	public static MailUser newUser(){
		MailUser mu =null;
		System.out.println("Enter Your Details");
		mu=MailUser.getUser(
							String.valueOf(getInput("Enter ur Name: ", String.class)),
							String.valueOf(getInput("Enter ur Password: ",String.class)),
							(Integer)getInput("Enter ur Phone: ",Integer.class),
							String.valueOf(getInput("Enter ur Email: ",String.class))
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
