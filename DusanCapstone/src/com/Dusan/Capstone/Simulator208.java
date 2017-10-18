package com.Dusan.Capstone;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Simulator208 {

	Scene ele208 = null;
	BorderPane root = null;
	Stage secondStage = new Stage();
	
	public BuildingData data_208 = new BuildingData(1,"208��",1,6);
	// ������ �־��־���Ѵ�. ���������� ���� 1��, �ǹ��̸� 208��, �ǹ� ������ 1��, �ǹ� �ְ��� 6��
	
	Simulator208_Controller my208_controller = null;
	// Controller ��ü�� �������� ����. 
	
	
	FXMLLoader loader = null;
	
	public void StartWindow()
	{
		try {
			
			loader = new FXMLLoader(getClass().getResource("Simulator208.fxml"));
			
			root = (BorderPane) loader.load(); //FXMLLoader.load(getClass().getResource("Simulator208.fxml"));
			ele208 = new Scene(root);
			ele208.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			my208_controller = (Simulator208_Controller) loader.getController();
 			
			
			secondStage.setScene(ele208);
			
			//secondStage.setUserData(data_208);
			// 208 data�� �Ѱ��ش�.
			secondStage.setX(1310);
			secondStage.setY(10);
			secondStage.show();
			secondStage.setResizable(false);
			secondStage.setTitle("208�� ���������� �ùķ�����");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("�Ϸ�");
		
		//my208_controller.getScene();
		my208_controller.getScene();
		my208_controller.setData(this.data_208);
		InitElevator();
		my208_controller.start();
		// �̷������� �ٲ㼭 �����Ѵ�. 
		
		
	}
	
	public void InitElevator()
	{
		System.out.println("�ʱ�ȭ");
		
		this.data_208.elevator[0] = new Elevator(this.data_208.low_floor, this.data_208.high_floor); 
		//208���� ���������ʹ� 1���ۿ� ����. 1������, 6������ ����
	}
}
