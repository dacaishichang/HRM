package com.hrm.gui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecManage extends JPanel {
	private JButton button_del;
	private JButton button_add;
	private JPanel welcome_panel;
	private JPanel show_panel;
	private CardLayout card;
	private RecAdd recadd;
	private RecDel recdel;


	/**
	 * Create the panel.
	 */
	public RecManage() {
		setLayout(null);
		
		card = new CardLayout();
		
		show_panel = new JPanel();
		show_panel.setBounds(10, 26, 303, 250);
		add(show_panel);
		show_panel.setLayout(card);
		
		welcome_panel = new JPanel();
		show_panel.add(welcome_panel, "name_15648971340890");
		welcome_panel.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u6765\u5230\u62DB\u8058\u7BA1\u7406\uFF01");
		label.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		label.setBounds(39, 56, 228, 37);
		welcome_panel.add(label);
		
		JLabel label_1 = new JLabel("\u8BF7\u4ECE\u53F3\u8FB9\u5F00\u59CB\u4F60\u7684\u7BA1\u7406\uFF01");
		label_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		label_1.setBounds(39, 139, 228, 37);
		welcome_panel.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(319, 61, 121, 141);
		add(panel);
		
		button_add = new JButton("\u589E\u52A0");
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "Ôö¼ÓÕÐÆ¸");
			}
		});
		button_add.setBounds(14, 5, 93, 33);
		panel.add(button_add);
		
		button_del = new JButton("\u5220\u9664");
		button_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(show_panel, "É¾³ýÕÐÆ¸");
			}
		});
		button_del.setBounds(14, 86, 93, 33);
		panel.add(button_del);
		
		
		recadd = new RecAdd();
		recdel = new RecDel();

		
		show_panel.add(recadd,"Ôö¼ÓÕÐÆ¸");
		show_panel.add(recdel,"É¾³ýÕÐÆ¸");


	}

}
