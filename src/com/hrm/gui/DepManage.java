package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepManage extends JPanel {

	private JPanel panel_1;
	private JPanel show_panel;
	private JButton btn_add;
	private JButton btn_del;
	private JButton btn_change;
	private JPanel welcome_panel;
	private CardLayout card;
	public DepAdd depadd;
	public DepDel depdel;
	public DepChange depcha;

	/**
	 * Create the panel.
	 */
	public DepManage() {
		setLayout(null);
		
		card = new CardLayout();
		
		
		show_panel = new JPanel();
		show_panel.setLayout(card);
		show_panel.setBounds(10, 10, 303, 250);
		add(show_panel);
		
		welcome_panel = new JPanel();
		welcome_panel.setBounds(0, 0, 303, 250);
		show_panel.add(welcome_panel);
		welcome_panel.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u4ECE\u53F3\u8FB9\u5F00\u59CB\u4F60\u7684\u4FEE\u6539\uFF01");
		label.setBounds(44, 147, 220, 23);
		welcome_panel.add(label);
		label.setFont(new Font("幼圆", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("\u6B22\u8FCE\u6765\u5230\u90E8\u95E8\u4FEE\u6539\u7BA1\u7406\uFF01");
		label_1.setBounds(44, 60, 220, 23);
		welcome_panel.add(label_1);
		label_1.setFont(new Font("幼圆", Font.PLAIN, 20));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(319, 69, 121, 156);
		add(panel_1);
		
		btn_add = new JButton("\u589E\u52A0");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "添加部门");
			}
		});
		btn_add.setBounds(14, 5, 93, 33);
		panel_1.add(btn_add);
		
		btn_del = new JButton("\u5220\u9664");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "删除部门");
			}
		});
		btn_del.setBounds(14, 58, 93, 33);
		panel_1.add(btn_del);
		
		btn_change = new JButton("\u4FEE\u6539");
		btn_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "修改部门");
			}
		});
		btn_change.setBounds(14, 113, 93, 33);
		panel_1.add(btn_change);
			
		depadd=new DepAdd();
		depcha=new DepChange();
		depdel=new DepDel();
		
		show_panel.add(depadd,"添加部门");
		show_panel.add(depcha,"修改部门");
		show_panel.add(depdel,"删除部门");
		

	}
}
