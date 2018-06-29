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
	PreparedStatement pstmt = null;//��¼
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt=null;
    int count;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=HRM";
    String user="sa";  //�û���
    String password="1234";  //����
    int status;//����״̬
    
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
//    		System.out.println("��½�ɹ���");
//    	}else{  //����Ĳ�����ʱ
//            System.out.println("�˺Ż����������벻��ȷ��");
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
    //�������ݿ�
    public void connectDB(){
		try{
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HRM","sa","1234");
			System.out.println("ע��ɹ�");
			stmt=conn.createStatement();
			System.out.println("���ӳɹ�");
	    	status=1;//����״̬
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
    //�ر����ݿ�
    public void closeDB(){
		try{
			stmt.close();
			conn.close();
			System.out.println("�ر����ݿ�����");
		}catch(SQLException e){
			e.printStackTrace();
		}
    }
    //�������ݿ⣨��ɾ�ģ�
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
		//��ѯ���ݿ⣨��ѯ��
		public ResultSet executeQuery(String sql){
			ResultSet rs=null;
			try{
				rs=stmt.executeQuery(sql);
			}catch(SQLException e){
				e.printStackTrace();
			}
			return rs;
		}
		//רΪ��¼�Ĳ�ѯ
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
		    		System.out.println("��½�ɹ���");
		    	}else{  //����Ĳ�����ʱ
		            System.out.println("�˺Ż����������벻��ȷ��");
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
