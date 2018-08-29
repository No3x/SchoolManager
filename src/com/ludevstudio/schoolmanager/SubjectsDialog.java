package com.ludevstudio.schoolmanager;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SubjectsDialog extends Stage  implements EventHandler<ActionEvent> {
	DataBaseControler controller;
	ResourceBundle bundle;
	
	ComboBox<Subject> combSubjects;
	TextField tfName, tfTeacher, tfRoom;
	ColorPicker btnColor;
	Button btnOk, btnCancel;
	
	String subName = "Geschichte";
	ArrayList<Subject> subjects;
	Subject selectedSubject;
	
	public SubjectsDialog() {
		
		
		initUI();
	}
	
	public SubjectsDialog(String name) {
		this.subName = name;
		
		initUI();
	}
	
	
	private void initUI() {
		bundle =ResourceBundle.getBundle("Bundle");
		controller =new DataBaseControler();
		subjects = new ArrayList<>();
		loadSubjects();
		
		setTitle(bundle.getString("DialogAddandEditSubjects.Heuder.Title"));
		initModality(Modality.APPLICATION_MODAL);
		initOwner(MainWindow.getMainWindowStage());
		setResizable(false);
	
		
		GridPane layout =new GridPane();
		
		tfName =new TextField();
		GridPane.setMargin(tfName, new Insets(20, 10, 0, 0));
		tfName.setId("textfield");
		layout.add(tfName, 0, 1);
		
		tfTeacher =new TextField();
		GridPane.setMargin(tfTeacher, new Insets(20, 0, 0, 10));
		tfTeacher.setId("textfield");
		layout.add(tfTeacher, 1, 1);
		
		
		
		HBox roomAndColorContainer =new HBox(20);
		layout.add(roomAndColorContainer, 0, 2);
		GridPane.setMargin(roomAndColorContainer, new Insets(20, 10, 0, 0));
		
		tfRoom =new TextField();
		tfRoom.setId("textfield");
		roomAndColorContainer.getChildren().add(tfRoom);
		
		btnColor =new ColorPicker(Color.BLUE);
		btnColor.setOnAction(this);
		btnColor.setId("colorbutton");
		roomAndColorContainer.getChildren().add(btnColor);
		

		
 		
		combSubjects =new ComboBox<Subject>();
 		combSubjects.setId("combsubjects");
		
 		
 		for(Subject subject : subjects) {
 			combSubjects.getItems().add(subject);
 		}
 		combSubjects.getItems().add(new Subject("0", bundle.getString("DialogAddandEditSubjects.Content.Comb.New"), "", "", new Color(1, 1, 0.4, 1)));
 		
 		combSubjects.setOnAction(this);
 	if(subName!=null && subjects !=null) {
 		combSubjects.setValue(subjects.get(0));
 		selectedSubject = combSubjects.getValue();
		
		tfName.setText(combSubjects.getValue().getName());
		tfTeacher.setText(combSubjects.getValue().getTeacher());
		tfRoom.setText(combSubjects.getValue().getRoom());
		btnColor.setValue(combSubjects.getValue().getColor());
 	} else {
 		combSubjects.setValue(combSubjects.getItems().get(combSubjects.getItems().size()-1));
 	}
 		
 		layout.add(combSubjects, 0, 0, 2, 1);
		
		
		
		HBox btnContainer =new HBox(20);
		btnContainer.setStyle("-fx-max-width: infinity;");
		btnContainer.setAlignment(Pos.CENTER);
		layout.add(btnContainer, 1, 2);
		GridPane.setMargin(btnContainer, new Insets(20, 10, 0, 0));
		
		btnCancel =new Button(bundle.getString("DialogAddandEditSubjects.Buttons.Cancel"));
		btnCancel.setId("actionbutton");
		btnCancel.setOnAction(this);
		btnOk =new Button(bundle.getString("DialogAddandEditSubjects.Buttons.Ok"));
		btnOk.setId("actionbutton");
		btnOk.setOnAction(this);
		btnContainer.getChildren().addAll(btnCancel, btnOk);
		
		
		Scene scene =new Scene(layout);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("style/subjectsdialog.css").toExternalForm());
		setScene(scene);
		show();
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==combSubjects) {
			selectedSubject = combSubjects.getValue();
			
			tfName.setText(combSubjects.getValue().getName());
			tfTeacher.setText(combSubjects.getValue().getTeacher());
			tfRoom.setText((combSubjects.getValue().getRoom()));
			btnColor.setValue(combSubjects.getValue().getColor());
				
		} else if(event.getSource()==btnColor) {
			
		} else if(event.getSource()==btnOk) {
			save();
		}
	}
	
	
	
	
	
	
	
private String convertFxColorToHex(Color color) {
	 return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );

}
		// Check if something has been changed
	private boolean hasChanged() {
		Object[] oldInfos = new Object[] {
			combSubjects.getValue().getName(),
			combSubjects.getValue().getTeacher(),
			combSubjects.getValue().getRoom(),
			combSubjects.getValue().getColor()
		};
		
		Object[] newInfos = new Object[] {
			tfName.getText(),
			tfTeacher.getText(),
			tfRoom.getText(),
			btnColor.getValue()
		};
		
		
		
		for(int i=0; i<newInfos.length; i++) {
			if(!oldInfos[i].equals(newInfos[i])) {
				return true;
			}
		}
		
		return false;
	}

	private void save() {
		
			
				// Check if something has changed
				if(hasChanged()) { 
					// Show Message that subject is existing
					Alert msg =new Alert(AlertType.CONFIRMATION);
					msg.setHeaderText(bundle.getString("DialogAddandEditSubjects.Content.Message.Overwrite.Title"));
					msg.setContentText(bundle.getString("DialogAddandEditSubjects.Content.Message.Overwrite.Text"));
					msg.getDialogPane().setStyle("-fx-border-color:black;" + 
						"  -fx-border-width:2.0px;" + 
						" -fx-font-size: 2em;"); 
					msg.initStyle(StageStyle.UNDECORATED);
					msg.showAndWait().ifPresent(result -> {
					  if(result==ButtonType.OK) {
						  controller.createNewSubject(tfName.getText(), tfTeacher.getText(), tfRoom.getText(), convertFxColorToHex(btnColor.getValue()));
						  this.hide();
					  } else {
						  this.hide();
					  }
					});
					} else {
					
					
					this.hide();
			}
			
			
			
		}
		
	
	
	private void loadSubjects() {
		ArrayList<String> subjectIDs = controller.getSubjectList();
		
		for(String id : subjectIDs) {
			ArrayList<String> subjectInfos = controller.loadSubject(id);
			Subject subject = new Subject(subjectInfos.get(0), 
					subjectInfos.get(1),
					subjectInfos.get(2),
					subjectInfos.get(3),
					Color.web(subjectInfos.get(4)));
					
				subjects.add(subject);
		}
	}
	
	
	public Subject getSelectedSubject() {
		return selectedSubject;
	}
	
	
	}
	
