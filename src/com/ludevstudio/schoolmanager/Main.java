package com.ludevstudio.schoolmanager;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;

public class Main {
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("de"));
		
		Application.launch(MainWindow.class, args);
	}
}
