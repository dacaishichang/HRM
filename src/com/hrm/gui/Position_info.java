package com.hrm.gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Position_info extends JPanel {
	private JButton button;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	public ResultSet rs;
	public Vector vData;
	public Vector vName;
	public Vector vRow;
	private JTable table;
	private String[] nameLists;
	private JLabel label_1;
	private JComboBox comboBox;
	private JButton button_1;

	
	/**
	 * Create the panel.
	 */
	public Position_info() {
		setLayout(null);
		
		JLabel label = new JLabel("\u516C\u53F8\u804C\u4F4D\u4FE1\u606F");
		label.setBounds(315, 7, 108, 24);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		add(label);
		
		button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="select * from Position;";
					showPosition(sql);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		button.setBounds(433, 10, 78, 23);
		add(button);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
//		add(table);
		
		
		
		vData = new Vector();
		vName = new Vector();//列名
		vRow = new Vector();
		
		try {
			String sql="select * from Position;";
			showPosition(sql);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 501, 190);
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		label_1 = new JLabel("\u804C\u4F4D\u7B49\u7EA7");
		label_1.setBounds(10, 14, 54, 15);
		add(label_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(74, 11, 54, 21);
		add(comboBox);
		
		
		try {
			addComboBox();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		button_1 = new JButton("\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=comboBox.getSelectedItem().toString();
				String sql="select * from Position where Plevel='"+name+"';";
				System.out.println(sql);
				try {
					showPosition(sql);
					addComboBox();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}

			
		});
		button_1.setBounds(144, 10, 67, 23);
		add(button_1);

	}
//	private void showDepartment() throws SQLException {
//		String sql="select * from Position;";
//		comboBox.removeAllItems();
//		String item;
//		SQLManager sm=SQLManager.createInstance();
//		sm.connectDB();
//		rs = sm.executeQuery(sql);
//		while(rs.next())
//		{	item=rs.getString(3);
//			comboBox.addItem(item);
//		}
//		sm.closeDB();
//	}
	
	private void addComboBox() throws SQLException {
		String sql="select * from Position;";
		comboBox.removeAllItems();
		String item;
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();
		rs = sm.executeQuery(sql);
		TreeSet<String> tree=new TreeSet();
		while(rs.next())
		{	item=rs.getString(3);
			tree.add(item);
		}
		
		for(String value:tree){
			comboBox.addItem(value);
		}
		sm.closeDB();
	}
	public void showPosition(String sql) throws SQLException{
		vName.clear();
		nameLists = new String[] {"职位号","职位名","职位等级","工资"};
		for (String obj : nameLists) {
			vName.add(obj);
		}
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();
		
		rs = sm.executeQuery(sql);
		vData.clear();
		while(rs.next())
		{
			for(int i=1;i<=4;i++){
				vRow.add(rs.getString(i));
			}
			vData.add(vRow.clone());
			vRow.clear();
		}
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		sm.closeDB();
	}

}
