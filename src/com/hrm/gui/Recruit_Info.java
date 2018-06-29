package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class Recruit_Info extends JPanel {
	private JTable table;//表
	private JButton btn_add;
	private DefaultTableModel model;
	private ResultSet rs;
	private Vector vData;
	public Vector vName;
	public Vector vRow;
	private JScrollPane scrollPane;//滑动条
	private String[] nameLists;
	private JComboBox comboBox;
	
	/**
	 * Create the panel.
	 */
	
	public Recruit_Info() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("\u516C\u53F8\u62DB\u8058\u4FE1\u606F");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(335, 10, 120, 24);
		
		
		vData = new Vector();
		vName = new Vector();//列名
		vRow = new Vector();
		
		table = new JTable();
		
		try {
			String sql="select * from Recruit_look;";
			showRecruit(sql);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		btn_add = new JButton("\u5237\u65B0");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="select * from Recruit_look;";
					showRecruit(sql);
					addComboBox();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btn_add.setBounds(465, 13, 78, 23);
		add(btn_add);
		
		add(lblNewLabel);
		
		scrollPane.setBounds(10, 44, 533, 190);
		
		scrollPane.setViewportView(table);
		comboBox = new JComboBox();
		comboBox.setBounds(69, 13, 71, 21);
		add(comboBox);
		try {
			addComboBox();
		} catch (SQLException e2) {
			
			e2.printStackTrace();
		}
		
		
		JButton btn_sure = new JButton("\u67E5\u8BE2");
		btn_sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=comboBox.getSelectedItem().toString();
				String sql="select * from Recruit_look where Dname='"+name+"';";
				System.out.println(sql);
				try {
					showRecruit(sql);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btn_sure.setBounds(170, 13, 78, 23);
		add(btn_sure);
		
		JLabel label = new JLabel("\u90E8\u95E8");
		label.setBounds(26, 17, 33, 15);
		add(label);
	}
	private void addComboBox() throws SQLException {
		String sql="select * from Recruit_look;";
		comboBox.removeAllItems();
		String item;
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();
		rs = sm.executeQuery(sql);
		TreeSet<String> tree=new TreeSet();
		while(rs.next())
		{	item=rs.getString(2);
			tree.add(item);
		}
		for(String value:tree){
			comboBox.addItem(value);
		}
		sm.closeDB();
	}
	public void showRecruit(String sql) throws SQLException{
		
		vName.clear();
		nameLists = new String[] {"部门号","部门名","职位号","职位名","职位等级","主任电话","工资","招收人数"};
		for (String obj : nameLists) {
			vName.add(obj);
		}
		SQLManager sm=SQLManager.createInstance();
		sm.connectDB();

		rs = sm.executeQuery(sql);
		vData.clear();
		while(rs.next())
		{
			for(int i=1;i<=8;i++){
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
