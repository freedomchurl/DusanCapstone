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
	
	
	Thread peopleThread = null;
	Thread elevThread = null;
	
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
		this.SimulStart();
		my208_controller.start();
		
		// 이런식으로 바꿔서 얻어야한다. 
		
		
	}
	
	public void InitElevator()
	{
		System.out.println("초기화");
		
		this.data_208.elevator[0] = new Elevator(this.data_208.low_floor, this.data_208.high_floor); 
		//208관에 엘리베이터는 1개밖에 없다. 1층부터, 6층까지 운행
	}
	
	public void SimulStart()
	{
		System.out.println("ddd");
		// 사람 수를 체크하기 위함
		peopleThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					for(int i=0;i<6;i++)
					{
						
						if(data_208.front[0][i].check==false) // 만들어진데이터가 없다면
						{
							data_208.front[0][i].setPeople(15);
							//만들도록 한다. 
							
							System.out.println(i+1 + "층에 " + data_208.front[0][i].people_num + "명이 생겼다");

						}
						
						// 랜덤시간을 기다리도록 한다. 0~1초
						
						int randTime = (int)(Math.random() * 2);
						
						try
						{
							Thread.sleep(randTime*100);
						}
						catch(Exception e)
						{}
					}
					
					try
					{
						Thread.sleep(4000);
					}
					catch(Exception e)
					{}
					
				}
			}
		
		};
		
	
		peopleThread.start();
		
		// 여기는 엘리베이터가 1개라서 이렇게 두지만, 실제로는 엘리베이터마다 쓰레드를 둬야한다.
		elevThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					System.out.println("엘리베이터 상태 : moving = " + data_208.elevator[0].moving + " 사람 수 = " + data_208.elevator[0].inner_people + " 현재 층 = " + data_208.elevator[0].current_floor);
					
					if(data_208.elevator[0].isEmpty() == true)
					{
						boolean isPeople = false;
						// 엘리베이터에서 가장 가까운, 사람이 기다리는 곳으로 가야한다. 가서 태우는것까지
						
						int current_floor = data_208.elevator[0].current_floor;
						
						int upper = current_floor, low = current_floor;
						
						while(isPeople==false)
						{
							if(data_208.front[0][upper-1].people_num!=0)
							{
								
								if(data_208.front[0][upper-1].moving == true)
								{
									data_208.elevator[0].moving = 1; // 상승
								}
								else
								{
									data_208.elevator[0].moving = -1; // 하강
								}
								data_208.elevator[0].current_floor = upper; // 그 층으로 이동하고
								
								try
								{
									Thread.sleep(2000); // 2초 쉬고
								}
								catch(Exception e)
								{}
								
								data_208.elevator[0].inner_people = data_208.front[0][upper-1].people_num; // 사람을 태운다.
								
								if(data_208.elevator[0].inner_people !=0)
									isPeople = true;
								
							}
							else if(data_208.front[0][low-1].people_num!=0)
							{
								
								if(data_208.front[0][low-1].moving == true)
								{
									data_208.elevator[0].moving = 1; // 상승
								}
								else
								{
									data_208.elevator[0].moving = -1; // 하강
								}
								data_208.elevator[0].current_floor = low; // 그 층으로 이동하고
								
								try
								{
									Thread.sleep(2000); // 2초 쉬고
								}
								catch(Exception e)
								{}
								
								data_208.elevator[0].inner_people = data_208.front[0][low-1].people_num; // 사람을 태운다.
								
								if(data_208.elevator[0].inner_people !=0)
									isPeople = true;
							}
							else
							{
								low--;
								upper++;
								
								if(low<1)
									low = 1;
								
								if(upper > 6)
									upper = 6;
							}
						}
					}
					else
					{
						// 움직이고, 사람의 방향이 나랑 맞는지 확인하고, 내리고 , 태우고 업데이트까지
						// 도착한 층이 1층이거나 6층이면, 다 내리게 하면된다. 그리고 초기화
						// 움직여야한다. 
						
						if(data_208.elevator[0].moving==1) // 상승
						{
							data_208.elevator[0].current_floor++; // 한층 올라가고
							
							try
							{
								Thread.sleep(1500);
							}
							catch(Exception e)
							{}
							
							if(data_208.elevator[0].current_floor==6)
							{
								data_208.elevator[0].moving=0;
								data_208.elevator[0].inner_people = 0;
								data_208.elevator[0].current_wiegth = 0;
							}
							else
							{
								// 그렇지 않다면
								int removePeople = (int) (Math.random() * (data_208.elevator[0].inner_people+1));
								
								data_208.elevator[0].inner_people -= removePeople; // 그만큼 내리고
								
								if(data_208.elevator[0].isEmpty()==true)
								{
									// 사람이 비어있다면
									data_208.elevator[0].moving=0;
									data_208.elevator[0].inner_people = 0;
									data_208.elevator[0].current_wiegth = 0;
								}
								else
								{
									// 앞에 기다리는 사람이 엘리베이터 방향과 같은지 확인한다
									if(data_208.front[0][data_208.elevator[0].current_floor-1].moving == true)
									{
										if(data_208.front[0][data_208.elevator[0].current_floor-1].people_num > (15-data_208.elevator[0].inner_people))
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num-=(15-data_208.elevator[0].inner_people);
										}
										else
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num = 0;
											
											// 다 태울수 있다면 0으로 그리고 check = false로
											data_208.front[0][data_208.elevator[0].current_floor-1].check = false;
										}
									}
								}
							}
						}
						else if(data_208.elevator[0].moving==-1) // 상승
						{
							data_208.elevator[0].current_floor--; // 한층 올라가고
							
							try
							{
								Thread.sleep(1500);
							}
							catch(Exception e)
							{}
							
							if(data_208.elevator[0].current_floor==1)
							{
								data_208.elevator[0].moving=0;
								data_208.elevator[0].inner_people = 0;
								data_208.elevator[0].current_wiegth = 0;
							}
							else
							{
								// 그렇지 않다면
								int removePeople = (int) (Math.random() * (data_208.elevator[0].inner_people+1));
								
								data_208.elevator[0].inner_people -= removePeople; // 그만큼 내리고
								
								if(data_208.elevator[0].isEmpty()==true)
								{
									// 사람이 비어있다면
									data_208.elevator[0].moving=0;
									data_208.elevator[0].inner_people = 0;
									data_208.elevator[0].current_wiegth = 0;
								}
								else
								{
									// 앞에 기다리는 사람이 엘리베이터 방향과 같은지 확인한다
									if(data_208.front[0][data_208.elevator[0].current_floor-1].moving == false)
									{
										if(data_208.front[0][data_208.elevator[0].current_floor-1].people_num > (15-data_208.elevator[0].inner_people))
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num-=(15-data_208.elevator[0].inner_people);
										}
										else
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num = 0;
											
											// 다 태울수 있다면 0으로 그리고 check = false로
											data_208.front[0][data_208.elevator[0].current_floor-1].check = false;
										}
									}
								}
							}
						}
						
					}
				}
			}
		};
		
		
		elevThread.start();
	}
}
