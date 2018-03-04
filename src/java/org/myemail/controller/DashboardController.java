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
public class DashboardController{
	static MailService s=new MailService();
	public static void dashboard(MailUser mu) throws Exception {
		Menu m=new Menu();
		while(true){
			System.out.println("Hello "+mu.userName);
			m.queryMenu();
			int op=(Integer)GetInput.getInput("YOU WANT TO CHECK: ",Integer.class);
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
				//queryMenu=false;
				Controller.main();

			}
		}
	}

	public static void printMessage(String flag,List<Message> l){
		Integer i=0;
		System.out.println("No.    SUBJECT      BODY      USERNAME       DATE       TIME");
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
		List<Message> l=s.getMessages("inbox",fromUser);
		System.out.println("YOUR INBOX:");
		printMessage("inbox",l);
		
	}
	public static void sentBox(MailUser fromUser){
		List<Message> l=s.getMessages("sentBox",fromUser);
		System.out.println("YOUR SENTBOX:");
		printMessage("sentBox",l);
	}
	
	public static void composeMail(MailUser fromUser){
		MailUser mu1=new MailUser();
		System.out.println("TO: ");
		List<MailUser> l=s.recipientUsers(mu1);
		/*List of all users of MailUser table except logined user*/
		Integer nOfUsers=printRecipientUsers(l,fromUser);
		System.out.println("total recipient"+nOfUsers);
		
		Integer r=(Integer)GetInput.getInput("select recipient: ",Integer.class);
		MailUser toUser=getRecipientUser(nOfUsers,r,fromUser,l);
		System.out.println("recipient is "+toUser.userName);
		
		String subject=String.valueOf(GetInput.getInput("SUBJECT: ", String.class));
		String body=String.valueOf(GetInput.getInput("BODY: ", String.class));
		Message msg=new Message();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
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
		for(MailUser mUser:l){
			if(!mUser.equals(fromUser)){
				System.out.println(++i+" "+mUser.userName);
			}
		}
		return i;			 
	}
}
