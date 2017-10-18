package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Simulator208_Controller implements Initializable{

	BuildingData data_208 = null;
	/*
	 * ���⼭ 208���� ���� �����͸� �����, ��� �� ������ �����ؾ��ұ�?
	 * ���� Simulator208�ʿ��� �����带 ���ؼ�, ������ ��ӹٲٰ�.
	 * ���⼭�� �����带 ���ؼ� 1�ʸ��� ��� �����ϰ�, �װ� GUI�� �����ؾ��Ѵ�
	 */
	@FXML
	Label state_floor;
	
	@FXML
	ImageView homebutton;
	
	int i = 1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("�̰Ǻ���");
		
		// initialize�� load������ ȣ��Ǵ� �޼ҵ��, Scene� ������ �Ұ����ϴ�. 
		homebutton.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Ŭ��");
			}
			
		});
	}
	
	public void getScene()
	{
		data_208 = (BuildingData) homebutton.getScene().getUserData();
	}
	
	public void setData(BuildingData input)
	{
		this.data_208 = input;
	}
	
	public void start()
	{
		
		Thread thread = new Thread()
		{
			String str = null;
			public void run()
			{
				while(true)
				{
					
					str = String.valueOf(data_208.elevator[0].max_weigth);
				
					
					Platform.runLater(()->{
						state_floor.setText(str);
					});
					
					data_208.elevator[0].max_weigth++;
					
					try{Thread.sleep(1000);} catch (Exception e){}
				}
			}
		};
		
		thread.setDaemon(true);
		thread.start();
	}

}
