package com.ludevstudio.schoolmanager.Schedule;

import com.ludevstudio.schoolmanager.Subject;

public class LessonCell {
	Subject subject;
	int day;
	int lesson;
	
	
	public LessonCell(Subject subject, int day, int lesson) {
		this.subject = subject;
		this.day = day;
		this.lesson = lesson;
	}


	public Subject getSubject() {
		return subject;
	}


	public int getDay() {
		return day;
	}


	public int getLesson() {
		return lesson;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public void setLesson(int lesson) {
		this.lesson = lesson;
	}
	
	
}
