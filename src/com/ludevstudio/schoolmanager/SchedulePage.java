package com.ludevstudio.schoolmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ludevstudio.schoolmanager.Schedule.Schedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SchedulePage extends BorderPane implements EventHandler<ActionEvent> {
	ResourceBundle bundle;
	
	
	VBox titlebar;
	GridPane footer;
	
	// Footer controls
	Button btnAdd;
	ComboBox<String> combSchedules;
	
	// Schedule View
	ScrollPane scroll;
	
	// Database Acces
	DataBaseControler controler;
	
	public SchedulePage() {
		bundle = ResourceBundle.getBundle("Bundle"); // Get the Bundle for multipe lang strings
		setStyle("-fx-padding: 0 20 0 20;");
		
		// Database Acces
		 controler =new DataBaseControler();  
		
		
		// Titlebar
		initTitleBar();  // Call method to init the title bar
		setTop(titlebar); // set the titlebar to top of the Borderpane
		
			
		
	
	scroll = new ScrollPane();
	scroll.setStyle("-fx-background-color: white;");
	scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
	scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
//	scroll.setFitToHeight(true);
	scroll.setFitToWidth(true);
	scroll.setPadding(new Insets(0, 0, 20, 0));
	
	setCenter(scroll);
	
	// Footer
			initFooter();
			setBottom(footer); 
		
}
	
	// init Titlebar
	private void initTitleBar() {
		titlebar = new VBox();
		Label title = new Label(bundle.getString("MainWindow.Schedulepage.Header.Title"));  // title text
		title.setId("scheduletitlebartitle");
		titlebar.getChildren().add(title);
	
		
	}
	
	private void initFooter() {
		footer = new GridPane();	// Container for Button to anchor  right-bottum corner
		
		ObservableList<String> combItems =FXCollections.observableList(controler.getScheduleList()); // get all schedule names from database
		 combSchedules = new ComboBox<>(combItems);
		combSchedules.setId("combschedules");
		combSchedules.setOnAction(this);
		GridPane.setFillWidth(combSchedules, true);
		GridPane.setHgrow(combSchedules, Priority.ALWAYS);
		GridPane.setMargin(combSchedules, new Insets(0, 0, 20, 0));
		footer.add(combSchedules, 0, 0);
		
		// if combItems.lenght !=null
		if(!combItems.isEmpty()) {
			combSchedules.setValue(combItems.get(0));
			
			String scheduleName = combSchedules.getItems().get(0);
			updateView(scheduleName);
			
		} else {
			combSchedules.setValue(bundle.getString("MainWindow.Schedulepage.Footer.Combschedules.Noitem"));
		}
		
		
		
		
		btnAdd = new Button(bundle.getString("MainWindow.Schedulepage.ButtonAdd.Title")); // Create the button
		btnAdd.setId("scheduletitlebaraddbutton");  // set id for get style from css
		btnAdd.setOnAction(this);   // set Action listener
		GridPane.setMargin(btnAdd, new Insets(0, 0, 20, 20)); // set margin
		footer.add(btnAdd, 2, 0); // add button to container
		
	
		
	}
	
	

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==btnAdd) {
			CreateScheduleDialog dialog = new CreateScheduleDialog();  // create dialog for create new schedule
			dialog.showAndWait();	// show the dialog
			
			updateCombSchedules();
			updateView(combSchedules.getValue());
		} else if (event.getSource()==combSchedules) {
			updateView(combSchedules.getValue());
		}
	}
	
	private void updateCombSchedules() {
		List<String> scheduleList = controler.getScheduleList();
	 	
		combSchedules.setItems(FXCollections.observableList(scheduleList));
		
		if(!scheduleList.isEmpty()) {
			combSchedules.setValue(scheduleList.get(scheduleList.size()-1));
		}
	}
	
	
	private void updateView(String scheduleName) {
		ArrayList<String> infos = controler.loadScheduleInfos(scheduleName);
		scroll.setContent(new Schedule(infos.get(0), Integer.parseInt(infos.get(2)), new Boolean(Integer.parseInt(infos.get(3))!=0), new Boolean(Integer.parseInt(infos.get(4))!=0)));
		
	}
	
}
