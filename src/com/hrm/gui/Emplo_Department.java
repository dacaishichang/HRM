package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Emplo_Department extends JPanel {
	public ResultSet rs;
	public SQLManager sm;
	private JLabel name;
	private JLabel eno;
	private JLabel acc;
	private JTextField sex;
	private JTextField age;
	private JTextField pho;
	private JLabel status;
	private JLabel position;
	private JLabel plevel;
	private JLabel salary;
	private JLabel depart;
	private JLabel dname;
	private JLabel dphone;
	private JPanel panel_4;
	

	/**
	 * Create the panel.
	 */
	public Emplo_Department() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 53, 97, 191);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u5458\u5DE5\u59D3\u540D");
		label.setBounds(22, 10, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u53F7");
		label_1.setBounds(22, 35, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u8D26\u53F7");
		label_2.setBounds(22, 60, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u6027\u522B");
		label_3.setBounds(22, 85, 54, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u5E74\u9F84");
		label_4.setBounds(22, 110, 54, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u7535\u8BDD");
		label_5.setBounds(22, 135, 54, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u5DE5\u4F5C\u60C5\u51B5");
		label_6.setBounds(22, 160, 54, 15);
		panel.add(label_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(264, 53, 97, 191);
		add(panel_1);
		
		JLabel label_8 = new JLabel("\u804C\u4F4D");
		label_8.setBounds(22, 10, 54, 15);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("\u804C\u4F4D\u7B49\u7EA7");
		label_9.setBounds(22, 35, 54, 15);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel("\u5DE5\u8D44");
		label_10.setBounds(22, 60, 54, 15);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("\u6240\u8FF0\u90E8\u95E8");
		label_11.setBounds(22, 85, 54, 15);
		panel_1.add(label_11);
		
		JLabel label_12 = new JLabel("\u90E8\u957F");
		label_12.setBounds(22, 110, 54, 15);
		panel_1.add(label_12);
		
		JLabel label_13 = new JLabel("\u90E8\u957F\u7535\u8BDD");
		label_13.setBounds(22, 135, 54, 15);
		panel_1.add(label_13);
		
		JButton ref = new JButton("\u5237\u65B0");
		ref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_info(LogInPanel.Eno);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		ref.setBounds(10, 160, 80, 23);
		panel_1.add(ref);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(115, 53, 139, 191);
		add(panel_2);
		
		name = new JLabel("");
		name.setBounds(22, 10, 107, 15);
		panel_2.add(name);
		
		eno = new JLabel("");
		eno.setBounds(22, 35, 107, 15);
		panel_2.add(eno);
		
		acc = new JLabel("");
		acc.setBounds(22, 60, 107, 15);
		panel_2.add(acc);
		
		sex = new JTextField("");
		sex.setBounds(22, 85, 107, 15);
		panel_2.add(sex);
		
		age = new JTextField("");
		age.setBounds(22, 110, 107, 15);
		panel_2.add(age);
		
		pho = new JTextField("");
		pho.setBounds(22, 135, 107, 15);
		panel_2.add(pho);
		
		status = new JLabel("");
		status.setBounds(22, 160, 107, 15);
		panel_2.add(status);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(371, 53, 145, 191);
		add(panel_3);
		
		position = new JLabel("");
		position.setBounds(22, 10, 113, 15);
		panel_3.add(position);
		
		plevel = new JLabel("");
		plevel.setBounds(22, 35, 113, 15);
		panel_3.add(plevel);
		
		salary = new JLabel("");
		salary.setBounds(22, 60, 113, 15);
		panel_3.add(salary);
		
		depart = new JLabel("");
		depart.setBounds(22, 85, 113, 15);
		panel_3.add(depart);
		
		dname = new JLabel("");
		dname.setBounds(22, 110, 113, 15);
		panel_3.add(dname);
		
		dphone = new JLabel("");
		dphone.setBounds(22, 135, 113, 15);
		panel_3.add(dphone);
		
		JButton btn_change = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btn_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sex=sex.getText();
				String Age=age.getText();
				String Phone=pho.getText();
				try {
					changeInfo(Sex,Age,Phone);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}

		});
		btn_change.setBounds(32, 160, 93, 23);
		panel_3.add(btn_change);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, null, null, null));
		panel_4.setBounds(10, 10, 506, 33);
		add(panel_4);
		
		JLabel lblNewLabel = new JLabel("\u804C\u5DE5\u8BE6\u7EC6\u4FE1\u606F");
		panel_4.add(lblNewLabel);

	}
	private void changeInfo(String sex, String age, String phone) throws SQLException {
		String sql="update Employee set Sex='"+sex+"',Age="+age+",Phone="+phone+"where Eno="+LogInPanel.Eno+";";
		System.out.println(sql);
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=sm.executeUpdate(sql);
		if(count==1){
			System.out.println("修改成功");
			JOptionPane.showMessageDialog(null, "修改成功！");
		}else{
			JOptionPane.showMessageDialog(null, "修改失败！\n请输入有效信息！");
		}
		sm.closeDB();
		show_info(LogInPanel.Eno);
	}

	protected void show_info(int Eno) throws SQLException {
		String a = null,b = null,c = null,d = null,e = null,
				f = null,g = null,h = null,i = null,j = null,
				k = null,l = null,m = null;
		sm=SQLManager.createInstance();
		sm.connectDB();
		System.out.println("这是Eno:"+Integer.toString(Eno));
		String sql="select * from Position_look where Eno="+Integer.toString(Eno)+";";
		System.out.println(sql);
		rs = sm.executeQuery(sql);
		
		while(rs.next()){
			a=rs.getString("Ename");
			b=rs.getString("Eno");
			c=rs.getString("Account");
			d=rs.getString("Sex");
			e=rs.getString("Age");
			f=rs.getString("Phone");
			g=rs.getString("Status");
			h=rs.getString("Pname");
			i=rs.getString("Plevel");
			j=rs.getString("Salary");
			k=rs.getString("Dname");
			l=rs.getString("Director");
			m=rs.getString("Phone");
			
		}
		sm.closeDB();
		
		System.out.println(a+b+c+d+e+f+g+h+i+j+k+l+m);
		
		name.setText(a);
		eno.setText(b);
		acc.setText(c);
		sex.setText(d);
		age.setText(e);
		pho.setText(f);
		status.setText(g);
		position.setText(h);
		plevel.setText(i);
		salary.setText(j);
		depart.setText(k);
		dname.setText(l);
		dphone.setText(m);
	}
}
