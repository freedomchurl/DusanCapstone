package com.Dusan.Capstone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Simulator_Controller implements Initializable{

	public String up_arrow = "arrow-up.png";
	public String down_arrow = "arrow-down.png";
	
	public String people_down = "left-arrow.png";
	public String people_up = "upper_people.png";
	
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
			
			String [] floor_people = new String[12];
			int [] front_moving = new int[12];
			
			public void run()
			{
				while(true)
				{
					int leftfloor = my_data.elevator[0].current_floor;
					int rightfloor = my_data.elevator[1].current_floor;
					// ���� ���� �޾ƿ���
					
					int leftinner = my_data.elevator[0].inner_people;
					int rightinner = my_data.elevator[1].inner_people;
					
					int leftmove = my_data.elevator[0].moving;
					int rightmove = my_data.elevator[1].moving;
					
					String str_leftinner = leftinner + "/15��";
					String str_rightinner = rightinner + "/15��";
					
					this.SetFloorNum();
					// �����ʿ� - ���⼭ 1���� ���� ��������̷κ��� �޾ƿ;� �Ѵ�.
					
					this.SetFrontMoving();
					
					Platform.runLater(()->{
						// ���⼭ UI�� �����ϵ��� �Ѵ�.
						
						this.InitElevUI();
						this.SetElevUI(leftfloor, rightfloor);
						// ���������� ��� ǥ��
						
						inner_left.setText(str_leftinner);
						inner_right.setText(str_rightinner);
						// ���������� ���� �ο�
						// ���α׷����� ����
						left_bar.setProgress((double)leftinner /15);
						right_bar.setProgress((double)rightinner/15);
						
						
						this.SetFloorUI();
						// �� ���� �ο�
						
						this.InitElevArrow();
						// ���������� ȭ�� �ʱ�ȭ
						this.SetElevArrow(leftfloor, leftmove, rightfloor, rightmove);
						// ���������� ȭ�� UI ����
						this.SetUIFront();
						
						// �� ���� ����鿡 ���� ȭ��ǥ�� �����Ǹ� �ȴ�. 
					});
					
					try{UIThread.sleep(50);} catch(Exception e){}
				}
			}
			
			
			public void SetUIFront()
			{
				
					try {
						
						left_people_1.setOpacity(1);
						left_people_2.setOpacity(1);
						left_people_3.setOpacity(1);
						left_people_4.setOpacity(1);
						left_people_5.setOpacity(1);
						left_people_6.setOpacity(1);
						
						right_people_1.setOpacity(1);
						right_people_2.setOpacity(1);
						right_people_3.setOpacity(1);
						right_people_4.setOpacity(1);
						right_people_5.setOpacity(1);
						right_people_6.setOpacity(1);
						
						
						
						if(front_moving[0]==1)
							left_people_1.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[0]==-1)
							left_people_1.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_1.setOpacity(0);
						
						if(front_moving[1]==1)
							left_people_2.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[1]==-1)
							left_people_2.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_2.setOpacity(0);
						
						if(front_moving[2]==1)
							left_people_3.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[2]==-1)
							left_people_3.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_3.setOpacity(0);
						
						if(front_moving[3]==1)
							left_people_4.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[3]==-1)
							left_people_4.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_4.setOpacity(0);
						
						if(front_moving[4]==1)
							left_people_5.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[4]==-1)
							left_people_5.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_5.setOpacity(0);
						
						if(front_moving[5]==1)
							left_people_6.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[5]==-1)
							left_people_6.setImage(new Image(new FileInputStream(people_down)));
						else
							left_people_6.setOpacity(0);
						
						
						
						
						///////////
						
						if(front_moving[6]==1)
							right_people_1.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[6]==-1)
							right_people_1.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_1.setOpacity(0);
						
						if(front_moving[7]==1)
							right_people_2.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[7]==-1)
							right_people_2.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_2.setOpacity(0);
						
						if(front_moving[8]==1)
							right_people_3.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[8]==-1)
							right_people_3.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_3.setOpacity(0);
						
						if(front_moving[9]==1)
							right_people_4.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[9]==-1)
							right_people_4.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_4.setOpacity(0);
						
						if(front_moving[10]==1)
							right_people_5.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[10]==-1)
							right_people_5.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_5.setOpacity(0);
						
						if(front_moving[11]==1)
							right_people_6.setImage(new Image(new FileInputStream(people_up)));
						else if(front_moving[11]==-1)
							right_people_6.setImage(new Image(new FileInputStream(people_down)));
						else
							right_people_6.setOpacity(0);
						
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			
			public void SetFrontMoving()
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<6;j++)
					{
						this.front_moving[i*6+j] = my_data.front[i][j].moving;
					}
				}
			}
			
			public void SetElevArrow(int left, int left_moving, int right, int right_moving)
			{
				ImageView tmp_left = null;
				ImageView tmp_right = null;
				
				if(left==1)
					tmp_left = left_moving_1;
				else if(left==2)
					tmp_left = left_moving_2;
				else if(left==3)
					tmp_left = left_moving_3;
				else if(left==4)
					tmp_left = left_moving_4;
				else if(left==5)
					tmp_left = left_moving_5;
				else if(left==6)
					tmp_left = left_moving_6;
				
				
				if(right==1)
					tmp_right = right_moving_1;
				else if(right==2)
					tmp_right = right_moving_2;
				else if(right==3)
					tmp_right = right_moving_3;
				else if(right==4)
					tmp_right = right_moving_4;
				else if(right==5)
					tmp_right = right_moving_5;
				else if(right==6)
					tmp_right = right_moving_6;
				
				
				tmp_left.setOpacity(1);
				tmp_right.setOpacity(1);
				// ���̰� �ϰ�
				
				
				try {
					if(left_moving == 1)
					tmp_left.setImage(new Image(new FileInputStream(up_arrow)));
					else if(left_moving==-1) // �ϰ�
						tmp_left.setImage(new Image(new FileInputStream(down_arrow)));
					else
						tmp_left.setOpacity(0);
					
					if(right_moving == 1)
						tmp_right.setImage(new Image(new FileInputStream(up_arrow)));
						else if(right_moving==-1) // �ϰ�
							tmp_right.setImage(new Image(new FileInputStream(down_arrow)));
						else
							tmp_right.setOpacity(0);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			public void InitElevArrow()
			{
				left_moving_1.setOpacity(0);
				left_moving_2.setOpacity(0);
				left_moving_3.setOpacity(0);
				left_moving_4.setOpacity(0);
				left_moving_5.setOpacity(0);
				left_moving_6.setOpacity(0);
				
				right_moving_1.setOpacity(0);
				right_moving_2.setOpacity(0);
				right_moving_3.setOpacity(0);
				right_moving_4.setOpacity(0);
				right_moving_5.setOpacity(0);
				right_moving_6.setOpacity(0);
			}
			
			public void SetFloorNum()
			{
				floor_people[0] = new String(my_data.front[0][0].people_num + "��");
				floor_people[1] = new String(my_data.front[0][1].people_num + "��");
				floor_people[2] = new String(my_data.front[0][2].people_num + "��");
				floor_people[3] = new String(my_data.front[0][3].people_num + "��");
				floor_people[4] = new String(my_data.front[0][4].people_num + "��");
				floor_people[5] = new String(my_data.front[0][5].people_num + "��");
				
				floor_people[6] = new String(my_data.front[1][0].people_num + "��");
				floor_people[7] = new String(my_data.front[1][1].people_num + "��");
				floor_people[8] = new String(my_data.front[1][2].people_num + "��");
				floor_people[9] = new String(my_data.front[1][3].people_num + "��");
				floor_people[10] = new String(my_data.front[1][4].people_num + "��");
				floor_people[11] = new String(my_data.front[1][5].people_num + "��");
				
				
				
				
			}
			public void SetFloorUI()
			{
				left_1.setText(floor_people[0]);
				left_2.setText(floor_people[1]);
				left_3.setText(floor_people[2]);
				left_4.setText(floor_people[3]);
				left_5.setText(floor_people[4]);
				left_6.setText(floor_people[5]);
				
				right_1.setText(floor_people[6]);
				right_2.setText(floor_people[7]);
				right_3.setText(floor_people[8]);
				right_4.setText(floor_people[9]);
				right_5.setText(floor_people[10]);
				right_6.setText(floor_people[11]);
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
