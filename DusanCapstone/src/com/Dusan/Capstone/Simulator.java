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
	
	
	public BuildingData building_data = new BuildingData(2,"빌딩",1,6);
	
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
		my_controller.setData(this.building_data);
		my_controller.StartUIUpdate();
		this.building_data.SimulStart();
	}
	
	public void InitBuilding()
	{
		// 빌딩 정보를 초기화해야한다.
		
		// 초기화 해야할 부분은, low,high층
		this.building_data.low_floor = 1;
		this.building_data.high_floor = 1;
		
		// 빌딩이름
		this.building_data.building_name = new String("BBD 빌딩");
		
		// 엘리베이터 개수
		this.building_data.num_elev = 2;
		
		// 엘리베이터 할당
		this.building_data.elevator = new Elevator[2];
		
		// front 초기화
		//this.building_data.front = new People[2][6];
		// 엘리베이터가 2개, 6개 층
		
		for(int i=0;i<2;i++)
		{
			this.building_data.elevator[i] = new Elevator(1,6);
			// 2개의 엘리베이터 모두 1~6층까지 운행한다.
			
			for(int j=0;j<6;j++)
			{
				//this.building_data.front[i][j] = new People();
			}
		}
		
	}
	
	public void StartSimulator()
	{
		
	}
}
