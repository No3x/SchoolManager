package com.ludevstudio.schoolmanager;

import java.util.ResourceBundle;

import org.controlsfx.control.ToggleSwitch;

import impl.org.controlsfx.skin.ToggleSwitchSkin;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateScheduleDialog extends Stage{
	ResourceBundle bundle;
	GridPane content;
	
	public CreateScheduleDialog() {
		bundle = ResourceBundle.getBundle("Bundle"); // Get the Bundle for multipe lang strings
		
		initModality(Modality.APPLICATION_MODAL);
		initOwner(MainWindow.mainWindowStage);
		initStyle(StageStyle.DECORATED);
		setTitle(bundle.getString("DialogAddSchedule.Header.Title"));
		setResizable(false);
		
		
		initUI();
		
		Scene scene = new Scene(content);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("style/addscheduledialog.css").toExternalForm());
		setScene(scene);
	}
	
	private void initUI() {
		content = new GridPane();
		content.setId("addscheduledialog");
		
		
		
		TextField tfName = new TextField(bundle.getString("DialogAddSchedule.TfHints.Name"));
		tfName.setId("name");
		GridPane.setFillWidth(tfName, true);
		content.add(tfName, 0, 0, 2, 1);
		
		TextField tfComment = new TextField(bundle.getString("DialogAddSchedule.TfHints.Comment"));
		tfComment.setId("comment");
		GridPane.setMargin(tfComment, new Insets(10, 0 , 0, 0));
		content.add(tfComment, 0, 1, 2, 1);
	
		Label labLessons = new Label(bundle.getString("DialogAddSchedule.Settings.Lessons"));
		labLessons.setId("settingsitemlabel");
		GridPane.setMargin(labLessons, new Insets(20, 0, 0, 0));
		content.add(labLessons, 0, 3);
		
		int[] combLessonsArray = new int[] {4,5,6,7,8,9,10,11,12,13,14,15,16};
		ComboBox combLessons =new ComboBox();
			for(int i=0; i<combLessonsArray.length; i++) combLessons.getItems().add(combLessonsArray[i]);
		combLessons.setId("comblessons");
		combLessons.setValue(8);
		GridPane.setMargin(combLessons, new Insets(20, 0, 0, 50));
		content.add(combLessons, 1, 3);
		
		Label lab0Houer = new Label(bundle.getString("DialogAddSchedule.Settings.Zerohouer"));
		lab0Houer.setId("settingsitemlabel");
		GridPane.setMargin(lab0Houer, new Insets(0, 0, 0, 0));
		content.add(lab0Houer, 0, 4);
		
		ToggleSwitch switch0Houer = new ToggleSwitch();
		GridPane.setMargin(switch0Houer, new Insets(0, 0, 0, 50));
		switch0Houer.setScaleX(1.1);
		switch0Houer.setScaleY(1.1);
		content.add(switch0Houer, 1, 4);
		
		
		
		
		Label labWeekend = new Label(bundle.getString("DialogAddSchedule.Settings.Weekend"));
		labWeekend.setId("settingsitemlabel");
		GridPane.setMargin(labWeekend, new Insets(0, 0, 0, 0));
		content.add(labWeekend, 0, 5);
		
		ToggleSwitch switchWeekend = new ToggleSwitch();
		GridPane.setMargin(switchWeekend, new Insets(0, 0, 0, 50));
		switchWeekend.setId("switchweekend");
		switchWeekend.setScaleX(1.1);
		switchWeekend.setScaleY(1.1);
		content.add(switchWeekend, 1, 5);
		
		
		Button btnOk = new Button(bundle.getString("DialogAddSchedule.Buttons.Ok"));
		btnOk.setId("button");
		GridPane.setMargin(btnOk, new Insets(20, 0, 0, 0));
		GridPane.setFillWidth(btnOk, true);
		content.add(btnOk, 0, 6, 2, 1);
		
	/*	Button btnCancel = new Button(bundle.getString("DialogAddSchedule.Buttons.Cancel"));
		btnCancel.setId("button");
		GridPane.setMargin(btnCancel, new Insets(20, 0, 0, 0));
		GridPane.setFillWidth(btnCancel, true);
		content.add(btnCancel, 1, 6);
		
		*/
		
	}
}
