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
	
	public BuildingData data_208 = new BuildingData(1,"208관",1,6);
	// 정보를 넣어주어야한다. 엘리베이터 개수 1대, 건물이름 208관, 건물 최저층 1층, 건물 최고층 6층
	
	Simulator208_Controller my208_controller = null;
	// Controller 객체를 가져오기 위함. 
	
	
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
			// 208 data를 넘겨준다.
			secondStage.setX(1310);
			secondStage.setY(10);
			secondStage.show();
			secondStage.setResizable(false);
			secondStage.setTitle("208관 엘리베이터 시뮬레이터");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("완료");
		
		//my208_controller.getScene();
		my208_controller.getScene();
		my208_controller.setData(this.data_208);
		InitElevator();
		my208_controller.start();
		// 이런식으로 바꿔서 얻어야한다. 
		
		
	}
	
	public void InitElevator()
	{
		System.out.println("초기화");
		
		this.data_208.elevator[0] = new Elevator(this.data_208.low_floor, this.data_208.high_floor); 
		//208관에 엘리베이터는 1개밖에 없다. 1층부터, 6층까지 운행
	}
}
