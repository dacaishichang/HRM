package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyPassword extends JPanel {
	private JPasswordField password_old;
	private JPasswordField password_new;
	private JPasswordField password_sure;
	private String acc;
	private String pas;
	private SQLManager sm;
	private LogInPanel lgp;
	/**
	 * Create the panel.
	 */
	public ModifyPassword(int plevel) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(153, 52, 355, 153);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u65E7\u5BC6\u7801");
		lblNewLabel.setBounds(10, 13, 54, 15);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801");
		label.setBounds(10, 54, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801");
		label_1.setBounds(10, 95, 65, 15);
		panel.add(label_1);
		
		JButton btn_sure = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btn_sure.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				int count=0;
				String oldpass=password_old.getText();
				String newpass=password_new.getText();
				String surepass=password_sure.getText();
				sm = SQLManager.createInstance();
				lgp=LogInPanel.createInstance();
				String sql;
				acc=lgp.acc;
				pas=lgp.pas;
				sm.connectDB();
				if(pas.equals(oldpass)&&(surepass.equals(newpass))){
					sql="update Users set Password ="+"'"+newpass+"'"+" where Account='"+acc+"';";
					System.out.println(sql);
					count=sm.executeUpdate(sql);
					
					if(count>=1){
						sm.password=newpass;
						JOptionPane.showMessageDialog(null, "修改成功!");
						System.out.println("修改成功！");
					}
					else{
						JOptionPane.showMessageDialog(null, "修改失败！\n请输入正确信息！");
						System.out.println("修改失败！");
					}
				}
				sm.closeDB();
				reset();
				setVisible(false);
			}

		});
		btn_sure.setBounds(63, 123, 93, 23);
		panel.add(btn_sure);
		
		JButton btn_cancel = new JButton("\u53D6\u6D88\u4FEE\u6539");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				setVisible(false);
			}
		});
		
		btn_cancel.setBounds(184, 123, 93, 23);
		panel.add(btn_cancel);
		
		password_old = new JPasswordField();
		password_old.setBounds(102, 10, 175, 21);
		panel.add(password_old);
		
		password_new = new JPasswordField();
		password_new.setBounds(102, 51, 175, 21);
		panel.add(password_new);
		
		password_sure = new JPasswordField();
		password_sure.setBounds(102, 92, 175, 21);
		panel.add(password_sure);
		
		JLabel label_2 = new JLabel("\u4FEE\u6539\u4F60\u7684\u5BC6\u7801");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(286, 28, 102, 15);
		add(label_2);

	}
	public void reset() {
		password_new.setText(null);
		password_old.setText(null);
		password_sure.setText(null);
		
	}
}
