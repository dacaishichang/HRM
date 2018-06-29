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
	public static CardLayout card=null;//��Ƭ����
	public static LogInPanel lgin=null;//��¼
	public static RegisterPanel regi=null;//ע��
	public static AdminPanel adm=null;//����Ա���飬������
	public static EmPloPanel empl=null;//Ա����ֻ�ܲ鿴�Լ�
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMain frame = new ManagerMain("������Դ����ϵͳ�������԰�");
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
		
		//��¼
		lgin.btn_login.addActionListener(this);
		lgin.btn_cancel.addActionListener(this);
		lgin.btn_register.addActionListener(this);
		//ע��
		regi.btn_reg_cancel.addActionListener(this);
		regi.btn_reg_sure.addActionListener(this);
		
		//�������
		
		adm.mana_exit.addActionListener(this);//�˳�
		adm.change_pass.addActionListener(this);//�޸�����
		//Ա������
		empl.empl_exit.addActionListener(this);
		
		Mcenter.setLayout(card);
		Mcenter.add("��¼",lgin);
		Mcenter.add("ע��",regi);
		Mcenter.add("����Ա",adm);
		Mcenter.add("Ա��",empl);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
/*---------------------//��¼--------------------------*/
		if(e.getSource()==lgin.btn_login){
			//ʵ����
			int Plevel=0;
			try {
				Plevel=lgin.login(); 
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}
			if(Plevel<=0){
				JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�\n�˺Ż����������");
				System.out.println("Plevel==0��");
				
			}else if(Plevel>=5){
				
				JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ���");
				lgin.reset();
				adm.setLevel(Plevel);
				card.show(Mcenter, "����Ա");
				
			}else{
				JOptionPane.showMessageDialog(null, "Ա����¼�ɹ���");
				lgin.reset();
				empl.setLevel(Plevel);
				card.show(Mcenter, "Ա��");
			}
			
			
		}else if(e.getSource()==lgin.btn_register){
				card.show(Mcenter, "ע��");
		}else if(e.getSource()==lgin.btn_cancel){
			System.exit(0);
		}
		
		
/*---------------------//ע��--------------------------*/
		if(e.getSource()==regi.btn_reg_sure){
			int count=0;
			try {
				count=regi.register();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(count>=2){
				regi.reset();
				JOptionPane.showMessageDialog(null, "ע��ɹ�"); 
				card.show(Mcenter, "��¼");
			}else{
				JOptionPane.showMessageDialog(null, "ע��ʧ��"); 
				System.out.println("ע��ʧ��");
			}
		}
			if(e.getSource()==regi.btn_reg_cancel){
			regi.reset();
			card.show(Mcenter, "��¼");
		}
/*---------------------//����--------------------------*/
		if(e.getSource()==adm.mana_exit){
			card.show(Mcenter, "��¼");
		}
/*---------------------//Ա��--------------------------*/
		if(e.getSource()==empl.empl_exit){
			card.show(Mcenter, "��¼");
		}
	}
		
}
