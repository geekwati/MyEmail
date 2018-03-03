import java.util.*;
import java.sql.*;
public class MailUser extends Table implements Comparable<MailUser>{
	int userId;
	String userName;
	String password;
	int phoneNo;
	String emailId;
	
	public static MailUser getUser(int id){
		MailUser mu=new MailUser();
		mu.userId=id;
		return mu;
	}
	public static MailUser getUser(String name){
		MailUser mu=new MailUser();
		mu.userName=name;
		return mu;
	}
	public static MailUser getUser(String name,String password){
		MailUser mu=getUser(name);
		mu.password=password;
		return mu;
	}
	public static MailUser getUser(String name,String password,int pno,String emailId){
		MailUser mu = getUser(name, password);
		mu.phoneNo=pno;
		mu.emailId=emailId;
		return mu;
	}
	public static MailUser getUser(int id,String name,String password,int pno,String emailId){
		MailUser mu=getUser(name,password,pno,emailId);
		mu.getUser(id);
		return mu;
	}
	public int compareTo(MailUser mu){
		return this.userName.compareTo(mu.userName);
		/*userName is a String and String class implements comparable too and override compareTo() so here, we just call that already implemented method of String class
		*/
	}
	
	public Table generateObject(ResultSet rs){
		MailUser mu=new MailUser();
		try{
			mu.userId=rs.getInt(1);
			mu.userName=rs.getString(2);
			mu.password=rs.getString(3);
			mu.phoneNo=rs.getInt(4);
			mu.emailId=rs.getString(5);
		
		// mu.userId=(rs.getObject("userId")).intValueExact();
		// mu.userName=(String)rs.getObject("userName");
		// mu.password=(String)rs.getObject("password");
		// mu.phoneNo=(Integer)rs.getObject("phoneNo");
		// mu.emailId=(String)rs.getObject("emailId");
		}
		catch(Exception e){
			//System.out.println("dffur");
			
			e.printStackTrace();
		}
		return mu;
	}
	
	public String generateInsertQuery(){
		
		String str="insert into MailUser values(MailUser_Sequence.nextval,'"+userName.trim()+"','"+password+"',"+phoneNo+",'"+emailId.trim()+"')";
		return str;
	}
	
	public Map<String, String> getConstraint() {
		Map<String , String> map = new HashMap<String , String>();
		if(this.userName !=null ) {
			map.put("userName", this.userName);
		}
		
		if(this.userId !=0 ) {
			map.put("userId", String.valueOf(this.userId));
		}
		if(this.password!=null){
			map.put("password",this.password);
		}
		if(this.phoneNo!=0){
			map.put("phoneNo",String.valueOf(this.phoneNo));
		}
		if(this.emailId!=null){
			map.put("emailId",this.emailId);
		}
			
		return map;
	}
	
	public String generateSelectQuery(){
	
		Map<String , String> map = new HashMap<String , String>();
		
		// if(this.userName !=null ) {
			// map.add("name", this.userName);
		// }
		
		// if(this.id !=null ) {
			// map.add("name", this.userName);
		// }
		map=getConstraint();	
		return DBUtil.buildQuery("MailUser", map);
		
	}
	public boolean equals(Object o){
		if(o==this)
			return true;
		if(!(o instanceof MailUser))
			return false;
		MailUser mu=(MailUser)o;
		return this.userName.equals(mu.userName);
	}
	// public String createUpdateQuery(int pno){
		
		// String str="update MailUser set Phoneno='"+pno+"' where Name='"+userName+"'";
		// return str;
	// }
	// public String createDeleteQuery(){
		// String str="delete from MailUser where Name='"+userName+"'";
		// return str;
	// }
	
	// To do override Equals Method for compose mail to userlist T/F
	// To do Comparator Vs Comparable for sortedlist -1/0/1
}