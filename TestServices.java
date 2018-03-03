import java.sql.*;

public class TestServices{
		//Connection con;
	public static void main(String... args){
		testInsertUser();
		testAuthenticateUser1();
		testAuthenticateUser2();

	}
	static void testInsertUser(){
		MailUser mu=new MailUser();
		mu=MailUser.getUser("hello","java",32,"des");
		Services s=new Services();
		s.insertUser(mu);
		s.showUser("hello","java");
	}
	static void testAuthenticateUser1(){
		MailUser mu=null;
		Services s=new Services();
		mu=s.authenticateUser("hello","java");
		if(mu!=null)
			System.out.println("success test1 of authenticate user");
		else
			System.out.println("failure test1 of authenticate user");
		
	}
	static void testAuthenticateUser2(){
		MailUser mu=null;
		Services s=new Services();
		mu=s.authenticateUser("hel","java");
		if(mu!=null)
			System.out.println("success test2 of authenticate user");
		else
			System.out.println("failure test2 of authenticate user");
		
	}
	
}