package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DepChange extends JPanel {
	private JTextField olddname;
	private JTextField olddirector;
	private JTextField newdname;
	private JTextField newdirector;
	private JButton btnsure;
	private JPanel panel_1;
	private SQLManager sm;

	/**
	 * Create the panel.
	 */
	public DepChange() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 42, 170, 67);
		add(panel);
		
		JLabel label = new JLabel(" \u90E8\u95E8\u540D\u79F0");
		label.setBounds(5, 8, 66, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u90E8\u957F\u53F7");
		label_1.setBounds(89, 8, 72, 15);
		panel.add(label_1);
		
		olddname = new JTextField();
		olddname.setText((String) null);
		olddname.setColumns(10);
		olddname.setBounds(5, 31, 66, 21);
		panel.add(olddname);
		
		olddirector = new JTextField();
		olddirector.setText((String) null);
		olddirector.setColumns(10);
		olddirector.setBounds(89, 31, 66, 21);
		panel.add(olddirector);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 120, 170, 67);
		add(panel_1);
		
		JLabel label_3 = new JLabel(" \u90E8\u95E8\u540D\u79F0");
		label_3.setBounds(5, 8, 74, 15);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("\u90E8\u957F\u53F7");
		label_4.setBounds(89, 8, 72, 15);
		panel_1.add(label_4);
		
		newdname = new JTextField();
		newdname.setText((String) null);
		newdname.setColumns(10);
		newdname.setBounds(5, 31, 66, 21);
		panel_1.add(newdname);
		
		newdirector = new JTextField();
		newdirector.setText((String) null);
		newdirector.setColumns(10);
		newdirector.setBounds(89, 31, 66, 21);
		panel_1.add(newdirector);
		
		JLabel label_6 = new JLabel("\u90E8\u95E8\u4FEE\u6539");
		label_6.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_6.setBounds(60, 10, 85, 22);
		add(label_6);
		
		btnsure = new JButton("\u786E\u8BA4");
		btnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql=null;
				String oldName=olddname.getText();
				String oldDirector=olddirector.getText();
				
				String newName=newdname.getText();
				String newDirector=newdirector.getText();
			
				try {
					sql=decide(oldName,oldDirector,newName,newDirector);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				System.out.println(sql);
				changeDepartment(sql);
				reset();
			}
		});
		btnsure.setBounds(36, 197, 109, 31);
		add(btnsure);
		
		JLabel label_7 = new JLabel("\u65B0");
		label_7.setForeground(Color.RED);
		label_7.setBounds(190, 131, 30, 21);
		add(label_7);
		
		JLabel label_8 = new JLabel("\u65E7");
		label_8.setForeground(Color.RED);
		label_8.setBounds(190, 53, 30, 21);
		add(label_8);
		reset();
	}
	private void changeDepartment(String sql) {
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("修改成功！");
			JOptionPane.showMessageDialog(null, "修改部门成功！");
		}else{
			JOptionPane.showMessageDialog(null, "修改部门失败！\n请输入有效信息！");
		}
		sm.closeDB();
	}
	private void reset() {
		olddname.setText(null);
		olddirector.setText(null);
		
		newdname.setText(null);
		newdirector.setText(null);
	}
	private String decide(String oldName, String oldDirector, String newName, String newDirector) throws SQLException {
		String sql1="update Department set ";
		String sql1x="update Department set ";
		String sql2=" where ";
		String sql2x=" where ";
		
		//查询
		String enoold=null;
		String enonew=null;
		String sql3=null;
		ResultSet rs=null;
		sm=SQLManager.createInstance();
		sm.connectDB();
		
		
		if(!oldName.isEmpty()){
			sql2=sql2+"Dname='"+oldName+"'";
			System.out.println(sql2);
		}
		
		if(!oldDirector.isEmpty()){
			sql3="select * from Employee where Ename='"+oldDirector+"';";
			System.out.println(sql3);
			rs = sm.executeQuery(sql3);
			while(rs.next()){
				enoold=rs.getString("Eno");
			}
			
			if(!sql2.equals(sql2x)){
				sql2=sql2+" and ";
			}
			sql2=sql2+"DirectorNo="+enoold+"";
			System.out.println(sql2);
		}
		//新的
		if(!newName.isEmpty()){
			sql1=sql1+"Dname='"+newName+"'";
			System.out.println(sql1);
		}
		if(!newDirector.isEmpty()){
			sql3="select * from Employee where Ename='"+newDirector+"';";
			System.out.println(sql3);
			rs = sm.executeQuery(sql3);
			while(rs.next()){
				enonew=rs.getString("Eno");
			}
			if(!sql1.equals(sql1x)){
				sql1=sql1+",";
			}
			sql1=sql1+"DirectorNo="+enonew+"";
			System.out.println(sql1);
		}
		sm.closeDB();
		String sql=sql1+sql2+";";
		return sql;
	}
}
