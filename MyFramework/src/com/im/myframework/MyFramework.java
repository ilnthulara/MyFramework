package com.im.myframework;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class MyFramework {
	static String url = "";
	static String username = "";
	static String password = "";
	
	public static void init(String dbName, String un, String pw) {
		url = "jdbc:mysql://localhost:3306/"+dbName;
		username = un;
		password = pw;
	}
	
	public static void save(Object obj) {
		Class cls = obj.getClass();
		String name = cls.getSimpleName().toLowerCase();
		String columns ="";
		String values ="";
		
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String fname = field.getName();
			columns += fname+",";
			try 
			{
				values += "'"+field.get(obj)+"',";
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		columns = columns.substring(0, columns.length()-1);
		values = values.substring(0, values.length()-1);
		
		String sql = "INSERT INTO "+name+" ("+columns+") VALUES ("+values+")";
		System.out.println("SQL: "+sql);
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
