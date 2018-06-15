package com.hrm.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_test {

	public static void main(String[] args) throws SQLException {
		PreparedStatement ps = null;
	    Connection conn = null;
	    ResultSet rs = null;
	    Statement stmt=null;
	    
	     
	    
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=HRM";
	    String user="sa";  //�û���
	    String password="1234";  //����
	    try{
	    	//ע��
	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	System.out.println("ע��ɹ�");
	    	//����
	    	conn=DriverManager.getConnection( url,user,password);
	    	System.out.println("���ӳɹ�");
	    	//��������
	    	stmt=conn.createStatement();
	    	String sql = "select * from Users;";
	    	rs = stmt.executeQuery(sql);
	    	
	    	while(rs.next()){
	    		String Uno=rs.getString("Uno");
	    		String account=rs.getString("Account");
	    		String psw=rs.getString("Password");
	    		System.out.println(Uno+" "+account+" "+psw );
	    		
	    	}
	    	System.out.println(rs);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	if(rs!=null){
	    		rs.close();
	    		System.out.println("�ر�ResultSet�ɹ�");
	    	}
	    	if(stmt!=null){
	    		stmt.close();
	    		System.out.println("�ر�Statement�ɹ�");
	    	}
	        if(conn!=null){
	        	conn.close();
	        	System.out.println("�ر�Connection�ɹ�");
	        }
	    }
	}

}
