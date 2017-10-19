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
	
	public BuildingData data_208 = new BuildingData(1,"208��",1,6);
	// ������ �־��־���Ѵ�. ���������� ���� 1��, �ǹ��̸� 208��, �ǹ� ������ 1��, �ǹ� �ְ��� 6��
	
	Simulator208_Controller my208_controller = null;
	// Controller ��ü�� �������� ����. 
	
	
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
			// 208 data�� �Ѱ��ش�.
			secondStage.setX(1310);
			secondStage.setY(10);
			secondStage.show();
			secondStage.setResizable(false);
			secondStage.setTitle("208�� ���������� �ùķ�����");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("�Ϸ�");
		
		//my208_controller.getScene();
		my208_controller.getScene();
		my208_controller.setData(this.data_208);
		InitElevator();
		this.SimulStart();
		my208_controller.start();
		
		// �̷������� �ٲ㼭 �����Ѵ�. 
		
		
	}
	
	public void InitElevator()
	{
		System.out.println("�ʱ�ȭ");
		
		this.data_208.elevator[0] = new Elevator(this.data_208.low_floor, this.data_208.high_floor); 
		//208���� ���������ʹ� 1���ۿ� ����. 1������, 6������ ����
	}
	
	public void SimulStart()
	{
		System.out.println("ddd");
		// ��� ���� üũ�ϱ� ����
		peopleThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					for(int i=0;i<6;i++)
					{
						
						if(data_208.front[0][i].check==false) // ������������Ͱ� ���ٸ�
						{
							data_208.front[0][i].setPeople(15);
							//���鵵�� �Ѵ�. 
							
							System.out.println(i+1 + "���� " + data_208.front[0][i].people_num + "���� �����");

						}
						
						// �����ð��� ��ٸ����� �Ѵ�. 0~1��
						
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
		
		// ����� ���������Ͱ� 1���� �̷��� ������, �����δ� ���������͸��� �����带 �־��Ѵ�.
		elevThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					System.out.println("���������� ���� : moving = " + data_208.elevator[0].moving + " ��� �� = " + data_208.elevator[0].inner_people + " ���� �� = " + data_208.elevator[0].current_floor);
					
					if(data_208.elevator[0].isEmpty() == true)
					{
						boolean isPeople = false;
						// ���������Ϳ��� ���� �����, ����� ��ٸ��� ������ �����Ѵ�. ���� �¿�°ͱ���
						
						int current_floor = data_208.elevator[0].current_floor;
						
						int upper = current_floor, low = current_floor;
						
						while(isPeople==false)
						{
							if(data_208.front[0][upper-1].people_num!=0)
							{
								
								if(data_208.front[0][upper-1].moving == true)
								{
									data_208.elevator[0].moving = 1; // ���
								}
								else
								{
									data_208.elevator[0].moving = -1; // �ϰ�
								}
								data_208.elevator[0].current_floor = upper; // �� ������ �̵��ϰ�
								
								try
								{
									Thread.sleep(2000); // 2�� ����
								}
								catch(Exception e)
								{}
								
								data_208.elevator[0].inner_people = data_208.front[0][upper-1].people_num; // ����� �¿��.
								
								if(data_208.elevator[0].inner_people !=0)
									isPeople = true;
								
							}
							else if(data_208.front[0][low-1].people_num!=0)
							{
								
								if(data_208.front[0][low-1].moving == true)
								{
									data_208.elevator[0].moving = 1; // ���
								}
								else
								{
									data_208.elevator[0].moving = -1; // �ϰ�
								}
								data_208.elevator[0].current_floor = low; // �� ������ �̵��ϰ�
								
								try
								{
									Thread.sleep(2000); // 2�� ����
								}
								catch(Exception e)
								{}
								
								data_208.elevator[0].inner_people = data_208.front[0][low-1].people_num; // ����� �¿��.
								
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
						// �����̰�, ����� ������ ���� �´��� Ȯ���ϰ�, ������ , �¿�� ������Ʈ����
						// ������ ���� 1���̰ų� 6���̸�, �� ������ �ϸ�ȴ�. �׸��� �ʱ�ȭ
						// ���������Ѵ�. 
						
						if(data_208.elevator[0].moving==1) // ���
						{
							data_208.elevator[0].current_floor++; // ���� �ö󰡰�
							
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
								// �׷��� �ʴٸ�
								int removePeople = (int) (Math.random() * (data_208.elevator[0].inner_people+1));
								
								data_208.elevator[0].inner_people -= removePeople; // �׸�ŭ ������
								
								if(data_208.elevator[0].isEmpty()==true)
								{
									// ����� ����ִٸ�
									data_208.elevator[0].moving=0;
									data_208.elevator[0].inner_people = 0;
									data_208.elevator[0].current_wiegth = 0;
								}
								else
								{
									// �տ� ��ٸ��� ����� ���������� ����� ������ Ȯ���Ѵ�
									if(data_208.front[0][data_208.elevator[0].current_floor-1].moving == true)
									{
										if(data_208.front[0][data_208.elevator[0].current_floor-1].people_num > (15-data_208.elevator[0].inner_people))
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num-=(15-data_208.elevator[0].inner_people);
										}
										else
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num = 0;
											
											// �� �¿�� �ִٸ� 0���� �׸��� check = false��
											data_208.front[0][data_208.elevator[0].current_floor-1].check = false;
										}
									}
								}
							}
						}
						else if(data_208.elevator[0].moving==-1) // ���
						{
							data_208.elevator[0].current_floor--; // ���� �ö󰡰�
							
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
								// �׷��� �ʴٸ�
								int removePeople = (int) (Math.random() * (data_208.elevator[0].inner_people+1));
								
								data_208.elevator[0].inner_people -= removePeople; // �׸�ŭ ������
								
								if(data_208.elevator[0].isEmpty()==true)
								{
									// ����� ����ִٸ�
									data_208.elevator[0].moving=0;
									data_208.elevator[0].inner_people = 0;
									data_208.elevator[0].current_wiegth = 0;
								}
								else
								{
									// �տ� ��ٸ��� ����� ���������� ����� ������ Ȯ���Ѵ�
									if(data_208.front[0][data_208.elevator[0].current_floor-1].moving == false)
									{
										if(data_208.front[0][data_208.elevator[0].current_floor-1].people_num > (15-data_208.elevator[0].inner_people))
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num-=(15-data_208.elevator[0].inner_people);
										}
										else
										{
											data_208.front[0][data_208.elevator[0].current_floor-1].people_num = 0;
											
											// �� �¿�� �ִٸ� 0���� �׸��� check = false��
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
