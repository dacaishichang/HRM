package com.hrm.gui;

import javax.swing.JPanel;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RecDel extends JPanel {
	private JTextField Dname;
	private JTextField Pname;
	private JTextField number;
	private SQLManager sm;
	private ResultSet rs;

	/**
	 * Create the panel.
	 */
	public RecDel() {
		setLayout(null);
		
		JButton btnSure = new JButton("\u786E\u8BA4");
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dname=Dname.getText();
				String pname=Pname.getText();
				String num=number.getText();

				try {
					delRecruit(dname,pname);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				reset();
			}

			
		});
		btnSure.setBounds(62, 125, 109, 31);
		add(btnSure);
		
		Dname = new JTextField();
		Dname.setToolTipText("\u5B58\u5728\u7684\u90E8\u95E8");
		Dname.setText((String) null);
		Dname.setColumns(10);
		Dname.setBounds(10, 62, 66, 21);
		add(Dname);
		
		Pname = new JTextField();
		Pname.setToolTipText("");
		Pname.setText((String) null);
		Pname.setColumns(10);
		Pname.setBounds(86, 62, 66, 21);
		add(Pname);
		
		JLabel label = new JLabel("\u804C\u4F4D");
		label.setBounds(86, 37, 66, 15);
		add(label);
		
		JLabel label_1 = new JLabel("\u62DB\u8058\u90E8\u95E8");
		label_1.setBounds(10, 37, 66, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u62DB\u8058\u51CF\u5C11(\u5FC5\u586B)");
		label_2.setFont(new Font("·½ÕýÒ¦Ìå", Font.PLAIN, 16));
		label_2.setBounds(86, 10, 118, 21);
		add(label_2);
		
		number = new JTextField();
		number.setToolTipText("");
		number.setText((String) null);
		number.setColumns(10);
		number.setBounds(162, 62, 66, 21);
		add(number);
		
		JLabel label_3 = new JLabel("\u62DB\u8058\u6570\u91CF");
		label_3.setBounds(162, 37, 66, 15);
		add(label_3);
		
		reset();
	}
	private void reset() {
		Dname.setText(null);
		Pname.setText(null);
		number.setText(null);
	}
	private void delRecruit(String dname, String pname) throws SQLException {
		sm = SQLManager.createInstance();
		sm.connectDB();
		String Dno=null;
		String Pno=null;
		String sql1="select * from Position where Pname='"+pname+"';";
		System.out.println(sql1);
		rs = sm.executeQuery(sql1);
		while(rs.next()){
			Pno=rs.getString("Pno");
		}
		
		String sql2="select * from Department where Dname='"+dname+"';";
		rs = sm.executeQuery(sql2);
		while(rs.next()){
			Dno=rs.getString("Dno");
		}
		
		String sql="delete from Recruit where Dno="+Dno+" and Pno="+Pno+";";
		System.out.println(sql);
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("É¾³ý³É¹¦£¡");
			JOptionPane.showMessageDialog(null, "ÕÐÆ¸É¾³ý³É¹¦£¡");
		}else{
			JOptionPane.showMessageDialog(null, "ÕÐÆ¸É¾³ýÊ§°Ü£¡");
		}
		sm.closeDB();
	}

}
