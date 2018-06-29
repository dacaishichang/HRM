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

public class RegisterPanel extends JPanel {
	public JTextField account_reg;
	public JPasswordField pass_reg;
	public JTextField name;
	public JButton btn_reg_sure;
	public JButton btn_reg_cancel;
	private JLabel show_sth;
	private JTextField phone;
	public ResultSet rs;
	private SQLManager q;

	/**
	 * Create the panel.
	 */
	public RegisterPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("\u4EBA\u529B\u8D44\u6E90\u7BA1\u7406\u7CFB\u7EDF\u6CE8\u518C");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(387, 31, 161, 30);
		add(label);
		
		JLabel label_1 = new JLabel("\u8D26  \u53F7");
		label_1.setBounds(329, 74, 54, 15);
		add(label_1);
		
		account_reg = new JTextField();
		account_reg.setColumns(10);
		account_reg.setBounds(410, 71, 203, 21);
		add(account_reg);
		
		JLabel label_2 = new JLabel("\u5BC6  \u7801");
		label_2.setBounds(329, 105, 54, 15);
		add(label_2);
		
		pass_reg = new JPasswordField();
		pass_reg.setBounds(410, 102, 203, 21);
		add(pass_reg);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(410, 133, 203, 21);
		add(name);
		
		JLabel label_3 = new JLabel("\u59D3  \u540D");
		label_3.setBounds(329, 136, 54, 15);
		add(label_3);
		
		btn_reg_sure = new JButton("\u6CE8\u518C");
		btn_reg_sure.setBounds(340, 224, 93, 23);
		add(btn_reg_sure);
		
		btn_reg_cancel = new JButton("\u53D6\u6D88");
		btn_reg_cancel.setBounds(481, 224, 93, 23);
		add(btn_reg_cancel);
		
		show_sth = new JLabel("");
		show_sth.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		show_sth.setForeground(Color.RED);
		show_sth.setBounds(66, 123, 188, 30);
		add(show_sth);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(410, 164, 203, 21);
		add(phone);
		
		JLabel label_4 = new JLabel("\u7535  \u8BDD");
		label_4.setBounds(329, 167, 54, 15);
		add(label_4);
		
	}
	public void reset(){
		account_reg.setText(null);
		pass_reg.setText(null);
		name.setText(null);
		show_sth.setText(null);
		phone.setText(null);
		
	}
	public int register() throws SQLException{
		int count=0;
		int count2=0;
		int count3=0;
		String acc=account_reg.getText();
		String pas=pass_reg.getText();
		String pho=phone.getText();
		String Rname=name.getText();
		q = new SQLManager();
		q.connectDB();
		String sql1="insert into Users(Account,Password) values("+"'"+acc+"','"+pas+"');";
		String sql2="insert into Employee(Ename,Phone) values("+"'"+Rname+"','"+pho+"');";
		
		System.out.println(sql1+"\n"+sql2);
		count=q.executeUpdate(sql1);
		count2=q.executeUpdate(sql2);
		count3=getRelation(Rname,acc);
		if(count>=1&&count2>=1&&count3>=3){
			System.out.println("注册user成功！");
		}else{
			System.out.println("注册user失败！");
			show_sth.setText("账号已存在或者其他原因");
		}
		q.closeDB();
		return count3;
	}
		//对Have关系的更新
		public int getRelation(String name,String Account) throws SQLException{
			int count1=0;
			int count2=0;
			Integer eno=0;
			Integer uno=0;
			String sql1="select * from Employee where Ename='"+name+"';";
			System.out.println(sql1);
			rs=q.executeQuery(sql1);
			
			while(rs.next()){
				eno=rs.getInt("Eno");
			}
			String sql2="select * from Users where Account='"+Account+"';";
			System.out.println(sql2);
			rs=q.executeQuery(sql2);
			while(rs.next()){
				uno=rs.getInt("Uno");
			}
			String sql3="insert into Have values("+uno.toString()+","+eno.toString()+");";
			System.out.println(sql3);
			count1=q.executeUpdate(sql3);
			
			String sql4="insert into Serve values(100,"+eno.toString()+");";
			count2=q.executeUpdate(sql4);
			
			String sql5="insert into Attend(Eno) values("+eno+");";
			System.out.println(sql5);
			int count3 = q.executeUpdate(sql5);
			System.out.println(sql5);
			
			return count1+count2+count3;
		}
}
