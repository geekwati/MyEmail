package org.myemail.bl;

import org.myemail.db.*;
import org.myemail.domain.*;
import java.util.*;
import java.sql.*;
public class UserBL {
	// CRUD operation
	public void createUser(MailUser mu)	{
		DBUtil dbutil=new DBUtil();
		dbutil.insert(mu);
	}
	
	public List<MailUser> getUsers(MailUser mu) {
		// TO DO : repeated Line
		DBUtil dbutil=new DBUtil();
		List<MailUser> lm=new LinkedList();
		List<Table> lt=dbutil.getList(mu);
		for(Table t:lt){
			lm.add((MailUser)t);
		}
		return lm;//(List<MailUser>)dbutil.getList(mu);
	}
	
	public MailUser authenticateUser(MailUser mu){
		List<MailUser> lUsers= getUsers(mu);
		MailUser mu2=new MailUser();
		if(lUsers.size()==1) {
			for(MailUser mu1:lUsers)
			 mu2=mu1;
			return mu2;
		}
		return null;
	}
	public List<MailUser> recipientUsers(MailUser mu){
		List<MailUser> users= getUsers(mu);
		return users;
	}
}