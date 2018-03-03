package org.myemail.service;

import org.myemail.bl.*;
import org.myemail.domain.*;
import java.util.*;
import java.sql.*;
public class MailService{
	public void createMessage(Message msg){
		MessageBL mbl=new MessageBL();
		mbl.createMessage(msg);
	}
	public void createUser(MailUser mu){
		UserBL ubl=new UserBL();
		ubl.createUser(mu);
	}
	
	
	public MailUser authenticateUser(MailUser mu){
		UserBL ubl=new UserBL();
		mu=ubl.authenticateUser(mu);
		return mu;
	}
	
	public List<MailUser> recipientUsers(MailUser mu){
		UserBL ubl=new UserBL();
		List<MailUser> l=ubl.recipientUsers(mu);
		return l;
	}
	/*public MailUser getUserName(MailUser mu){
		UserBL ubl=new UserBL();
		mu=ubl.getUserName(mu);
		return mu;
	}*/
	public List<Message> getMessages(String flag, MailUser currentUser){
		MessageBL mbl=new MessageBL();
		List<Message> l=mbl.getMessages(flag,currentUser);
		return l;
		
	}
	
	
}