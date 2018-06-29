package com.hrm.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManagerMain extends JFrame implements ActionListener {

	public static JPanel Mcenter;
	public static CardLayout card=null;//卡片布局
	public static LogInPanel lgin=null;//登录
	public static RegisterPanel regi=null;//注册
	public static AdminPanel adm=null;//管理员，查，改所有
	public static EmPloPanel empl=null;//员工，只能查看自己
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMain frame = new ManagerMain("人力资源管理系统——测试版");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ManagerMain(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 360);
		Mcenter = new JPanel();
		Mcenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		Mcenter.setLayout(new BorderLayout(0, 0));
		setContentPane(Mcenter);
		card=new CardLayout();
		lgin=new LogInPanel();
		lgin.btn_cancel.setText("\u9000\u51FA");
		regi=new RegisterPanel();
		adm=new AdminPanel();
		empl=new EmPloPanel();
		
		//登录
		lgin.btn_login.addActionListener(this);
		lgin.btn_cancel.addActionListener(this);
		lgin.btn_register.addActionListener(this);
		//注册
		regi.btn_reg_cancel.addActionListener(this);
		regi.btn_reg_sure.addActionListener(this);
		
		//管理界面
		
		adm.mana_exit.addActionListener(this);//退出
		adm.change_pass.addActionListener(this);//修改密码
		//员工界面
		empl.empl_exit.addActionListener(this);
		
		Mcenter.setLayout(card);
		Mcenter.add("登录",lgin);
		Mcenter.add("注册",regi);
		Mcenter.add("管理员",adm);
		Mcenter.add("员工",empl);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
/*---------------------//登录--------------------------*/
		if(e.getSource()==lgin.btn_login){
			//实验性
			int Plevel=0;
			try {
				Plevel=lgin.login(); 
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}
			if(Plevel<=0){
				JOptionPane.showMessageDialog(null, "登录失败！\n账号或者密码错误！");
				System.out.println("Plevel==0！");
				
			}else if(Plevel>=5){
				
				JOptionPane.showMessageDialog(null, "管理员登录成功！");
				lgin.reset();
				adm.setLevel(Plevel);
				card.show(Mcenter, "管理员");
				
			}else{
				JOptionPane.showMessageDialog(null, "员工登录成功！");
				lgin.reset();
				empl.setLevel(Plevel);
				card.show(Mcenter, "员工");
			}
			
			
		}else if(e.getSource()==lgin.btn_register){
				card.show(Mcenter, "注册");
		}else if(e.getSource()==lgin.btn_cancel){
			System.exit(0);
		}
		
		
/*---------------------//注册--------------------------*/
		if(e.getSource()==regi.btn_reg_sure){
			int count=0;
			try {
				count=regi.register();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(count>=2){
				regi.reset();
				JOptionPane.showMessageDialog(null, "注册成功"); 
				card.show(Mcenter, "登录");
			}else{
				JOptionPane.showMessageDialog(null, "注册失败"); 
				System.out.println("注册失败");
			}
		}
			if(e.getSource()==regi.btn_reg_cancel){
			regi.reset();
			card.show(Mcenter, "登录");
		}
/*---------------------//管理--------------------------*/
		if(e.getSource()==adm.mana_exit){
			card.show(Mcenter, "登录");
		}
/*---------------------//员工--------------------------*/
		if(e.getSource()==empl.empl_exit){
			card.show(Mcenter, "登录");
		}
	}
		
}
