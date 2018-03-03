package org.myemail.db;

import org.myemail.domain.Table;
import java.util.*;
import java.sql.*;
public class DBUtil {
	
	public void insert(Table t) 
	{
		String insertQuery=t.generateInsertQuery();
		Connection con=null;
		try{
			con=DBConnection.createConnection();
			Statement smt = con.createStatement(); 
			//System.out.println("Query  "+insertQuery);
			smt.executeUpdate(insertQuery);
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("error while creating");
		}
	}
	public List<Table> getList(Table t){
		//System.out.println("hellooooo");
		String selectQuery=t.generateSelectQuery();
		Connection con=null;
		ResultSet rs=null;
		List<Table> l = new LinkedList();
		//System.out.println(selectQuery);
		try{
			con=DBConnection.createConnection();
			Statement smt = con.createStatement(); 
			rs=smt.executeQuery(selectQuery);
		}
			
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("some error while fetching record");
		}
		
		try{
			while(rs.next()){
				l.add(t.generateObject(rs));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	public static String buildFilter(String existingFilter, String newConstraint){
		return existingFilter = existingFilter!=null? existingFilter + " and " + newConstraint: newConstraint;
	}
	
	public static String buildQuery(String tableName, Map<String, String> constraint) {
		String query = String.format("select * from %s", tableName);
		//System.out.println(query);
		String filter =null;
		for(Map.Entry<String,String> entry : constraint.entrySet()) {
			filter=buildFilter(filter, String.format("%s='%s'", entry.getKey(), entry.getValue()));
		}
		query=filter!=null?query+ " where "+filter : query;	
		//System.out.println(query);
		//return filter!=null?query+ " where "+filter : query;
		return query;
	}
}

