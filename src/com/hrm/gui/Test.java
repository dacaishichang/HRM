package com.hrm.gui;

import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		String[] nameList={"���ź�","������","ְλ��","ְλ��","ְλ�ȼ�","���ε绰","����","��������"};
		Vector vData = new Vector();//����
		Vector vName = new Vector();//����
		for (String obj : nameList) {
			vName.add(obj);
		}
		Vector vRow = new Vector();
		
		String[][] values={
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
				{"1","2","3","4","5","6","7","8"},
		};
		for (String[] strings : values) {
			for (String string : strings) {
				vRow.add(string);
			}
			vData.add(vRow.clone());
			System.out.println(vRow.clone());
			vRow.clear();
		}
	}
}
