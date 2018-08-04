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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application implements EventHandler<ActionEvent> {
	ResourceBundle bundle;
	
	static Stage mainWindowStage;
	
	
	static BorderPane root;
	AnchorPane sideBar;
	VBox sideBarTopButtons, sideBarBottumBButtons;
	
	// Menu Items
	Button sidebarItemSchedule, sidebarItemHomework, sidebarItemMark, sidebarItemSettings;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		bundle = ResourceBundle.getBundle("Bundle");
		mainWindowStage = stage;
		
		root = new BorderPane();
		Scene mainScene = new Scene(root);
		stage.initStyle(StageStyle.DECORATED);
		
		
		
		
		
		
		
		
		mainScene.getStylesheets().add(getClass().getClassLoader().getResource("style/style.css").toExternalForm());
		stage.setScene(mainScene);
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setMinHeight(800);
		stage.setMinWidth(1200);
		
		initComponents();
		stage.show();
	}
	
	public void initComponents() {
		
		
		initSideBar();
		root.setLeft(sideBar);
		
		root.setCenter(new SchedulePage());
		
	}
	
	
	private void initSideBar () {
		sideBar = new AnchorPane();
		sideBar.setId("sidebar");
		
		sideBarTopButtons = new VBox();
		
		sidebarItemSchedule = new Button(bundle.getString("MainWindow.Sidebar.Item.Schedules.Title"));
		sidebarItemSchedule.setId("sidebaritem");
		sidebarItemSchedule.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemSchedule.setStyle("-fx-text-fill: white; -fx-background-color: #00a;");
		sidebarItemSchedule.setOnAction(this);
		sideBarTopButtons.getChildren().add(sidebarItemSchedule);
		
		sidebarItemHomework = new Button(bundle.getString("MainWindow.Sidebar.Item.Homeworks.Title"));
		sidebarItemHomework.setId("sidebaritem");
		sidebarItemHomework.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemHomework.setOnAction(this);
		sideBarTopButtons.getChildren().add(sidebarItemHomework);
		
		sidebarItemMark = new Button(bundle.getString("MainWindow.Sidebar.Item.Marks.Title"));
		sidebarItemMark.setId("sidebaritem");
		sidebarItemMark.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemMark.setOnAction(this);
		sideBarTopButtons.getChildren().add(sidebarItemMark);
		
		
		sideBarBottumBButtons = new VBox();
		
		sidebarItemSettings = new Button(bundle.getString("MainWindow.Sidebar.Item.Settings.Title"));
		sidebarItemSettings.setId("sidebaritem");
		sidebarItemSettings.setAlignment(Pos.BASELINE_LEFT);
		sidebarItemSettings.setOnAction(this);
		sideBarBottumBButtons.getChildren().add(sidebarItemSettings);

	sideBar.setBottomAnchor(sideBarBottumBButtons, (double) 20);
	sideBar.getChildren().addAll(sideBarTopButtons, sideBarBottumBButtons);
	
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==sidebarItemSchedule) {
			for(Node item : sideBarTopButtons.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			for(Node item : sideBarBottumBButtons.getChildren())	
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemSchedule.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		} else if(event.getSource()==sidebarItemHomework) {
			for(Node item : sideBarTopButtons.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			for(Node item : sideBarBottumBButtons.getChildren())	
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemHomework.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		} else if(event.getSource()==sidebarItemMark) {
			for(Node item : sideBarTopButtons.getChildren()) 
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			for(Node item : sideBarBottumBButtons.getChildren())	
				item.setStyle("-fx-background-color: bbbbff; -fx-text-fill: black;");
			sidebarItemMark.setStyle("-fx-background-color: #00a; -fx-text-fill: white;");
		}
	
	}

	
	
	public static void setOpacity(double opacity) {
		root.setOpacity(opacity);
	}
 	
		
}
