package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class Simulator208_Controller implements Initializable{

	BuildingData data_208 = null;
	/*
	 * ���⼭ 208���� ���� �����͸� �����, ��� �� ������ �����ؾ��ұ�?
	 * ���� Simulator208�ʿ��� �����带 ���ؼ�, ������ ��ӹٲٰ�.
	 * ���⼭�� �����带 ���ؼ� 1�ʸ��� ��� �����ϰ�, �װ� GUI�� �����ؾ��Ѵ�
	 */
	
	@FXML
	ImageView homebutton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("�̰Ǻ���");
		
		// initialize�� load������ ȣ��Ǵ� �޼ҵ��, Scene� ������ �Ұ����ϴ�. 
		
	}
	
	public void getScene()
	{
		data_208 = (BuildingData) homebutton.getScene().getUserData();
	}

}
