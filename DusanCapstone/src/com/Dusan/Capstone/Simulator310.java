package com.Dusan.Capstone;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Simulator310 {

	
	Scene ele310 = null;
	BorderPane root = null;
	Stage primaryStage = null;
	
	public BuildingData data_310 = new BuildingData();
	
	public void setStage(Stage input)
	{
		this.primaryStage = input;
	}
	
	
	public void StartWindow() throws IOException
	{
		root = (BorderPane)FXMLLoader.load(getClass().getResource("DusanCapstone.fxml"));
		ele310 = new Scene(root);
		ele310.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(ele310);
		primaryStage.setX(10);
		primaryStage.setY(10);
		primaryStage.setUserData(data_310);
		// data_310 객체를 그대로 넘겨준다.
		
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
}
