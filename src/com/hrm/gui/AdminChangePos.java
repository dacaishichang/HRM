package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminChangePos extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminChangePos() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(348, 37, 92, 200);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u589E\u52A0");
		btnNewButton.setBounds(0, 42, 93, 35);
		panel.add(btnNewButton);
		
		JButton button = new JButton("\u51CF\u5C11");
		button.setBounds(0, 98, 93, 35);
		panel.add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.setBounds(0, 155, 93, 35);
		panel.add(button_1);
		
		JLabel label = new JLabel("\u64CD\u4F5C\u9009\u9879");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		label.setBounds(21, 10, 61, 22);
		panel.add(label);

	}

}
