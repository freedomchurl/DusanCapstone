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
	
	
	public BuildingData building_data = new BuildingData(2,"����",1,6);
	
	public void setStage(Stage input)
	{
		this.primaryStage = input;
	}
	
	public void StartWindow() throws Exception
	{
		this.fx_loader = new FXMLLoader(getClass().getResource("Simulator.fxml"));
		
		root = (BorderPane) this.fx_loader.load();
		
		this.simul_scene = new Scene(root); // Scene�� �����ϰ�
		this.simul_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		my_controller = (Simulator_Controller) fx_loader.getController();
		// controller ��ü�� �����´�.
		
		primaryStage.setScene(this.simul_scene);
		
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setTitle("����Ʈ ���������� �ùķ����� - BBD��");
		
		
		this.InitBuilding();
		my_controller.setData(this.building_data);
		my_controller.StartUIUpdate();
		this.building_data.SimulStart();
	}
	
	public void InitBuilding()
	{
		// ���� ������ �ʱ�ȭ�ؾ��Ѵ�.
		
		// �ʱ�ȭ �ؾ��� �κ���, low,high��
		this.building_data.low_floor = 1;
		this.building_data.high_floor = 1;
		
		// �����̸�
		this.building_data.building_name = new String("BBD ����");
		
		// ���������� ����
		this.building_data.num_elev = 2;
		
		// ���������� �Ҵ�
		this.building_data.elevator = new Elevator[2];
		
		// front �ʱ�ȭ
		//this.building_data.front = new People[2][6];
		// ���������Ͱ� 2��, 6�� ��
		
		for(int i=0;i<2;i++)
		{
			this.building_data.elevator[i] = new Elevator(1,6);
			// 2���� ���������� ��� 1~6������ �����Ѵ�.
			
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
