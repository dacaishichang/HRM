package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class PersonInfo extends JPanel {

	private  JLabel eno_label;
	private  JLabel uno_label;
	private  JLabel name_label;
	private  JLabel position_label;
	private  JLabel plevel_label;
	private  JLabel status_label;
	public  SQLManager sm;
	private  ResultSet rs;
	
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public PersonInfo() {
		setLayout(null);
		
		JLabel label = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		label.setBounds(300, 10, 81, 24);
		add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.BLACK, null, null));
		panel.setBounds(300, 44, 185, 177);
		add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u53F7");
		label_1.setBounds(24, 24, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u53F7");
		label_2.setBounds(24, 49, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u59D3\u540D");
		label_3.setBounds(24, 73, 54, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u804C\u4F4D");
		label_4.setBounds(24, 98, 54, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u804C\u52A1\u7B49\u7EA7");
		label_5.setBounds(24, 123, 54, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u72B6\u6001");
		label_6.setBounds(24, 148, 54, 15);
		panel.add(label_6);
		
		eno_label = new JLabel("");
		eno_label.setBounds(88, 24, 91, 15);
		panel.add(eno_label);
		
		uno_label = new JLabel("");
		uno_label.setBounds(88, 49, 91, 15);
		panel.add(uno_label);
		
		name_label = new JLabel("");
		name_label.setBounds(88, 73, 91, 15);
		panel.add(name_label);
		
		position_label = new JLabel("");
		position_label.setBounds(88, 98, 91, 15);
		panel.add(position_label);
		
		plevel_label = new JLabel("");
		plevel_label.setBounds(88, 123, 91, 15);
		panel.add(plevel_label);
		
		status_label = new JLabel("");
		status_label.setBounds(88, 148, 91, 15);
		panel.add(status_label);
		
		JButton btn_refresh = new JButton("\u5237\u65B0");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_info(LogInPanel.Eno);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btn_refresh.setBounds(392, 13, 93, 23);
		add(btn_refresh);
		
		JLabel label_7 = new JLabel("\u6B22\u8FCE\u60A8\uFF01");
		label_7.setBackground(Color.CYAN);
		label_7.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		label_7.setBounds(131, 14, 81, 17);
		add(label_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\JAVA_FILE\\HRM_BUILDER\\image\\pic3.png"));
		lblNewLabel.setBounds(82, 37, 147, 178);
		add(lblNewLabel);
		System.out.println(LogInPanel.Eno);
//		show_info(LogInPanel.Eno);
		
	}
	public void show_info(int eno) throws SQLException{
		String a = null,b = null,c = null,d = null,e = null,f = null;
		sm=SQLManager.createInstance();
		sm.connectDB();
		System.out.println("’‚ «Eno:"+Integer.toString(eno));
		String sql="select * from Employee_look where Eno="+Integer.toString(eno)+";";
		System.out.println(sql);
		rs = sm.executeQuery(sql);
		
		while(rs.next()){
			a=rs.getString("Eno");
			b=rs.getString("Uno");
			c=rs.getString("Ename");
			d=rs.getString("Pname");
			e=rs.getString("Plevel");
			f=rs.getString("Status");
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			System.out.println(e);
			System.out.println(f);
			
		}
//		System.out.println(a+b+c+d+e+f);
		sm.closeDB();
		eno_label.setText(a);
		uno_label.setText(b);
		name_label.setText(c);
		position_label.setText(d);
		plevel_label.setText(e);
		status_label.setText(f);
	}
}
