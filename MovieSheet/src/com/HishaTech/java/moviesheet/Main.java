package com.HishaTech.java.moviesheet;

import com.HishaTech.java.moviesheet.gui.GUIConstants;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource(GUIConstants.fxml_Main));
			Scene scene = new Scene(root, GUIConstants.program_MinWidth,
					GUIConstants.program_MinHeight);
			scene.getStylesheets().add(
					getClass().getResource(GUIConstants.css_Main)
							.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage
					.getIcons()
					.add(new Image(Main.class
							.getResourceAsStream(AppConstants.program_AppIcon)));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
