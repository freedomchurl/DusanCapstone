package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Simulator_Controller implements Initializable{

	public String up_arrow = "../../../../resource/arrow-up.png";
	public String down_arrow = "../../../../resource/arrow-down.png";
	
	public Thread UIThread = null;
	
	BuildingData my_data = null;
	
	
	@FXML ImageView play;
	@FXML ImageView pause;
	// ��ư�� ���ҵ� �ؾ��Ѵ�.
	boolean isPlaying = true;
	
	@FXML ImageView left_moving_1,left_moving_2,left_moving_3,left_moving_4,left_moving_5,left_moving_6;
	@FXML ImageView left_people_1,left_people_2,left_people_3,left_people_4,left_people_5,left_people_6;
	// ������������ �����Ӱ� ����� ������ ���� ���������� ��������.
	@FXML ImageView right_moving_1,right_moving_2,right_moving_3,right_moving_4,right_moving_5,right_moving_6;
	@FXML ImageView right_people_1,right_people_2,right_people_3,right_people_4,right_people_5,right_people_6;
	
	@FXML ImageView left_elev_1,left_elev_2,left_elev_3,left_elev_4,left_elev_5,left_elev_6;
	@FXML ImageView right_elev_1,right_elev_2,right_elev_3,right_elev_4,right_elev_5,right_elev_6;
	// ���������� ����� ��Ÿ��.
	// ImageView���� ������
	
	@FXML Label inner_left, inner_right;
	// ���������� ���� �ο�ǥ��
	
	@FXML Label left_1,left_2,left_3,left_4,left_5,left_6;
	@FXML Label right_1,right_2,right_3,right_4,right_5,right_6;
	// �� ���� ����� ���� ��Ÿ����.
	
	@FXML ProgressBar left_bar, right_bar;
	// ���� ������� �ο��� á������ �����ش�.
	
	@FXML Slider speed;
	// �ùķ����� �ӵ�
	public double simul_speed = 100.0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		play.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// play ��ư�� ���������� �̺�Ʈ
				isPlaying = true;
				System.out.println("����Ѵ�");
			}			
		});
		pause.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				// pause ��ư�� ���������� �̺�Ʈ
				isPlaying = false;
				System.out.println("�����Ѵ�");
			}
		});
		
		speed.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// �����̴����� ���� �����´�.
				
				simul_speed = speed.getValue();
				
				System.out.println(simul_speed);
				
			}
			
		});
	}
	
	public void StartUIUpdate()
	{
		UIThread = new Thread()
		{
			// UI ������Ʈ �����带 ������.
			// my_data���� �����͸� �����ͼ� UI ���游�� �Ѵ�.
			public void run()
			{
				int leftfloor = my_data.elevator[0].current_floor;
				int rightfloor = my_data.elevator[1].current_floor;
				// ���� ���� �޾ƿ���
				
				int leftinner = my_data.elevator[0].inner_people;
				int rightinner = my_data.elevator[1].inner_people;
				
				String str_leftinner = leftinner + "/15";
				String str_rightinner = rightinner + "/15";
				
				Platform.runLater(()->{
					// ���⼭ UI�� �����ϵ��� �Ѵ�.
					
					this.InitElevUI();
					this.SetElevUI(leftfloor, rightfloor);
					// ���������� ��� ǥ��
					
					inner_left.setText(str_leftinner);
					inner_right.setText(str_rightinner);
				});
				
				try{UIThread.sleep(50);} catch(Exception e){}
			}
			
			public void SetElevUI(int left, int right)
			{
				if(left==1)
					left_elev_1.setOpacity(1);
				else if(left==2)
					left_elev_2.setOpacity(1);
				else if(left==3)
					left_elev_3.setOpacity(1);
				else if(left==4)
					left_elev_4.setOpacity(1);
				else if(left==5)
					left_elev_5.setOpacity(1);
				else if(left==6)
					left_elev_6.setOpacity(1);
				
				if(right==1)
					right_elev_1.setOpacity(1);
				else if(right==2)
					right_elev_2.setOpacity(1);
				else if(right==3)
					right_elev_3.setOpacity(1);
				else if(right==4)
					right_elev_4.setOpacity(1);
				else if(right==5)
					right_elev_5.setOpacity(1);
				else if(right==6)
					right_elev_6.setOpacity(1);
			}

			public void InitElevUI()
			{
				left_elev_1.setOpacity(0);
				left_elev_2.setOpacity(0);
				left_elev_3.setOpacity(0);
				left_elev_4.setOpacity(0);
				left_elev_5.setOpacity(0);
				left_elev_6.setOpacity(0);
				
				right_elev_1.setOpacity(0);
				right_elev_2.setOpacity(0);
				right_elev_3.setOpacity(0);
				right_elev_4.setOpacity(0);
				right_elev_5.setOpacity(0);
				right_elev_6.setOpacity(0);
			}
		};
		
		UIThread.start(); 
		// �����Ѵ�. 
	}
	
	public void setData(BuildingData input)
	{
		this.my_data = input;
	}

}
