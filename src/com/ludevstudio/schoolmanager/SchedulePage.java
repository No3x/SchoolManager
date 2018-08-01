package com.ludevstudio.schoolmanager;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SchedulePage extends BorderPane implements EventHandler<ActionEvent> {
	ResourceBundle bundle;
	
	
	VBox titlebar;
	
	
	Button btnAdd;
	
	
	public SchedulePage() {
		bundle = ResourceBundle.getBundle("Bundle"); // Get the Bundle for multipe lang strings
		
		
		initTitleBar();  // Call method to init the title bar
		setTop(titlebar); // set the titlebar to top of the Borderpane
		
		
		// Button for add schedules
	AnchorPane ancBtnAdd = new AnchorPane();	// Container for Button to anchor  right-bottum corner
	 btnAdd = new Button(bundle.getString("MainWindow.Schedulepage.ButtonAdd.Title")); // Create the button
	btnAdd.setId("scheduletitlebaraddbutton");  // set id for get style from css
	btnAdd.setOnAction(this);   // set Action listener
	ancBtnAdd.setRightAnchor(btnAdd, 20d);	// set anchors
	ancBtnAdd.setBottomAnchor(btnAdd, 20d);
	ancBtnAdd.getChildren().add(btnAdd);  // add Button to Container
	setBottom(ancBtnAdd);   // Add container to Borderpane
	
}
	
	// init Titlebar
	private void initTitleBar() {
		titlebar = new VBox();
		Label title = new Label(bundle.getString("MainWindow.Schedulepage.Header.Title"));  // title text
		title.setId("scheduletitlebartitle");
		titlebar.getChildren().add(title);
	
		
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==btnAdd) {
			CreateScheduleDialog dialog = new CreateScheduleDialog();
			dialog.show();
			
		}
	}
	
	
}
