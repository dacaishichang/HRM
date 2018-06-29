package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ibm.icu.util.BytesTrie.Result;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DepAdd extends JPanel {
	private JTextField Dname;
	private JTextField Director;
	private SQLManager sm;
	public ResultSet rs;
	/**
	 * Create the panel.
	 */
	public DepAdd() {
		setLayout(null);
		
		JLabel label = new JLabel("\u90E8\u95E8\u540D\u79F0");
		label.setBounds(21, 35, 66, 15);
		add(label);
		
		Dname = new JTextField();
		Dname.setToolTipText("\u90E8\u95E8\u6DFB\u52A0");
		Dname.setText((String) null);
		Dname.setColumns(10);
		Dname.setBounds(21, 60, 74, 21);
		add(Dname);
		
		Director = new JTextField();
		Director.setToolTipText("\u9009\u62E9\u90E8\u957F");
		Director.setText((String) null);
		Director.setColumns(10);
		Director.setBounds(144, 60, 86, 21);
		add(Director);
		
		JLabel label_2 = new JLabel(" \u90E8\u957F\u540D");
		label_2.setBounds(149, 35, 67, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("\u90E8\u95E8\u589E\u52A0(\u5FC5\u586B)");
		label_3.setFont(new Font("方正姚体", Font.PLAIN, 16));
		label_3.setBounds(74, 10, 109, 21);
		add(label_3);
		
		JButton btn_sure = new JButton("\u786E\u8BA4");
		btn_sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=Dname.getText();
				String director=Director.getText();
				try {
					addDepartment(name,director);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				reset();
			}

			

			
		});
		btn_sure.setBounds(74, 123, 109, 31);
		add(btn_sure);

	}
	private void addDepartment(String Dname,String Director) throws SQLException {
		sm = SQLManager.createInstance();
		sm.connectDB();
		int count=0;
		//查询
		//职位号
		String Pno=null;
		String sql2="select * from Position where Pname='部门主任';";
		rs=sm.executeQuery(sql2);
		while(rs.next()){
			Pno=rs.getString("Pno");
		}
		//员工号
		String eno=null;
		String sql1="select * from Employee where Ename='"+Director+"';";
		System.out.println(sql1);
		rs=sm.executeQuery(sql1);
		while(rs.next()){
			eno=rs.getString("Eno");
		}
		
		
		//添加部门
		String sql="insert into Department(Dname,DirectorNo) values('"+Dname+"',"+eno+")";
		System.out.println(sql);
		count=sm.executeUpdate(sql);
		//修改个人职位
		String sql3="update Serve set Pno = "+Pno+" where Eno= "+eno+";";
		System.out.println(sql3);
		int count2=0;
		count2=sm.executeUpdate(sql3);
		//得到新部门号
		String Dno=null;
		String sql4="select * from Department where Dname='"+Dname+"';";
		System.out.println(sql4);
		rs=sm.executeQuery(sql4);
		while(rs.next()){
			Dno=rs.getString("Dno");
		}
		//更新部门

		String sql5="update Attend set Dno = "+Dno+" where Eno= "+eno+";";
		System.out.println(sql5);
		int count3=0;
		count3=sm.executeUpdate(sql5);
		
		
		if(count==1&&count2==1&&count3==1){
			System.out.println("添加成功！");
			JOptionPane.showMessageDialog(null, "部门添加成功！");
		}else{
			JOptionPane.showMessageDialog(null, "部门添加失败！");
		}
		
		sm.closeDB();
	}

	private void reset() {
		Dname.setText(null);
		Director.setText(null);
	}
}
