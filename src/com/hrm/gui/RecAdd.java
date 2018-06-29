package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RecAdd extends JPanel {
	private JTextField Pname;
	private JTextField Dname;
	private JTextField numbertext;
	private SQLManager sm;
	private ResultSet rs;

	/**
	 * Create the panel.
	 */
	public RecAdd() {
		setLayout(null);
		
		JButton btnSure = new JButton("\u786E\u8BA4");
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dname=Dname.getText();
				String pname=Pname.getText();
				String number=numbertext.getText();

				try {
					addRecruit(dname,pname,number);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				reset();
			}
	
		});
		btnSure.setBounds(63, 123, 109, 31);
		add(btnSure);
		
		Pname = new JTextField();
		Pname.setToolTipText("\u9009\u62E9\u90E8\u957F");
		Pname.setText((String) null);
		Pname.setColumns(10);
		Pname.setBounds(86, 60, 66, 21);
		add(Pname);
		
		Dname = new JTextField();
		Dname.setToolTipText("\u90E8\u95E8\u6DFB\u52A0");
		Dname.setText((String) null);
		Dname.setColumns(10);
		Dname.setBounds(10, 60, 66, 21);
		add(Dname);
		
		JLabel label = new JLabel("\u90E8\u95E8\u540D\u79F0");
		label.setBounds(10, 35, 66, 15);
		add(label);
		
		JLabel label_1 = new JLabel("\u804C\u4F4D\u540D");
		label_1.setBounds(91, 35, 57, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u62DB\u8058\u589E\u52A0(\u5FC5\u586B)");
		label_2.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_2.setBounds(63, 10, 109, 21);
		add(label_2);
		
		JLabel label_3 = new JLabel("\u6570\u91CF");
		label_3.setBounds(170, 35, 57, 15);
		add(label_3);
		
		numbertext = new JTextField();
		numbertext.setToolTipText("\u9009\u62E9\u90E8\u957F");
		numbertext.setText((String) null);
		numbertext.setColumns(10);
		numbertext.setBounds(165, 60, 66, 21);
		add(numbertext);

	}
	private void reset() {
		Dname.setText(null);
		Pname.setText(null);
		numbertext.setText(null);
	}
	private void addRecruit(String dname, String pname, String number) throws SQLException {
		
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		//查询
		String Dno=null;
		String Pno=null;
		String sql1="select * from Department where Dname='"+dname+"';";
		System.out.println(sql1);
		rs = sm.executeQuery(sql1);
		while(rs.next()){
			Dno=rs.getString("Dno");
		}
		String sql2="select * from Position where Pname='"+pname+"';";
		System.out.println(sql2);
		rs = sm.executeQuery(sql2);
		while(rs.next()){
			Pno=rs.getString("Pno");
		}
		String sql="insert into Recruit values("+Dno+","+Pno+","+number+");";
		System.out.println(sql);
		//更新
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("添加成功！");
			JOptionPane.showMessageDialog(null, "招聘添加成功！");
		}else{
			JOptionPane.showMessageDialog(null, "招聘添加失败！");
		}
		sm.closeDB();
	}
	

}
