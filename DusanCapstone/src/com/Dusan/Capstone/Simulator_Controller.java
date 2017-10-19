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
	// 버튼의 역할도 해야한다.
	boolean isPlaying = true;
	
	@FXML ImageView left_moving_1,left_moving_2,left_moving_3,left_moving_4,left_moving_5,left_moving_6;
	@FXML ImageView left_people_1,left_people_2,left_people_3,left_people_4,left_people_5,left_people_6;
	// 엘리베이터의 움직임과 사람의 방향을 왼쪽 오른쪽으로 나누었다.
	@FXML ImageView right_moving_1,right_moving_2,right_moving_3,right_moving_4,right_moving_5,right_moving_6;
	@FXML ImageView right_people_1,right_people_2,right_people_3,right_people_4,right_people_5,right_people_6;
	
	@FXML ImageView left_elev_1,left_elev_2,left_elev_3,left_elev_4,left_elev_5,left_elev_6;
	@FXML ImageView right_elev_1,right_elev_2,right_elev_3,right_elev_4,right_elev_5,right_elev_6;
	// 엘리베이터 모양을 나타냄.
	// ImageView들을 가져옴
	
	@FXML Label inner_left, inner_right;
	// 엘리베이터 내의 인원표시
	
	@FXML Label left_1,left_2,left_3,left_4,left_5,left_6;
	@FXML Label right_1,right_2,right_3,right_4,right_5,right_6;
	// 각 층의 사람의 수를 나타낸다.
	
	@FXML ProgressBar left_bar, right_bar;
	// 각각 어느정도 인원이 찼는지를 보여준다.
	
	@FXML Slider speed;
	// 시뮬레이터 속도
	public double simul_speed = 100.0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		play.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// play 버튼을 눌렀을때의 이벤트
				isPlaying = true;
				System.out.println("재생한다");
			}			
		});
		pause.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				// pause 버튼을 눌렀을떄의 이벤트
				isPlaying = false;
				System.out.println("정지한다");
			}
		});
		
		speed.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// 슬라이더에서 값을 가져온다.
				
				simul_speed = speed.getValue();
				
				System.out.println(simul_speed);
				
			}
			
		});
	}
	
	public void StartUIUpdate()
	{
		UIThread = new Thread()
		{
			// UI 업데이트 쓰레드를 돌린다.
			// my_data에서 데이터를 가져와서 UI 변경만을 한다.
			public void run()
			{
				int leftfloor = my_data.elevator[0].current_floor;
				int rightfloor = my_data.elevator[1].current_floor;
				// 현재 층을 받아오고
				
				int leftinner = my_data.elevator[0].inner_people;
				int rightinner = my_data.elevator[1].inner_people;
				
				String str_leftinner = leftinner + "/15";
				String str_rightinner = rightinner + "/15";
				
				Platform.runLater(()->{
					// 여기서 UI를 변경하도록 한다.
					
					this.InitElevUI();
					this.SetElevUI(leftfloor, rightfloor);
					// 엘리베이터 모양 표시
					
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
		// 시작한다. 
	}
	
	public void setData(BuildingData input)
	{
		this.my_data = input;
	}

}
