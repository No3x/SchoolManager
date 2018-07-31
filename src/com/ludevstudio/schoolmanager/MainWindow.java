package com.ludevstudio.schoolmanager;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application implements EventHandler<ActionEvent> {
	ResourceBundle bundle;
	
	BorderPane root;
	VBox sideBar;
	
	
	// Menu Items
	Button sidebarItemSchedule, sidebarItemHomework, sidebarItemMark;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		bundle = ResourceBundle.getBundle("Bundle");
	
		root = new BorderPane();
		Scene mainScene = new Scene(root);
		stage.initStyle(StageStyle.DECORATED);
		initComponents();
		
		
		mainScene.getStylesheets().add(getClass().getClassLoader().getResource("style/style.css").toExternalForm());
		stage.setScene(mainScene);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setMinHeight(800);
		stage.setMinWidth(1200);
		
		stage.show();
	}
	
	public void initComponents() {
		
		
		initSideBar();
		
		
		root.setLeft(sideBar);
		
		
	}
	
	
	private void initSideBar () {
		sideBar = new VBox();
		sideBar.setId("sidebar");
		
		sidebarItemSchedule = new Button(bundle.getString("MainWindow.Sidebar.Item.Schedules.Title"));
		sidebarItemSchedule.setId("sidebaritem");
		sidebarItemSchedule.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemSchedule.setStyle("-fx-text-fill: white; -fx-background-color: #00a;");
		sidebarItemSchedule.setOnAction(this);
		sideBar.getChildren().add(sidebarItemSchedule);
		
		sidebarItemHomework = new Button(bundle.getString("MainWindow.Sidebar.Item.Homeworks.Title"));
		sidebarItemHomework.setId("sidebaritem");
		sidebarItemHomework.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemHomework.setOnAction(this);
		sideBar.getChildren().add(sidebarItemHomework);
		
		sidebarItemMark = new Button(bundle.getString("MainWindow.Sidebar.Item.Marks.Title"));
		sidebarItemMark.setId("sidebaritem");
		sidebarItemMark.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemMark.setOnAction(this);
		sideBar.getChildren().add(sidebarItemMark);
		
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==sidebarItemSchedule) {
			for(Node item : sideBar.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemSchedule.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		} else if(event.getSource()==sidebarItemHomework) {
			for(Node item : sideBar.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemHomework.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		} else if(event.getSource()==sidebarItemMark) {
			for(Node item : sideBar.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemMark.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		}
	}

	
	
	
	
		
}
