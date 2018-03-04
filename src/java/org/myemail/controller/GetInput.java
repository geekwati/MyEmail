package org.myemail.controller;

import java.util.Scanner;
public class GetInput{
	public static Object getInput(String inputMessage, Class clazz){
		Scanner reader=new Scanner(System.in);
		System.out.println(inputMessage);
		Object ob=null;
		if(clazz.equals(String.class)){
			String str=reader.nextLine();
			ob=str;
		}
		if(clazz.equals(Integer.class)){
			Integer n=reader.nextInt();
			ob=(Integer)n;
		}
		return ob;
	}
}
