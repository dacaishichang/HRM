package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInPanel extends JPanel {
	static LogInPanel lgp;
	public JTextField account;
	public JPasswordField password;
	public JButton btn_register;
	public JButton btn_login;
	public JButton btn_cancel;
	private JLabel show_sth;
	public static String acc;
	public static String pas;
	public static int Eno;
	private JLabel lblNewLabel;
	
	/**
	 * Create the panel.
	 */
	public LogInPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u767B\u5F55\u4EBA\u529B\u8D44\u6E90\u7BA1\u7406\u7CFB\u7EDF");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(408, 53, 195, 33);
		add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7");
		label_1.setBounds(381, 111, 40, 15);
		add(label_1);
		
		account = new JTextField();
		account.setColumns(10);
		account.setBounds(427, 111, 182, 21);
		add(account);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setBounds(381, 155, 40, 15);
		add(label_2);
		
		password = new JPasswordField();
		password.setBounds(427, 155, 182, 21);
		add(password);
		
		btn_login = new JButton("\u767B\u5F55");
		btn_login.setBounds(391, 186, 67, 23);
		add(btn_login);
		
		btn_register = new JButton("\u6CE8\u518C");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_register.setBounds(469, 186, 67, 23);
		add(btn_register);
		
		btn_cancel = new JButton("\u53D6\u6D88");
		btn_cancel.setBounds(546, 186, 67, 23);
		add(btn_cancel);
		
		show_sth = new JLabel("");
		show_sth.setForeground(Color.RED);
		show_sth.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		show_sth.setBounds(391, 236, 222, 33);
		add(show_sth);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\JAVA_FILE\\HRM_BUILDER\\image\\pic7.png"));
		lblNewLabel.setBounds(10, 10, 370, 284);
		add(lblNewLabel);

	}
	public int login() throws SQLException{
		Integer Uno=0;
		acc = account.getText();
		pas=password.getText();
		SQLManager sm=new SQLManager();
		int plevel=0;
		sm.connectDB();
		Uno=sm.executeLogin(acc,pas);
		if(Uno==0){
			this.show_sth.setText("账号或者密码输入不正确！");
			
		}else{
			System.out.println(Uno);
			ResultSet a=sm.executeView("*", "Employee_look", "Uno", Uno.toString());
			while(a.next()){
				plevel=a.getInt("Plevel");
				Eno=a.getInt("Eno");
				System.out.println("这个是Eno："+Integer.toString(Eno));
			}
		}
		reset();
		return plevel;
	}
	public void reset(){
//		account.setText(null);
		password.setText(null);
		show_sth.setText(null);
	}
	public static LogInPanel createInstance(){
		if(lgp==null)
		{
			lgp=new LogInPanel();
		}
		return lgp;
	}
}
