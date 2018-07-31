package com.ludevstudio.schoolmanager;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TitleBar extends AnchorPane {
	HBox buttons;
	Button exit;
	
	public TitleBar() {
		setId("titlebar");
		setStyle("-fx-background: #00a;");
		
		
		setRightAnchor(buttons(), 5d);
		getChildren().addAll(buttons());
	}
	
	
	private HBox buttons () {
		 buttons = new HBox(10);
		 buttons.setId("titlebarbuttons");
		 exit = new Button("X");
		exit.setId("titlebarbuttonsexit");
		
		buttons.getChildren().add(exit);
		return buttons;
	}
	
}
