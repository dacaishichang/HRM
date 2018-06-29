package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PosChange extends JPanel {
	private JTextField name_old;
	private JTextField plevel_old;
	private JTextField salary_old;
	private JTextField name_new;
	private JTextField plevel_new;
	private JTextField salary_new;
	private JButton btn_change;
	private SQLManager sm;

	/**
	 * Create the panel.
	 */
	public PosChange() {
		setLayout(null);
		
		JLabel label = new JLabel("\u804C\u4F4D\u4FEE\u6539");
		label.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label.setBounds(101, 8, 85, 22);
		add(label);
		
		btn_change = new JButton("\u786E\u8BA4");
		btn_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql;
				String old=name_old.getText();
				String olevel=plevel_old.getText();
				String oldsalary=salary_old.getText();
				String newname=name_new.getText();
				String newlevel=plevel_new.getText();
				String newsalary=salary_new.getText();
				sql=decide(old,olevel,oldsalary,newname,newlevel,newsalary);
				System.out.println(sql);
				changePosition(sql);
				reset();
			}

			

			
		});
		btn_change.setBounds(65, 195, 109, 31);
		add(btn_change);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 40, 252, 67);
		add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel(" \u804C\u4F4D\u540D\u79F0");
		label_1.setBounds(5, 8, 66, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel(" \u804C\u4F4D\u7B49\u7EA7");
		label_2.setBounds(89, 8, 72, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel(" \u804C\u4F4D\u5DE5\u8D44");
		label_3.setBounds(171, 8, 70, 15);
		panel.add(label_3);
		
		name_old = new JTextField();
		name_old.setBounds(5, 31, 66, 21);
		panel.add(name_old);
		name_old.setColumns(10);
		
		plevel_old = new JTextField();
		plevel_old.setBounds(89, 31, 66, 21);
		panel.add(plevel_old);
		plevel_old.setColumns(10);
		
		salary_old = new JTextField();
		salary_old.setBounds(175, 31, 66, 21);
		panel.add(salary_old);
		salary_old.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(15, 118, 252, 67);
		add(panel_1);
		
		JLabel label_4 = new JLabel(" \u804C\u4F4D\u540D\u79F0");
		label_4.setBounds(5, 8, 74, 15);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel(" \u804C\u4F4D\u7B49\u7EA7");
		label_5.setBounds(89, 8, 72, 15);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel(" \u804C\u4F4D\u5DE5\u8D44");
		label_6.setBounds(171, 8, 70, 15);
		panel_1.add(label_6);
		
		name_new = new JTextField();
		name_new.setColumns(10);
		name_new.setBounds(5, 31, 66, 21);
		panel_1.add(name_new);
		
		plevel_new = new JTextField();
		plevel_new.setColumns(10);
		plevel_new.setBounds(89, 31, 66, 21);
		panel_1.add(plevel_new);
		
		salary_new = new JTextField();
		salary_new.setColumns(10);
		salary_new.setBounds(175, 31, 66, 21);
		panel_1.add(salary_new);
		
		JLabel label_7 = new JLabel("\u65E7");
		label_7.setForeground(Color.RED);
		label_7.setBounds(277, 40, 30, 21);
		add(label_7);
		
		JLabel label_8 = new JLabel("\u65B0");
		label_8.setForeground(Color.RED);
		label_8.setBounds(277, 118, 30, 21);
		add(label_8);
		
		
		
		
		reset();

	}
	private void changePosition(String sql) {
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("修改成功！");
			JOptionPane.showMessageDialog(null, "修改职位成功！");
		}else{
			JOptionPane.showMessageDialog(null, "修改失败！\n请输入有效信息！");
		}
		sm.closeDB();
	}
	public void reset(){
		name_new.setText(null);
		name_old.setText(null);
		plevel_new.setText(null);
		plevel_old.setText(null);
		salary_new.setText(null);
		salary_old.setText(null);
	}
	private String decide(String oldname, String oldlevel, String oldsalary, String newname, String newlevel,
			String newsalary) {
		String sql1="update Position set ";
		String sql1x="update Position set ";
		String sql2=" where ";
		String sql2x=" where ";
		if(!oldname.isEmpty()){
			sql2=sql2+"Pname='"+oldname+"'";
			System.out.println(sql2);
		}
		if(!oldlevel.isEmpty()){
			if(!sql2.equals(sql2x)){
				sql2=sql2+" and ";
			}
			sql2=sql2+"Plevel="+oldlevel+"";
			System.out.println(sql2);
		}
		if(!oldsalary.isEmpty()){
			if(!sql2.equals(sql2x)){
				sql2=sql2+" and ";
			}
			sql2=sql2+"Salary="+oldsalary+"";
			System.out.println(sql2);
		}
		//新的
		if(!newname.isEmpty()){
			sql1=sql1+"Pname='"+newname+"'";
			System.out.println(sql1);
		}
		if(!newlevel.isEmpty()){
			if(!sql1.equals(sql1x)){
				sql1=sql1+",";
			}
			sql1=sql1+"Plevel="+newlevel+"";
			System.out.println(sql1);
		}
		if(!newsalary.isEmpty()){
			if(!sql1.equals(sql1x)){
				sql1=sql1+",";
			}
			sql1=sql1+"Salary="+newsalary+"";
			System.out.println(sql1);
		}
		
		String sql=sql1+sql2+";";
		return sql;
	}

}
