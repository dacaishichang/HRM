package com.hrm.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Excercise extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Excercise() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(378, 291, -344, -254);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(334, 235, -269, -178);
		add(table);
		

	}
}
