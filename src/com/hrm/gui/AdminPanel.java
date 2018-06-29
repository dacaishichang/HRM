package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class AdminPanel extends JPanel {
	public JMenuBar Personal;
	public JMenu change_info;
	public JMenuItem change_pass;
	public JMenu menu;
	public JMenuBar manage_item;
	public JMenuItem change_person;
	public JMenuItem mod_po;
	public JPanel tool_bar_panel;
	public CardLayout card;
	public JPanel show_panel;

	public JMenuItem mana_exit;
	private JMenuBar see_item;
	private JMenu menu_1;
	private JMenuItem see_recruit;
	public ModifyPassword mdfp;
//	public ModifyPassword2 mdfp;
	public PersonInfo PersonInfo;
	public static int plevel=0;
	private JPanel panel_1;
	private JLabel label;
	private JMenuItem change_recruit;
	private JMenuItem change_serve;
	private JMenuItem change_position;
	private JMenuItem see_position;
	private JMenuItem see_department;
	public Recruit_Info emploRec;
	public Position_info emploPos;
	public Department_info emploDep;
	public PosManage emploPM;
	public DepManage emploDM;
	public RecManage emploRM;
	public SerChange emploSC;
	public PersonTable see_person;
	private JMenuItem see_Ptable;
	Emplo_Department emplo_Depart;
	private JMenuItem see_Detail;
	public EmploDel delEmplo;
	private JMenuItem delEmployee;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public AdminPanel() {
		setLayout(null);
		card=new CardLayout();
		
		tool_bar_panel = new JPanel();
		tool_bar_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tool_bar_panel.setBounds(10, 9, 232, 39);
		add(tool_bar_panel);
		tool_bar_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Personal = new JMenuBar();
		tool_bar_panel.add(Personal);
		
		change_info = new JMenu("\u4E2A\u4EBA\u8BBE\u7F6E");
		Personal.add(change_info);
		
		change_pass = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		change_pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				card.show(show_panel, "修改密码");
			}
		});
		change_info.add(change_pass);
		
		change_person = new JMenuItem("\u4E2A\u4EBA\u4FE1\u606F");
		change_person.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "个人信息");
				System.out.println(LogInPanel.Eno);
			}
		});
		
		change_info.add(change_person);
		
		mana_exit = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		change_info.add(mana_exit);
		
		see_Detail = new JMenuItem("\u8BE6\u7EC6\u4FE1\u606F");
		see_Detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "详细信息");
			}
		});
		change_info.add(see_Detail);
		
		see_item = new JMenuBar();
		tool_bar_panel.add(see_item);
		
		menu_1 = new JMenu("\u4EBA\u4E8B\u67E5\u8BE2");
		see_item.add(menu_1);
		
		see_recruit = new JMenuItem("\u67E5\u770B\u62DB\u8058");
		see_recruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "查看招聘");
			}
		});
		menu_1.add(see_recruit);
		
		see_position = new JMenuItem("\u67E5\u770B\u804C\u4F4D");
		see_position.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "查看职位");
			}
		});
		menu_1.add(see_position);
		
		see_department = new JMenuItem("\u67E5\u770B\u90E8\u95E8");
		see_department.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "查看部门");
			}
		});
		menu_1.add(see_department);
		
		see_Ptable = new JMenuItem("\u67E5\u770B\u5458\u5DE5");
		see_Ptable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "查看员工");
			}
		});
		menu_1.add(see_Ptable);
		
		manage_item = new JMenuBar();
		tool_bar_panel.add(manage_item);
		
		menu = new JMenu("\u4EBA\u4E8B\u7BA1\u7406");
		manage_item.add(menu);
		
		mod_po = new JMenuItem("\u4FEE\u6539\u804C\u4F4D");
		mod_po.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "修改职位");
			}
		});
		menu.add(mod_po);
		
		change_recruit = new JMenuItem("\u4FEE\u6539\u62DB\u8058");
		change_recruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "修改招聘");
			}
		});
		menu.add(change_recruit);
		
		change_serve = new JMenuItem("\u4EBA\u5458\u804C\u4F4D\u66F4\u6539");
		change_serve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "修改个人职位");
			}
		});
		menu.add(change_serve);
		
		change_position = new JMenuItem("\u4FEE\u6539\u90E8\u95E8");
		change_position.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "修改部门");
			}
		});
		menu.add(change_position);
		
		delEmployee = new JMenuItem("\u5220\u9664\u5458\u5DE5");
		delEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "删除员工");
			}
		});
		menu.add(delEmployee);
		
		show_panel = new JPanel();
		show_panel.setBounds(10, 58, 545, 242);
		add(show_panel);
		show_panel.setLayout(new CardLayout(0, 0));

		
		
		
		mdfp=new ModifyPassword(plevel);
		emploRec=new Recruit_Info();
		emploPos=new Position_info();
		see_person=new PersonTable();
		emploDep=new Department_info();
		emploPM=new PosManage();
		emploDM=new DepManage();
		emploRM=new RecManage();
		emploSC=new SerChange();
		emplo_Depart=new Emplo_Department();
		delEmplo=new EmploDel();
		
		
		PersonInfo=new PersonInfo();
		PersonInfo.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		
		
		show_panel.setLayout(card);
		
		show_panel.add(PersonInfo,"个人信息");
		show_panel.add(mdfp,"修改密码");
		show_panel.add(emploRec,"查看招聘");
		show_panel.add(emploPos,"查看职位");
		show_panel.add(emploDep,"查看部门");
		show_panel.add(emploPM,"修改职位");
		show_panel.add(emploDM,"修改部门");
		show_panel.add(emploRM, "修改招聘");
		show_panel.add(emploSC,"修改个人职位");
		show_panel.add(see_person,"查看员工");
		show_panel.add(emplo_Depart, "详细信息");
		show_panel.add(delEmplo,"删除员工");
		
		JPanel panel = new JPanel();
		show_panel.add(panel, "name_9905441433763");
		
		panel_1 = new JPanel();
		show_panel.add(panel_1, "name_9925065964099");
		
		JButton refresh = new JButton("New button");
		show_panel.add(refresh, "name_9986822466684");
		
		label = new JLabel("\u7BA1\u7406\u5458\u6A21\u5757");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(335, 9, 95, 27);
		add(label);
		
		
		

	}
	public void setLevel(int level){
		this.plevel=level;
	}
}
