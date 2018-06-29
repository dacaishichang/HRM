package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PosAdd extends JPanel {
	private JTextField name;
	private JTextField plevel;
	private JTextField salary;
	private JLabel shiw_sth;
	public ResultSet rs;
	public SQLManager sm;

	/**
	 * Create the panel.
	 */
	public PosAdd() {
		setBorder(null);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u804C\u4F4D\u589E\u52A0");
		lblNewLabel.setFont(new Font("方正姚体", Font.PLAIN, 16));
		lblNewLabel.setBounds(73, 10, 85, 21);
		add(lblNewLabel);
		
		JButton btnAdd = new JButton("\u786E\u8BA4");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Pname=name.getText();
				String Plevel=plevel.getText();
				String Salary=salary.getText();
				String sql="insert into Position(Pname,Plevel,Salary) values('"+Pname+"',"+Plevel+","+Salary+");";
				System.out.println(sql);
				addPosition(sql);
				reset();
			}
		});
		btnAdd.setBounds(73, 123, 109, 31);
		add(btnAdd);
		
		name = new JTextField();
		name.setToolTipText("\u9009\u62E9\u4F60\u559C\u6B22\u7684\u804C\u4F4D");
		name.setBounds(20, 60, 66, 21);
		add(name);
		name.setColumns(10);
		
		plevel = new JTextField();
		plevel.setToolTipText("1-5\u7684\u6570\u5B57");
		plevel.setBounds(96, 60, 66, 21);
		add(plevel);
		plevel.setColumns(10);
		
		salary = new JTextField();
		salary.setToolTipText("\u6574\u6570");
		salary.setColumns(10);
		salary.setBounds(172, 60, 66, 21);
		add(salary);
		
		JLabel label = new JLabel(" \u804C\u4F4D\u540D\u79F0");
		label.setBounds(20, 35, 66, 15);
		add(label);
		
		JLabel label_1 = new JLabel(" \u804C\u4F4D\u7B49\u7EA7");
		label_1.setBounds(95, 35, 67, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel(" \u804C\u4F4D\u5DE5\u8D44");
		label_2.setBounds(173, 35, 65, 15);
		add(label_2);
		
		shiw_sth = new JLabel("");
		shiw_sth.setBounds(30, 91, 218, 21);
		add(shiw_sth);
		
		
		
		
		reset();

	}
	public void addPosition(String sql){
		sm=SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("添加成功！");
			JOptionPane.showMessageDialog(null, "职位添加成功！");
		}else{
			JOptionPane.showMessageDialog(null, "职位添加失败！");
		}
		sm.closeDB();
	}
	public void reset(){
		name.setText(null);
		plevel.setText(null);
		salary.setText(null);
	}
}
