package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SerChange extends JPanel {
	private JTextField newPosition;
	private JTextField name;
	private JTextField oldPosition;
	private SQLManager sm;
	private ResultSet rs;
	private JTextField newStatus;

	/**
	 * Create the panel.
	 */
	public SerChange() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(216, 44, 121, 156);
		add(panel);
		
		JButton buttonsure = new JButton("\u786E\u8BA4\u4FEE\u6539");
		buttonsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Ename=name.getText();
				String oldpo=oldPosition.getText();
				String newpo=newPosition.getText();
				String newstatus=newStatus.getText();
				String sql=null;
				try {
					sql = decide(Ename,oldpo,newpo,newstatus);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}//决定输出
				System.out.println(sql);
				changeServe(sql);
				reset();
			}

			

		});
		buttonsure.setBounds(10, 36, 93, 72);
		panel.add(buttonsure);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 120, 168, 67);
		add(panel_1);
		
		JLabel label = new JLabel("\u65B0\u804C\u4F4D");
		label.setBounds(10, 10, 52, 15);
		panel_1.add(label);
		
		newPosition = new JTextField();
		newPosition.setText((String) null);
		newPosition.setColumns(10);
		newPosition.setBounds(10, 31, 59, 21);
		panel_1.add(newPosition);
		
		newStatus = new JTextField();
		newStatus.setToolTipText("'\u5728\u804C','\u79BB\u804C','\u8BF7\u5047','\u57F9\u8BAD','\u672A\u5F55'");
		newStatus.setBounds(92, 31, 66, 21);
		panel_1.add(newStatus);
		newStatus.setColumns(10);
		
		JLabel label_1 = new JLabel("\u72B6\u6001");
		label_1.setBounds(90, 10, 54, 15);
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 42, 168, 67);
		add(panel_2);
		
		JLabel label_3 = new JLabel("\u5458\u5DE5\u59D3\u540D");
		label_3.setBounds(5, 8, 66, 15);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("\u539F\u804C\u4F4D");
		label_4.setBounds(89, 8, 72, 15);
		panel_2.add(label_4);
		
		name = new JTextField();
		name.setText((String) null);
		name.setColumns(10);
		name.setBounds(5, 31, 66, 21);
		panel_2.add(name);
		
		oldPosition = new JTextField();
		oldPosition.setText((String) null);
		oldPosition.setColumns(10);
		oldPosition.setBounds(89, 31, 66, 21);
		panel_2.add(oldPosition);
		
		JLabel label_6 = new JLabel("\u65E7");
		label_6.setForeground(Color.RED);
		label_6.setBounds(188, 42, 30, 21);
		add(label_6);
		
		JLabel label_7 = new JLabel("\u65B0");
		label_7.setForeground(Color.RED);
		label_7.setBounds(188, 120, 30, 21);
		add(label_7);
		
		JLabel label_8 = new JLabel("\u4EBA\u5458\u804C\u4F4D\u4FEE\u6539");
		label_8.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_8.setBounds(68, 10, 132, 22);
		add(label_8);
		reset();

	}
	
	private void reset() {
		name.setText(null);
		oldPosition.setText(null);
		newPosition.setText(null);
		newStatus.setText(null);
	}
	//改变函数
	private void changeServe(String sql) {
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("修改成功！");
			JOptionPane.showMessageDialog(null, "修改个人成功！");
		}else{
			JOptionPane.showMessageDialog(null, "修改失败！\n请输入有效信息！");
		}
		sm.closeDB();
	}
	//输出改变SQL语句
	private String decide(String ename, String oldpo, String newpo,String newStatus) throws SQLException {
		String[] str=getnum(ename,oldpo,newpo,newStatus);
		for (String string : str) {
			System.out.println(string);
		}
		String sql1="update Serve set ";
		String sql1x="update Serve set ";
		String sql2=" where ";
		String sql2x=" where ";
		if(!ename.isEmpty()){
			sql2=sql2+"Eno='"+str[0]+"'";
			System.out.println(sql2);
		}
		
		if(!oldpo.isEmpty()){
			if(!sql2.equals(sql2x)){
				sql2=sql2+" and ";
			}
			sql2=sql2+"Pno="+str[1]+"";
			System.out.println(sql2);
		}
		//新的
		if(!newpo.isEmpty()){
			sql1=sql1+"Pno='"+str[2]+"'";
			System.out.println(sql1);
		}
		String sql=sql1+sql2+";";
		return sql;
	}
	//查询操作
	private String[] getnum(String ename, String oldpo, String newpo, String newStatus) throws SQLException {
		String sql1="select * from Employee where Ename='"+ename+"';";
		String sql2="select * from Position where Pname='"+oldpo+"';";
		String sql3="select * from Position where Pname='"+newpo+"';";
		
		
		String Eno=null;
		String Pno_o=null;
		String Pno_n=null;
		sm = SQLManager.createInstance();
		sm.connectDB();
		rs=sm.executeQuery(sql1);
		while(rs.next()){
			Eno=rs.getString("Eno");
		}
		rs=sm.executeQuery(sql2);
		while(rs.next()){
			Pno_o=rs.getString("Pno");
		}
		rs=sm.executeQuery(sql3);
		while(rs.next()){
			Pno_n=rs.getString("Pno");
		}
		//更新状态
		int count3=-1;
		if(!newStatus.isEmpty()){
			String sql6="update Employee set Status='"+newStatus+"' where Ename='"+ename+"';";
			System.out.println(sql6);
			count3=sm.executeUpdate(sql6);
				}
		
		
		
		String[] a={Eno,Pno_o,Pno_n};
		
		int count6=-1;
		if(newpo.equals("部门主任")){
			String Dno=null;
			String sql5="select * from Attend where Eno="+Eno+";";
			rs=sm.executeQuery(sql5);
			while(rs.next()){
				Dno=rs.getString("Dno");
			}
			String sql4="update Department set DirectorNo="+Eno+"where Dno="+Dno+";";
			count6=sm.executeUpdate(sql4);
			
		}
		if(count6==1&&count3==1){
			System.out.println("部门主任更新成功！");
		}else{
			System.out.println("部门主任更新失败");
		}
		sm.closeDB();
		return a;
	}
}
