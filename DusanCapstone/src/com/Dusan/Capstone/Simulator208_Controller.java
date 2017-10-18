package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class Simulator208_Controller implements Initializable{

	BuildingData data_208 = null;
	/*
	 * 여기서 208관에 대한 데이터를 얻고나서, 어디서 이 값들을 수정해야할까?
	 * 저쪽 Simulator208쪽에서 쓰레드를 통해서, 값들을 계속바꾸고.
	 * 여기서는 쓰레드를 통해서 1초마다 계속 감시하고, 그걸 GUI로 전달해야한다
	 */
	
	@FXML
	ImageView homebutton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("이건별개");
		
		// initialize는 load과정에 호출되는 메소드라서, Scene등에 접근이 불가능하다. 
		
	}
	
	public void getScene()
	{
		data_208 = (BuildingData) homebutton.getScene().getUserData();
	}

}
