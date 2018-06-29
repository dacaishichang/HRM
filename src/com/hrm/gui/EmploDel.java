package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmploDel extends JPanel {
	private JTextField name;
	private JTextField eno;
	private SQLManager sm;
	private ResultSet rs;

	/**
	 * Create the panel.
	 */
	public EmploDel() {
		setLayout(null);
		
		name = new JTextField();
		name.setToolTipText("\u90E8\u95E8\u6DFB\u52A0");
		name.setText((String) null);
		name.setColumns(10);
		name.setBounds(25, 75, 74, 21);
		add(name);
		
		eno = new JTextField();
		eno.setToolTipText("\u9009\u62E9\u90E8\u957F");
		eno.setText((String) null);
		eno.setColumns(10);
		eno.setBounds(148, 75, 86, 21);
		add(eno);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Ename=name.getText();
				String Eno=eno.getText();
				try {
					delEployee(Eno,Ename);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				reset();
			}

			

		});
		button.setBounds(78, 138, 109, 31);
		add(button);
		
		JLabel label = new JLabel("\u5458\u5DE5\u540D\u79F0");
		label.setBounds(25, 50, 66, 15);
		add(label);
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u53F7");
		label_1.setBounds(153, 50, 67, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u5458\u5DE5\u5220\u9664\uFF08\u9009\u586B\uFF09");
		label_2.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_2.setBounds(78, 25, 142, 21);
		add(label_2);
		
		reset();

	}
	private void delEployee(String eno, String ename) throws SQLException {
		sm = SQLManager.createInstance();
		//通过得到Uno，删除Have对应，再在部门删除（如果是部门主任则改为管理员，删除Attend）在删除Deartment中的数据，最后求出Serve的职位
		eno=decide(eno,ename);
		int c2=deleteAttend(eno);
		int c3=deleteServe(eno);
		int c1=deleleHave(eno);
		int c4=deleteEmployee(eno);
		if(c1==2&&c2==1&&c3==1&&c4==1){
			JOptionPane.showMessageDialog(null, "删除用户成功！");
		}else{
			JOptionPane.showMessageDialog(null, "删除用户失败！");
		}
	}
	private int deleteEmployee(String eno2) {
		sm.connectDB();
		String sql2 = "delete from Employee where Eno="+eno2+";";
		int count=sm.executeUpdate(sql2);
		
		sm.closeDB();
		return count;
	}
	private int deleteServe(String eno2) throws SQLException {
		sm.connectDB();
		String sql2 = "delete from Serve where Eno="+eno2+";";
		int count=0;
		count=sm.executeUpdate(sql2);
		
		if(count==1){
			System.out.println("删除任职成功！");
		}else{
			System.out.println("删除任职失败！");
		}
		sm.closeDB();
		return count;
		
	}
	private int deleteAttend(String eno2) throws SQLException {
		sm.connectDB();
//		String Dno=null;
//		String sql1 = "select * from Attend where Eno="+eno2+";";
//		System.out.println(sql1);
//		rs = sm.executeQuery(sql1);
//		while(rs.next()){
//			Dno=rs.getString("Dno");
//		}
		String sql3="update Department set DirectorNo=100000000 where DirectorNo="+eno2+";";
		System.out.println(sql3);
		int count2 =sm.executeUpdate(sql3);
		System.out.println("是否部门主任："+Integer.toString(count2));
		String sql2 = "delete from Attend where Eno="+eno2+";";
		System.out.println(sql2);
		int count=0;
		count=sm.executeUpdate(sql2);
		
		if(count==1){
			System.out.println("删除所属成功！");
		}else{
			System.out.println("删除所属失败！");
		}
		sm.closeDB();
		return count;
	}
	private int deleleHave(String eno2) throws SQLException {
		String Uno=null;
		sm.connectDB();
		String sql1 = "select * from Have where Eno="+eno2+";";
		System.out.println(sql1);
		rs = sm.executeQuery(sql1);
		while(rs.next()){
			Uno=rs.getString("Uno");
		}
		String sql2 = "delete from Have where Eno="+eno2+";";
		System.out.println(sql2);
		int count=0;
		count=sm.executeUpdate(sql2);
		String sql3 = "delete from Users where Uno="+Uno+";";
		System.out.println(sql3);
		int count2=0;
		count2=sm.executeUpdate(sql3);
		if(count==1&&count2==1){
			System.out.println("删除账号成功！");
		}else{
			System.out.println("删除账号失败！");
		}
		sm.closeDB();
		return count+count2;
	}
	private String decide(String eno2, String ename) throws SQLException {
		String sql1="select * from Employee where Ename=";
		if(!eno2.isEmpty()){
			return eno2;
		}
		
		if(!ename.isEmpty()){
			sql1=sql1+"'"+ename+"';";
			System.out.println(sql1);
			sm.connectDB();
			rs = sm.executeQuery(sql1);
			while(rs.next()){
				eno2=rs.getString("Eno");
			}
			sm.closeDB();
		}
		//新的
		return eno2;
		
	}
	private void reset() {
		name.setText(null);
		eno.setText(null);
	}
}
