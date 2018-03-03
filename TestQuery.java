import java.sql.*;

public class TestQuery{
	public static void main(String... args){
	Connection con=null;
		con=createCon();
	Query q=new Query();
	/*insert mailUser*/
	MailUser mu=new MailUser();
	mu=MailUser.getUser("jyoti","goel",32,"des");
	System.out.println(mu.userId);
		System.out.println(mu.userName);
		System.out.println(mu.password);
		System.out.println(mu.phoneNo);
		System.out.println(mu.emailId);
	 String str=mu.createInsertQuery();
	 q.executeDmlQuery(str,con);
		
	q.selectUser(con);

	}
	public static Connection createCon(){
		Connection con=null;
		try{
			System.out.println("connection creation process started");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","chat","chat");
			if(con!=null)
				System.out.println("connection created");
		}
			
		catch(Exception e){
			if(con==null)
				System.out.println("Connection not created" );
			else
				System.out.println(e);
	}
	return con;
	}

}