package com.Dusan.Capstone;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Simulator {

	Scene simul_scene = null;
	BorderPane root = null;
	Stage primaryStage = null;
	
	Simulator_Controller my_controller = null;
	
	FXMLLoader fx_loader = null;
	
	
	public BuildingData building_data = new BuildingData();
	
	public void setStage(Stage input)
	{
		this.primaryStage = input;
	}
	
	public void StartWindow() throws Exception
	{
		this.fx_loader = new FXMLLoader(getClass().getResource("Simulator.fxml"));
		
		root = (BorderPane) this.fx_loader.load();
		
		this.simul_scene = new Scene(root); // Scene을 설정하고
		this.simul_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		my_controller = (Simulator_Controller) fx_loader.getController();
		// controller 객체를 가져온다.
		
		primaryStage.setScene(this.simul_scene);
		
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setTitle("스마트 엘리베이터 시뮬레이터 - BBD팀");
		
		
		this.InitBuilding();
	}
	
	public void InitBuilding()
	{
		
	}
	
}
