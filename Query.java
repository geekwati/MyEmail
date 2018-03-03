import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Query{
	 
	
	
	void executeDmlQuery(String query,Connection con){
		
		try{
			Statement st = con.createStatement(); 
			st.executeUpdate(query);
		}
		catch(SQLException e){
			System.out.println("error while insrting");
		}
	}
		
	
	
	public MailUser selectUser(String name,String password,Connection con){
	
		MailUser mu=new MailUser();
		mu=null;
		try{
	
				Statement smt=con.createStatement();
				ResultSet rs=smt.executeQuery("select * from MailUser where Name='"+name+"' and Password='"+password+"'");
				
				while(rs.next()){
					System.out.println("current data of record");
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"    "+rs.getInt(4)+"   "+rs.getString(5));
					
					mu=MailUser.getUser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
					
				}
				if(mu==null)
					System.out.println("end of searching no record found");
		}
		catch(SQLException e){
			System.out.println("error while fetching record");
		}
		return mu;
	}
	public void selectUser(Connection con){
	
		try{
				Statement smt=con.createStatement();
				ResultSet rs=smt.executeQuery("select * from MailUser");
				
				boolean found=false;
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"    "+rs.getInt(4)+"   "+rs.getString(5));
					found=true;
				}
				if(!found)
					System.out.println("end of searching no record found");
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("some error while fetching record");
		}
	}
	
}