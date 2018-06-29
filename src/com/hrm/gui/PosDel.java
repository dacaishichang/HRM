package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PosDel extends JPanel {
	private JTextField name;
	private JTextField plevel;
	private JTextField salary;
	private JLabel show_sth;
	private SQLManager sm;

	/**
	 * Create the panel.
	 */
	public PosDel() {
		setLayout(null);
		
		name = new JTextField();
		name.setToolTipText("\u5B58\u5728\u7684\u804C\u4F4D");
		name.setColumns(10);
		name.setBounds(21, 75, 66, 21);
		add(name);
		
		plevel = new JTextField();
		plevel.setToolTipText("1-5");
		plevel.setColumns(10);
		plevel.setBounds(97, 75, 66, 21);
		add(plevel);
		
		salary = new JTextField();
		salary.setToolTipText("\u6574\u6570");
		salary.setColumns(10);
		salary.setBounds(173, 75, 66, 21);
		add(salary);
		
		JLabel label_1 = new JLabel(" \u804C\u4F4D\u5DE5\u8D44");
		label_1.setBounds(174, 50, 65, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel(" \u804C\u4F4D\u7B49\u7EA7");
		label_2.setBounds(96, 50, 67, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel(" \u804C\u4F4D\u540D\u79F0");
		label_3.setBounds(21, 50, 66, 15);
		add(label_3);
		
		JButton btn_del = new JButton("\u786E\u8BA4");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql;
				String Pname=name.getText();
				String Plevel=plevel.getText();
				String Salary=salary.getText();
				sql=decide(Pname,Plevel,Salary);
				if(sql.equals("delete from Position where ;")){
					JOptionPane.showMessageDialog(null, "未输入有效信息！");
				}else{
				System.out.println(sql);
				delPosition(sql);
					}
				reset();
			}

		});
		btn_del.setBounds(73, 138, 109, 31);
		add(btn_del);
		
		JLabel label_4 = new JLabel("\u804C\u4F4D\u51CF\u5C11");
		label_4.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_4.setBounds(97, 23, 66, 21);
		add(label_4);
		
		show_sth = new JLabel("");
		show_sth.setBounds(31, 106, 218, 22);
		add(show_sth);
		
		
		
		
		reset();

	}
	public void delPosition(String sql){
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("删除成功！");
			JOptionPane.showMessageDialog(null, "删除职位成功！");
		}else{
			JOptionPane.showMessageDialog(null, "职位删除失败！\n请输入有效信息！");
		}
		sm.closeDB();
	}
	public void reset(){
		name.setText(null);
		plevel.setText(null);
		salary.setText(null);
	}
	private String decide(String Pname,String Plevel,String Salary) {
		String sql="delete from Position where ";
		String sqlx="delete from Position where ";
		if(!Pname.isEmpty()){
			sql=sql+"Pname='"+Pname+"'";
			System.out.println(sql);
		}
		if(!Plevel.isEmpty()){
			if(!sql.equals(sqlx)){
				sql=sql+" and ";
			}
			sql=sql+"Plevel="+Plevel+"";
			System.out.println(sql);
		}
		if(!Salary.isEmpty()){
			if(!sql.equals(sqlx)){
				sql=sql+" and ";
			}
			sql=sql+"Salary="+Salary+"";
			System.out.println(sql);
		}
		sql=sql+";";
		return sql;
	}
}
