package com.Dusan.Capstone;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class Simulator_Controller implements Initializable{

	public String up_arrow = "../../../../resource/arrow-up.png";
	public String down_arrow = "../../../../resource/arrow-down.png";
	
	
	BuildingData my_data = null;
	
	
	@FXML ImageView play;
	@FXML ImageView pause;
	// 버튼의 역할도 해야한다.
	
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void setData(BuildingData input)
	{
		this.my_data = input;
	}

}
