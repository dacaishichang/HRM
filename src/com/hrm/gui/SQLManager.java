package com.hrm.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.*;

public class SQLManager {
	static SQLManager p;
	PreparedStatement pstmt = null;//登录
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt=null;
    int count;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=HRM";
    String user="sa";  //用户名
    String password="1234";  //密码
    int status;//连接状态
    
    public SQLManager() {
    	
    }
//    public String LogIn(String account,String password) throws SQLException{
//    	int is_true=0;
//    	String sql="select * from Users where Account=? and Password=?;";
//    	pstmt=conn.prepareStatement(sql);
//    	pstmt.setString(1, account);
//    	pstmt.setString(2, password);
//    	rs = pstmt.executeQuery();
//    	if(rs.next()){
//    		is_true=1;
//    		System.out.println("登陆成功！");
//    	}else{  //输入的不符合时
//            System.out.println("账号或者密码输入不正确！");
//        }
//    	String Uno=rs.getString(1);
//    	return Uno;
//    }
    public static SQLManager createInstance(){
		if(p==null)
		{
			p=new SQLManager();
		}
		return p;
	}
    //连接数据库
    public void connectDB(){
		try{
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HRM","sa","1234");
			System.out.println("注册成功");
			stmt=conn.createStatement();
			System.out.println("连接成功");
	    	status=1;//连接状态
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
    //关闭数据库
    public void closeDB(){
		try{
			stmt.close();
			conn.close();
			System.out.println("关闭数据库连接");
		}catch(SQLException e){
			e.printStackTrace();
		}
    }
    //更新数据库（增删改）
	public int executeUpdate(String sql){
		int ret=0;
		try{
			ret=stmt.executeUpdate(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ret;
	}
		//查询数据库（查询）
		public ResultSet executeQuery(String sql){
			ResultSet rs=null;
			try{
				rs=stmt.executeQuery(sql);
			}catch(SQLException e){
				e.printStackTrace();
			}
			return rs;
		}
		//专为登录的查询
		public int executeLogin(String a,String b){
			String account;
			
			ResultSet rs=null;
			int Uno = 0;
			String sql="select * from Users where Account=? and Password=?;";
			try{
				pstmt=conn.prepareStatement(sql);
		    	pstmt.setString(1, a);
		    	pstmt.setString(2, b);
		    	rs = pstmt.executeQuery();
		    	if(rs.next()){
		    		System.out.println("登陆成功！");
		    	}else{  //输入的不符合时
		            System.out.println("账号或者密码输入不正确！");
		        }
		    	Uno=rs.getInt("Uno");
			}catch(SQLException e){
				e.printStackTrace();
			}
			return Uno;
		}
		public ResultSet executeView(String a,String b,String c,String d){
			String sql="select "+a+" from "+b+" where "+c+" = "+d;
			ResultSet rs=null;
			try{
				rs=stmt.executeQuery(sql);
			}catch(SQLException e){
				e.printStackTrace();
			}
			return rs;
		}
}
