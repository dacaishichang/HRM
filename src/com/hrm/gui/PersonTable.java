package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonTable extends JPanel {

	private Vector vRow;
	private Vector vName;
	private Vector vData;
	private JScrollPane scrollPane;
	private JButton button_1;
	private JButton button;
	private JTable table;
	private String[] nameLists;
	private ResultSet rs;
	private DefaultTableModel model;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public PersonTable() {
		setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(10, 9, 39, 15);
		add(label);
		
		comboBox = new JComboBox();
		comboBox.setBounds(59, 6, 67, 21);
		add(comboBox);
		
		button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=comboBox.getSelectedItem().toString();
				String sql="select * from Position_look where Ename='"+name+"';";
				System.out.println(sql);
				try {
					showPerson(sql);
					addComboBox();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(144, 5, 67, 23);
		add(button);
		
		JLabel label_1 = new JLabel("\u516C\u53F8\u804C\u5458\u4FE1\u606F");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(249, 4, 108, 24);
		add(label_1);
		
		button_1 = new JButton("\u5237\u65B0");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from Position_look;";
				try {
					
					showPerson(sql);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(367, 7, 78, 23);
		add(button_1);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		
		
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 45, 504, 194);
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		
		vData = new Vector();
		vName = new Vector();
		vRow = new Vector();
		
		try {
			String sql="select * from Position_look;";
			showPerson(sql);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		
		try {
			addComboBox();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		
		

	}
	
	private void addComboBox() throws SQLException {
		String sql="select * from Position_look;";
		comboBox.removeAllItems();
		String item;
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();
		rs = sm.executeQuery(sql);
		TreeSet<String> tree=new TreeSet();
		while(rs.next())
		{	item=rs.getString("Ename");
			tree.add(item);
		}
		
		for(String value:tree){
			comboBox.addItem(value);
		}
		sm.closeDB();
	}
	
	public void showPerson(String sql) throws SQLException{
		vName.clear();
		nameLists = new String[] {"员工号","姓名","性别","年龄","电话","状态","职位","部门","工资"};
		String[] enameList=new String[]{"Eno","Ename","Sex","Age","Phone","Status","Pname","Dname","Salary"};
		for (String obj : nameLists) {
			vName.add(obj);
		}
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();
		
		rs = sm.executeQuery(sql);
		vData.clear();
		while(rs.next())
		{
			for(int i=0;i<9;i++){
				vRow.add(rs.getString(enameList[i]));
			}
			vData.add(vRow.clone());
			vRow.clear();
		}
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		sm.closeDB();
	}

}
