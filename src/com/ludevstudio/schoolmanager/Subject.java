package com.ludevstudio.schoolmanager;

import javafx.scene.paint.Color;

public class Subject {
	String ID;
	String name;
	String teacher;
	String room;
	Color color;
	
	
	public Subject(String ID, String name, String teacher, String room, Color color) {
		this.ID = ID;
		this.name = name;
		this.teacher = teacher;
		this.room = room;
		this.color = color;
	}


	
	
	
	
	
	public String getID() {
		return ID;
	}


	public String getName() {
		return name;
	}


	public String getTeacher() {
		return teacher;
	}


	public String getRoom() {
		return room;
	}


	public Color getColor() {
		return color;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
