package com.ludevstudio.schoolmanager.Schedule;

import java.util.ResourceBundle;

public class Day {
	int number;
	String name;
	
	
	public Day(int number) {
		ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
		
		this.number = number;
		
		
		String[] names =new String[] {
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Monday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Tuesday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Wednesday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Thursday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Friday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Saturday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Sunday")
		};
		
		name = names[number-1];
		
	}


	public int getNumber() {
		return number;
	}


	public String getName() {
		return name;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
