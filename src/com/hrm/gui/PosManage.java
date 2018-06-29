package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class PosManage extends JPanel {

	private JPanel change_panel;
	private JButton btnchange;
	private JButton btn_del;
	private JButton btn_add;
	private JPanel btn_panel;
	public PosAdd posadd;
	public PosDel posdel;
	public PosChange poscha;
	private CardLayout card;
	private JPanel welcome_panel;
	

	/**
	 * Create the panel.
	 */
	public PosManage() {
		setLayout(null);
		
		btn_panel = new JPanel();
		btn_panel.setBounds(323, 54, 121, 156);
		add(btn_panel);
		btn_panel.setLayout(null);
		
		btn_add = new JButton("\u589E\u52A0");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(change_panel, "增加职位");
			}
		});
		btn_add.setBounds(14, 5, 93, 33);
		btn_panel.add(btn_add);
		
		btn_del = new JButton("\u5220\u9664");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(change_panel, "删除职位");
			}
		});
		btn_del.setBounds(14, 58, 93, 33);
		btn_panel.add(btn_del);
		
		btnchange = new JButton("\u4FEE\u6539");
		btnchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(change_panel, "修改职位");
			}
		});
		btnchange.setBounds(14, 113, 93, 33);
		btn_panel.add(btnchange);
		
		change_panel = new JPanel();
		change_panel.setBounds(10, 10, 303, 250);
		add(change_panel);
		
		card = new CardLayout();
		change_panel.setLayout(card);
		
		//添加
		
		posadd=new PosAdd();
		posadd.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, null, null, null));
		posdel=new PosDel();
		poscha=new PosChange();
		welcome_panel = new JPanel();
		
		
		
		welcome_panel.setLayout(null);
		JLabel label = new JLabel("\u6B22\u8FCE\u6765\u5230\u804C\u4F4D\u4FEE\u6539\u7BA1\u7406\uFF01");
		label.setFont(new Font("幼圆", Font.PLAIN, 20));
		label.setBounds(26, 51, 228, 37);
		
		
		JLabel label_1 = new JLabel("\u8BF7\u4ECE\u53F3\u8FB9\u5F00\u59CB\u4F60\u7684\u4FEE\u6539\uFF01");
		label_1.setFont(new Font("幼圆", Font.PLAIN, 20));
		label_1.setBounds(26, 134, 228, 37);
		
		
		welcome_panel.add(label_1);
		welcome_panel.add(label);
		
		change_panel.add(welcome_panel, "name_30931896660450");
		change_panel.add(posadd,"增加职位");
		change_panel.add(posdel,"删除职位");
		change_panel.add(poscha,"修改职位");
		
	}
}
