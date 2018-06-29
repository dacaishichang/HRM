package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DepDel extends JPanel {
	private JTextField Did;
	private JTextField Dname;
	private SQLManager sm;

	/**
	 * Create the panel.
	 */
	public DepDel() {
		setLayout(null);
		
		Did = new JTextField();
		Did.setToolTipText("");
		Did.setText((String) null);
		Did.setColumns(10);
		Did.setBounds(144, 62, 88, 21);
		add(Did);
		
		Dname = new JTextField();
		Dname.setToolTipText("\u5B58\u5728\u7684\u90E8\u95E8");
		Dname.setText((String) null);
		Dname.setColumns(10);
		Dname.setBounds(10, 62, 88, 21);
		add(Dname);
		
		JLabel name = new JLabel("\u90E8\u95E8\u540D\u79F0");
		name.setBounds(10, 37, 66, 15);
		add(name);
		
		JLabel label_1 = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		label_1.setBounds(143, 37, 89, 15);
		add(label_1);
		
		JLabel label_3 = new JLabel("\u90E8\u95E8\u51CF\u5C11(\u5FC5\u586B)");
		label_3.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_3.setBounds(76, 10, 120, 21);
		add(label_3);
		
		JButton btn_sure = new JButton("\u786E\u8BA4");
		btn_sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=Dname.getText();
				String did=Did.getText();
				System.out.println(name+did);
				delDepartment(name,did);
				
				reset();
			}

			
		});
		btn_sure.setBounds(62, 125, 109, 31);
		add(btn_sure);

	}
	private void delDepartment(String name, String did) {
		sm = SQLManager.createInstance();
		sm.connectDB();
		//and Attend
		int count2=0;
		String sql="delete from Department where ";
		String sqlx="delete from Department where ";
		if(!name.isEmpty()){
			sql=sql+"Dname='"+name+"'";
		}
		if(!did.isEmpty()){
			if(!sql.equals(sqlx)){
				sql=sql+" and ";
			}
			sql=sql+"Dno="+did;
			//删除Attend
			String sql3="update Attend set Dno=100 where Dno="+did+";";
			System.out.println(sql3);
			count2=sm.executeUpdate(sql3);
		}
		sql=sql+";";
		System.out.println(sql);
		int count=0;
		count=sm.executeUpdate(sql);

		if(count==1&&count2==1){
			
			System.out.println("删除成功！");
			JOptionPane.showMessageDialog(null, "部门删除成功！");
		}else{
			
			JOptionPane.showMessageDialog(null, "部门删除失败！");
		}
		sm.closeDB();
	}
	private void reset() {
		Dname.setText(null);
		Did.setText(null);
	}
}
