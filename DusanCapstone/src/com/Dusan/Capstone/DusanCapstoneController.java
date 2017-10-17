package com.Dusan.Capstone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DusanCapstoneController implements Initializable{

	Scene ele208 = null;
	BorderPane root = null;
	Stage secondStage = new Stage();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Simulator208.fxml"));
			ele208 = new Scene(root);
			ele208.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			secondStage.setScene(ele208);
			secondStage.show();
			secondStage.setResizable(false);
			secondStage.setTitle("208관 엘리베이터 시뮬레이터");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
