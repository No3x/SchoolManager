package com.ludevstudio.schoolmanager.Schedule;

import java.util.ArrayList;
import java.util.ResourceBundle;

import com.ludevstudio.schoolmanager.DataBaseControler;
import com.ludevstudio.schoolmanager.Subject;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Schedule extends GridPane {
	String name;
	int days;
	int lessons;
	Boolean hasZeroLesson;
	Boolean hasWeekend;

	
	ResourceBundle bundle = ResourceBundle.getBundle("bundle");
	DataBaseControler controller;
	
	// Hex Colors
	final String basicColor = "blue";
	
	
	// Resources
	ArrayList<Subject> allSubjects;
	
	
	public Schedule(String name, int lessons, Boolean hasZeroLesson, Boolean hasWeekend) {
		controller =new DataBaseControler();
		
		this.name = name;
		this.lessons = lessons;
		this.hasZeroLesson = hasZeroLesson;
		this.hasWeekend = hasWeekend;
	
	// init
		setupGridpane();
		createEmptySchedule();
		load();
	}
	
	
	
	// tinit the Schedules UI before loading subjects
	private void createEmptySchedule() {
		// set nubmer of days
		if(hasWeekend) { 
			days = 7; 
		 } else { 
			 days = 5;
			} 
			
		// set top Left corner
		Label topLeftCorner = new Label();
		topLeftCorner.setText("");
		GridPane.setHgrow(topLeftCorner, Priority.ALWAYS);
		GridPane.setVgrow(topLeftCorner, Priority.ALWAYS);
		topLeftCorner.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		topLeftCorner.setStyle("-fx-background-color: "+basicColor+";");
		add(topLeftCorner, 0, 0);
		
		
		
		// set the day Headline cells
		for(int i = 1; i<=days; i++) {
			add(dayUI(new Day(i)), i, 0);
		}
		
		// set the lessons headlines
		for(int i = 1; i<=lessons; i++) {
			add(lessonUI(new Lesson(i)), 0, i);
		}
		
		
	}
	
	
	// gridpane Settings
	private void setupGridpane() {
		// set Lines
		this.setGridLinesVisible(true);
		
		// set Stretching
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		
		
		// Set Column Width
		
		ColumnConstraints c =new ColumnConstraints();
		c.setMaxWidth(50);
		this.getColumnConstraints().add(c);
		for(int i =0; i<days; i++) {
			ColumnConstraints cc =new ColumnConstraints();
			cc.setPercentWidth(99/days);
			this.getColumnConstraints().add(cc);
		}
		
		
	}
	
	
	// Load schedule Subjects
			private void load() {
				
				// Load all existing Subjects
				ArrayList<String> allSubjectIDs = controller.getSubjectList();
				allSubjects =new ArrayList<>();
				
				for(String ID : allSubjectIDs) {
					ArrayList<String> subjectInfos = controller.loadSubject(ID);
					
					
					
					allSubjects.add(new Subject(subjectInfos.get(0), subjectInfos.get(1), 
							subjectInfos.get(2), subjectInfos.get(3), Color.web(subjectInfos.get(4))));
				}
				
				// fill subjects into schedule
				ArrayList<String[]> subjectIDs = controller.loadScheduleSubjects(name);
				
				for(int i=0; i<lessons; i++) {
					for(int j=0; j<days; j++) {
						Subject subject = getSubject(subjectIDs.get(i)[j]);
						if(subject!=null) {
						add(subjectUI(subject), j+1, i+1);
					}
					}
				}
				
			}
	
			
			
			
	// Cell UIs
	
	private Label dayUI(Day day) {
		Label cell =new Label();
		cell.setText(day.getName());
		cell.setStyle("-fx-background-color: "+basicColor+"; "
				+ "-fx-text-fill: white; "
				+ "-fx-padding: 7; "
				+ "-fx-font-size: 1.4em;  "
				+ "-fx-font-weight: bold;");
		cell.setAlignment(Pos.CENTER);
		cell.setMaxWidth(Double.MAX_VALUE);
		GridPane.setHgrow(cell, Priority.ALWAYS);
		return cell;
	}
	
	
	private Label lessonUI(Lesson lesson) {
		Label cell =new Label();
		cell.setText(lesson.getNumber()+"");
		cell.setStyle("-fx-background-color: "+basicColor+"; "
				+ "-fx-text-fill: white; "
				+ "-fx-padding: 7; "
				+ "-fx-font-size: 1.4em;  "
				+ "-fx-font-weight: bold;");
		cell.setAlignment(Pos.CENTER);
		cell.setMaxWidth(Double.MAX_VALUE);
		GridPane.setHgrow(cell, Priority.ALWAYS);
		return cell;
	} 

	private VBox subjectUI(Subject subject) {
		String colorStr =subject.getColor().toString().replace("0x", "#");
		
		VBox cell =new VBox();
		Label labName =new Label(subject.getName());
		labName.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 150%;");
		Label labTeacher =new Label(subject.getTeacher());
		labTeacher.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
		Label labRoom =new Label(subject.getRoom());
		labRoom.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
		cell.getChildren().addAll(labName, labTeacher, labRoom);
		cell.setStyle("-fx-background-color: "+colorStr+"; "
				+ "-fx-padding: 5 10 5 10;");
		
		return cell;
	}
	
	
	
	
	// Other
	private Subject getSubject(String ID) {
		for(Subject s : allSubjects) {
			if(s.getID().equals(ID)) {
				return s;
			}
		}
		return null;
	}
	
	
}
