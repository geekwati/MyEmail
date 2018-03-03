import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;  
public class Message extends Table implements Comparable<Message>{
	//here  compareTo() of Comparable provide default sorting to array and collection of custom class
	int mId;
	String mBody;
	String mSubject;
	int fromUserId;
	int toUserId;
	String dateTime;
	int deleted;
	public int compareTo(Message msg){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			java.util.Date date1 = new java.util.Date();  
			java.util.Date date2 = new java.util.Date();  
			try{
				date1=formatter.parse(this.dateTime);
				date2=formatter.parse(msg.dateTime);
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("problem in date conversion");
				
			}
			
			return date1.compareTo(date2);//int;
		
	}
	public static Comparator<Message> subjectCompare=new Comparator<Message>(){
		public int compare(Message m1,Message m2){
		return m1.mSubject.compareTo(m2.mSubject);
		}
	};
	public static Message getMessage(String flag,int userId){
		Message msg=new Message();
		if(flag.equals("inbox")){
			//msg object contain toUserId=currentUserId and deleted=0
			msg.toUserId=userId;
		}
			
		if(flag.equals("sentBox")){
			//msg object contain fromUserId=currentUserId and deleted=0
			msg.fromUserId=userId;
		}
		msg.deleted=0;
		return msg;
	}
	// public static Message getMessage(int toUserId){
		// Message msg=new Message();
		// msg.toUserId=toUserId;
		// return msg;
	// }
	public static Message getMessage(String mBody,String mSubject,int fromUserId,int toUserId,String dateTime){
		//Message msg=getMessage(fromUserId);
		//msg=getMessage(toUserId);
		Message msg=new Message();
		msg.mBody=mBody;
		msg.mSubject=mSubject;
		msg.fromUserId=fromUserId;
		msg.toUserId=toUserId;
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		//System.out.println((dateTime));
		msg.dateTime=dateTime;
		msg.deleted=0;
		return msg;
	}
	public Table generateObject(ResultSet rs){
		Message msg=new Message();
		try{
		msg.mId=rs.getInt(1);
		msg.mBody=rs.getString(2);
		msg.mSubject=rs.getString(3);
		msg.fromUserId=rs.getInt(4);
		msg.toUserId=rs.getInt(5);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		//System.out.println(formatter.format(rs.getDate(6)));
		msg.dateTime=formatter.format(rs.getTimestamp(6));
		//System.out.println("msg  "+formatter.format(rs.getDate(6)));
		msg.deleted=rs.getInt(7);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return msg;
	}
	public String generateInsertQuery(){
		
		String str="insert into Message values(Message_Sequence.nextval,'"+mBody.trim()+"','"+mSubject.trim()+"',"+fromUserId+","+toUserId+",now(),"+0+")";
		return str;
	}
	
	public Map<String, String> getConstraint() {
		Map<String , String> map = new HashMap<String , String>();
		if(this.mId !=0 ) {
			map.put("mId", String.valueOf(this.mId));
		}
		
		if(this.mBody !=null ) {
			map.put("mBody", this.mBody);
		}
		if(this.mSubject!=null){
			map.put("mSubject",this.mSubject);
		}
		if(this.fromUserId!=0){
			map.put("fromUserId",String.valueOf(this.fromUserId));
		}
		if(this.toUserId!=0){
			map.put("toUserId",String.valueOf(this.toUserId));
		}
		if(this.dateTime!=null){
			map.put("dateTime",this.dateTime);
		}
		if(this.deleted==0){
			map.put("deleted",String.valueOf(this.deleted));
		}
		if(this.deleted==1){
			map.put("deleted",String.valueOf(this.deleted));
		}	
		return map;
	}
	
	public String generateSelectQuery(){
	
		Map<String , String> map = new HashMap<String , String>();
		map=getConstraint();	
		return DBUtil.buildQuery("Message", map);
		
	}
	
}