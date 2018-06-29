package com.hrm.gui;

import javax.swing.JPanel;

import sun.security.util.Password;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class EmPloPanel extends JPanel {
	public JMenu mnNewMenu;
	public JMenuBar menuBar;
	public JPanel panel;
	public JMenuBar menuBar_1;
	public JMenu menu;
	public JMenuItem empl_exit;
	public JMenuItem person_set;
	public CardLayout card;
	private JMenuItem position_info;
	private JMenuItem recruit_info;
	public JPanel panel_show;
	private JMenuItem change_pas;
	public ModifyPassword mdfp;
	public static int plevel=0;
	public PersonInfo personInfo;
	private JLabel label;
	public Emplo_Department emploInfo;
	public Recruit_Info emploRec;
	/**
	 * Create the panel.
	 */
	public EmPloPanel() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		setLayout(null);
		
		card=new CardLayout();
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, Color.WHITE, null));
		panel.setBounds(10, 10, 207, 35);
		add(panel);
		
		menuBar = new JMenuBar();
		panel.add(menuBar);
		
		mnNewMenu = new JMenu("\u4E2A\u4EBA\u8BBE\u7F6E");
		menuBar.add(mnNewMenu);
		
		empl_exit = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		
		mnNewMenu.add(empl_exit);
		
		person_set = new JMenuItem("\u4E2A\u4EBA\u4E2D\u5FC3");
		person_set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_show, "个人信息");
			}
		});
		mnNewMenu.add(person_set);
		
		change_pas = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		change_pas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_show, "修改密码");
			}
		});
		mnNewMenu.add(change_pas);
		
		menuBar_1 = new JMenuBar();
		panel.add(menuBar_1);
		
		menu = new JMenu("\u516C\u53F8\u4FE1\u606F");
		menuBar_1.add(menu);
		
		position_info = new JMenuItem("\u6211\u7684\u804C\u4F4D\u4FE1\u606F");
		position_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_show, "员工信息");
			}
		});
		menu.add(position_info);
		
		recruit_info = new JMenuItem("\u62DB\u8058\u4FE1\u606F");
		recruit_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_show, "招聘信息");
			}
		});
		menu.add(recruit_info);
		
		panel_show = new JPanel();
		panel_show.setBounds(10, 48, 544, 242);
		
		panel_show.setLayout(card);
		
		add(panel_show);
		
		
		personInfo=new PersonInfo();
		mdfp=new ModifyPassword(plevel);
		emploInfo=new Emplo_Department();
		emploRec=new Recruit_Info();
		
		
		panel_show.add(personInfo,"个人信息");
		panel_show.add(mdfp,"修改密码");
		panel_show.add(emploInfo,"员工信息");
		panel_show.add(emploRec,"招聘信息");
		
		label = new JLabel("\u5458\u5DE5\u6A21\u5757");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(351, 18, 95, 27);
		add(label);
		

	}
	public void setLevel(int level){
		this.plevel=level;
		
	}

}
