package com.ludevstudio.schoolmanager;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import org.controlsfx.control.GridCell;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public final class Schedule extends GridPane  {
	ResourceBundle bundle;
	
	int days;
		int houers;
	
	public Schedule(int houers, boolean weekend) {
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: white;");
		setGridLinesVisible(true);
		
		
		bundle = ResourceBundle.getBundle("Bundle");
		
		if(weekend) {
			this.days = 7;
		} else {
			this.days = 5;
		}
		this.houers = houers;

		
		
		createHeader();
		
		
		for(int i = 1; i<=days; i++) {
		for(int j = 1; j<=houers; j++) {
			setEmpty(i, j);
		}	
		}
	
		
		load();
		
		scaleColumnWidth();
		
		
	
	}
	
	
	
		
	
	
	
	public void scaleColumnWidth() {
		ColumnConstraints c = new ColumnConstraints();
		c.fillWidthProperty().set(true);
		getColumnConstraints().add(c);
		
		for(int i = 0; i<days; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setPercentWidth(87.0/days);
			
			getColumnConstraints().add(cc);
		} 
		
	}
	
	
	
	public Label dayCell(int day) {
		String[] days = new String[] {
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Monday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Tuesday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Wednesday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Thursday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Friday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Saturday"),
				bundle.getString("MainWindow.Schedulepage.Schedule.Days.Sunday")
			};
		
		Label cell = new Label(days[day]);
		cell.setAlignment(Pos.CENTER);
		cell.setStyle("-fx-background-color: #00f; -fx-text-fill: #fff; -fx-font-weight: 700; -fx-padding: 7; -fx-font-size: 2em;");
		cell.setTextOverrun(OverrunStyle.CLIP);
		GridPane.setHgrow(cell, Priority.ALWAYS);
		
		cell.setMaxWidth(Double.MAX_VALUE);
		return cell;
	}
	
	
	
	
	
	public HBox houerCell(int houer) {
		HBox cell =new HBox();
		Label labHouer = new Label(houer + "");
		labHouer.setStyle("-fx-text-alignment: left; -fx-background-color: #00f; -fx-text-fill: #fff; -fx-font-weight: 700; -fx-padding: 7 0 7 4; -fx-font-size: 2em; -fx-max-width: infinity; -fx-max-height: infinity");
		labHouer.setAlignment(Pos.BASELINE_LEFT);
		GridPane.setHgrow(labHouer, Priority.ALWAYS);
		
		VBox timeBox = new VBox();
		timeBox.maxHeight(Double.MAX_VALUE);
		timeBox.maxWidth(Double.MAX_VALUE);
		timeBox.setAlignment(Pos.CENTER_RIGHT);
		timeBox.setStyle("-fx-margin: 0 5 0 0;");
		Label labBegin = new Label("12:30");
		labBegin.setStyle("-fx-text-fill: white; -fx-background-color: blue;");
		
		Label labEnd = new Label("13:45");
		labEnd.setStyle("-fx-text-fill: white; -fx-background-color: blue;");
		timeBox.getChildren().addAll(labBegin, labEnd);
		
		
		cell.getChildren().add(labHouer);
		cell.getChildren().add(timeBox);
		cell.setMaxWidth(Double.MAX_VALUE);
		
		cell.setStyle("-fx-background-color: blue; -fx-margin: 0 20 0 20; ");
		cell.setAlignment(Pos.CENTER_LEFT);
		return cell;
	}
	
	
	
	
	private void createHeader() {
		for(int i=0; i<days; i++) {
			add(dayCell(i), (i+1), 0);
		}
		for(int i=0; i<houers; i++) {
			add(houerCell(i+1), (0), (i+1));
		}
	}
	
	
	public void setLesson(int day, int houer, String subject, String teacher, String room, String color) {
		AnchorPane cell =new AnchorPane();
		cell.setStyle("-fx-background-color: "+color+";");
	
		
		Label labName = new Label(subject);
		labName.setTextOverrun(OverrunStyle.CLIP);
		labName.setAlignment(Pos.CENTER);
		labName.setStyle("-fx-font-size: 200%;  -fx-font-weight: 700; -fx-text-fill: white; -fx-max-height: infinity");
		
		Label labRoom = new Label(room);
		labRoom.setStyle("-fx-font-weight: 700; -fx-text-fill: white;");
		labRoom.setAlignment(Pos.BASELINE_RIGHT);
		Label labTeacher = new Label(teacher);
		labTeacher.setStyle("-fx-font-weight: 700; -fx-text-fill: white; ");
		cell.setLeftAnchor(labName, 5d);
		cell.setRightAnchor(labName, 5d);
		cell.getChildren().add(labName);
		
		cell.setLeftAnchor(labTeacher, 2d);
		cell.setRightAnchor(labTeacher, 2d);
		cell.setBottomAnchor(labTeacher, 0d);
		cell.getChildren().add(labTeacher);
		
		cell.setRightAnchor(labRoom, 2d);
		cell.setLeftAnchor(labRoom, 2d);
		cell.setBottomAnchor(labRoom, 0d);
		cell.getChildren().add(labRoom);
		
		
		
		
		add(cell, day, houer);
		
	}


	public void setEmpty(int day, int houer) { 
		Label cell = new Label("");
		cell.setMaxHeight(Double.MAX_VALUE);
		cell.setMaxWidth(Double.MAX_VALUE);
		
		cell.setOnMouseClicked((MouseEvent t) -> {
	       
		setLesson(GridPane.getColumnIndex(cell), GridPane.getRowIndex(cell), "Geschichte", "Frau König", "119", "#198974");
		
		
		});
		
		add(cell, day, houer);
		
		
		
	}
	
	private void load() {
		DataBaseControler controler =new DataBaseControler();
		ArrayList<String[]> subjects = controler.loadScheduleSubjects("test");
		
		for(int i=0; i<subjects.size(); i++) {
			for(int j=0; j<days; j++) {
				ArrayList<String> subject = controler.loadSubject(subjects.get(i)[j]);
				if(subjects.get(i)[j]!=null && subject.size()!=0) {
					setLesson(j+1, i+1, subject.get(0), subject.get(1), subject.get(2), subject.get(3));
				} else {
					setEmpty(j+1, i+1);
				}
			}
		}
	
	}
	
}
