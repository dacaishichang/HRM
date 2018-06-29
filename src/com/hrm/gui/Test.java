package com.hrm.gui;

import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		String[] nameList={"部门号","部门名","职位号","职位名","职位等级","主任电话","工资","招收人数"};
		Vector vData = new Vector();//数据
		Vector vName = new Vector();//列名
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
