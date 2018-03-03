package org.myemail.bl;

import org.myemail.db.*;
import org.myemail.domain.*;
import java.sql.*;
import java.util.*;
public class MessageBL {
	// CRUD operation
	public void createMessage(Message msg)	{
		DBUtil dbutil=new DBUtil();
		dbutil.insert(msg);
	}
	
	public List<Message> getMessages(String flag,MailUser currentUser) {
		DBUtil dbutil=new DBUtil();
		List<Message> lmsg=new LinkedList();
		List<Table> lt=null;
		//msg object contain toUserId=currentUserId and deleted=0
		//msg object contain fromUserId=currentUserId and deleted=0
		Message msg=Message.getMessage(flag,currentUser.userId);
		lt=dbutil.getList(msg);
		for(Table t:lt){
			lmsg.add((Message)t);
		}
		return lmsg;
	}
	
	// public boolean authenticateUser(MailUser mu){
		// List users= getUsers(mu);
		// if(users.size()==1) {
			// return true;
		// }
		// return false;
	// }
	// public List<MailUser> recipientUsers(MailUser mu){
		// List users= getUsers(mu);
	// }
}