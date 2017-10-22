package com.Dusan.Capstone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BuildingData {

	public Elevator[] elevator = null;
	
	public String building_name = null;
	
	public int low_floor;
	public int high_floor;
	
	public People front[][]= null;
	
	public int num_elev = 0;
	
	public PeopleThread [][] peopleThread = null;
	public ElevThread [] elevThread = null;
	// �� �κ� �߰��Ͽ��� �Ѵ�. 
	
	BuildingData()
	{
		
	}
	
	BuildingData(int num)
	{
		// num�� ������������ ����
		
		elevator = new Elevator[num]; // num������ŭ ���������� �迭�� �����Ѵ�.
		
		// ����ٰ� ������ �迭�� ���ο� ��ü�� �� �����ؾ��Ѵ�. 
	}
	
	BuildingData(int num, String name)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name); // �� ������ �̸��� �����Ѵ�.
		
		for(int i=0;i<num;i++)
		{
			elevator[i] = new Elevator(1,6);
		}
	}
	
	BuildingData(int num, String name,int low, int high)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name);
		
		
		for(int i=0;i<num;i++)
		{
			elevator[i] = new Elevator(1,6);
		}
		
		this.low_floor = low;
		this.high_floor = high;
		
		
		front = new People[num][high-low+1];
		// front �����͸� �ʱ�ȭ �Ѵ�.
		
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<high-low+1;j++)
			{
			
				front[i][j] = new People();
				front[i][j].floor = j+1;
				System.out.println(front[i][j].floor + " ���Դϴ�");
			}
		}
		
	}
	
	public void SimulStart() throws InterruptedException
	{
		// �̺κ� ä���־���Ѵ�. 
		peopleThread = new PeopleThread[2][6];
			
		for(int j=0;j<2;j++)
		{
			for(int i=0;i<6;i++)
			{
				peopleThread[j][i] = new PeopleThread(j+1,i+1,front[j][i]);
				peopleThread[j][i].start();
			}
		}
					
	
		elevThread = new ElevThread[2];
		
		for(int i=0;i<2;i++)
		{
			elevThread[i] = new ElevThread(i+1,front[i],elevator[i]);
			elevThread[i].start();
		}
	
		
		
		Thread writeThread = new Thread(){
			public void run()
			{
				
				// ���⼭�� ��Ʈ��ũ ������� �����;� �Ѵ�.
					while(true)
					{
					
						String [][] front_String = new String[2][6];
						
						for(int i=0;i<2;i++)
						{
							for(int j=0;j<6;j++)
							{
								front_String[i][j] = String.valueOf(front[i][j].people_num + "/" + front[i][j].moving);
								//System.out.println("�̾��� �ٺ� = " + front[i][j].people_num);
							}
						}
						
						String inputuse = "http://35.201.215.220/write_front.php?";
						
						inputuse = inputuse + "f1=" + front_String[0][0] + "&f2="  + front_String[0][1] + "&f3="  +front_String[0][2] +"&f4="  +front_String[0][3] +"&f5="  +
						front_String[0][4] +"&f6="  +front_String[0][5] + "&elev=0";
								
						//System.out.println(inputuse + "��ö õ��");
					
						SendHttp(inputuse);
						
						// ���⼭�� ������ ���������� ���� �ο��� �����ִ�. 
						inputuse = "http://35.201.215.220/write_front.php?";
						
						inputuse = inputuse + "f1=" + front_String[1][0] + "&f2="  + front_String[1][1] + "&f3="  +front_String[1][2] +"&f4="  +front_String[1][3] +"&f5="  +
								front_String[1][4] +"&f6="  +front_String[1][5] + "&elev=1";
						
						SendHttp(inputuse);
						
						
						// ���⼭�� ������Ƽ�� ���� �ο��� ����Ѵ�.  
						inputuse = "http://35.201.215.220/write_state.php?";
						
						inputuse = inputuse + "num=" + "01" + "&floor="  + String.valueOf(elevator[0].current_floor) + "&full="+ "15" +"&current="  +String.valueOf(elevator[0].inner_people) +
								"&moving="  + String.valueOf(elevator[0].moving);
						
						
				
						SendHttp(inputuse);
						
						inputuse = "http://35.201.215.220/write_state.php?";
						
						inputuse = inputuse + "num=" + "02" + "&floor="  + String.valueOf(elevator[1].current_floor) + "&full="+ "15" +"&current="  +String.valueOf(elevator[1].inner_people) +
								"&moving="  + String.valueOf(elevator[1].moving);
						
						
				
						SendHttp(inputuse);
						
						try
						{
							this.sleep(300);
						}catch(Exception e)
						{}
					}
				
			}
			
			public void SendHttp(String input_url)
			{
				try
				{
			String str = URLEncoder.encode("�ѱ�","UTF-8");
			//String url = "http://35.201.215.220/write_front?.php";
			String url = new String(input_url);
			URL _url = new URL(url);
			
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(false);
			
			conn.setUseCaches(false);
			
			conn.setReadTimeout(20000);
			
			conn.setRequestMethod("GET");
			
			
			StringBuffer sb = new StringBuffer();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			for(;;)
			{
				String line = br.readLine();
				
				if(line == null)
					break;
				
				sb.append(line + "\n");
			}
			
			
			br.close();
			
			conn.disconnect();
			
			String getXml = sb.toString();
			
			//System.out.println(getXml);
			
			}catch(Exception e){}
			}
		};
		
		writeThread.start();
		}
				
			
		}

	
	
	class PeopleThread extends Thread{

		int p_elev_num;
		int p_floor;
		
		People p_front= null;
		
		PeopleThread(int elev, int floor, People front)
		{
			this.p_elev_num = elev;
			this.p_floor = floor;
			this.p_front = front;
		}
		
		public void run()
		{
			while(true)
			{
				if(p_front.check==false) // ������������Ͱ� ���ٸ�
				{
					p_front.setPeople(true,0);
					System.out.println("aaaaa" + " " + p_floor);
					
					if(p_floor==1 && (p_front.people_num !=0)) // 1���̶��, ��¸� ����
						p_front.moving = 1;
					
					if(p_floor==6 && (p_front.people_num !=0)) // 1���̶��, ��¸� ����
						p_front.moving = -1;
					
					//���鵵�� �Ѵ�. 								
					//System.out.println((p_elev_num) +"��° ���������� " + (p_floor) + "���� " + p_front.people_num + "���� �����");
				}					
				// �����ð��� ��ٸ����� �Ѵ�. 0~1��					
				int randTime = (int)(Math.random() * 3) + 3;
				
				try
				{
					Thread.sleep(randTime*1000);
					// 3~5��
				}
				catch(Exception e)
				{}
			}
		}
	}
	
	class ElevThread extends Thread{

		int e_elev_num;
		int e_floor;
		
		People [] e_front = null;
		Elevator e_elevator = null;
		
		ElevThread(int elev_num, People [] front, Elevator elev)
		{
			this.e_elev_num = elev_num;
			this.e_floor = 1;
			this.e_front = front;
			this.e_elevator = elev;
		}
		
		public void run()
		{
			while(true)
			{
				if(e_elevator.isEmpty() == true)
				{
					// ���������, ���� ����� ������ �̵��ؾ��Ѵ�. 
					boolean isPeople = false;
					
					int current_floor = e_elevator.current_floor;
					
					int upper = current_floor+1, low = current_floor-1;
					
					if(upper>6)
						upper = 6;
					if(low<1)
						low = 1;
					
					while(isPeople == false)
					{

						if(e_elevator.moving == 1) // ������̾��ٸ�
						{
							for(int i=current_floor;i<=6;i++)
							{
								if(e_front[i-1].moving == 1 && e_front[i-1].people_num!=0) // �� ���� ������� ��ǥ�� �����, �׸���  0�� �ƴ϶��
								{
									// �ű�� ������ �߰��� �����ϰ�.
									try
									{
										Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
									}
									catch(Exception e)
									{}
									
									e_elevator.inner_people = e_front[i-1].people_num;
									e_front[i-1].setPeople(false, 0);
									e_front[i-1].moving = 0;
									
									if(e_elevator.inner_people != 0)
										isPeople = true;
									
									break;
								}
							}
							
							
							
						
						}
						else if(e_elevator.moving == -1) // �ϰ����̾��ٸ�
						{
							for(int i=current_floor;i>=1;i--)
							{
								if(e_front[i-1].moving == -1 && e_front[i-1].people_num!=0) // �� ���� ������� ��ǥ�� �����, �׸���  0�� �ƴ϶��
								{
									// �ű�� ������ �߰��� �����ϰ�.
									try
									{
										Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
									}
									catch(Exception e)
									{}
									
									e_elevator.inner_people = e_front[i-1].people_num;
									e_front[i-1].setPeople(false, 0);
									e_front[i-1].moving = 0;
									
									if(e_elevator.inner_people != 0)
										isPeople = true;
									
									break;
								}
							}
							
							
						}
						else if(e_elevator.moving == 0)
						{
							// �����ִ� �����̶�� , 1���� �ִ� ����, Ȥ�� 6���� �ִ� ����
							if(e_elevator.current_floor ==1) // 1���̶��
							{
								for(int i=1;i<=6;i++) // �ݴ�� ���� �հ����� Ž���ؾ߰ڴ�.
								{
									if( e_front[i-1].moving == 1 && e_front[i-1].people_num!=0) // �� ���� ������� ��ǥ�� �����, �׸���  0�� �ƴ϶��
									{
										// �ű�� ������ �߰��� �����ϰ�.
										
										e_elevator.moving = e_front[i-1].moving;
										// �� �������� ���
										try
										{
											Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
										}
										catch(Exception e)
										{}
										
										for(int j =1;j<=i;j++)
										{
											e_elevator.current_floor++; // ���� ��� �ø���
											try
											{
												Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
											}
											catch(Exception e)
											{}
										}
										
										e_elevator.inner_people = e_front[i-1].people_num;
										e_front[i-1].setPeople(false, 0);
										e_front[i-1].moving = 0;
										
										if(e_elevator.inner_people != 0)
											isPeople = true;
										
										break;
									}
								}
							}
							else if(e_elevator.current_floor == 6) // 6���̶��
							{
								for(int i=6;i>=1;i--) // �ݴ�� ���� �հ����� Ž���ؾ߰ڴ�.
								{
									if( e_front[i-1].moving == -1 && e_front[i-1].people_num!=0) // �� ���� ������� ��ǥ�� �����, �׸���  0�� �ƴ϶��
									{
										// �ű�� ������ �߰��� �����ϰ�.
										
										e_elevator.moving = e_front[i-1].moving;
										// �� �������� ���
										try
										{
											Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
										}
										catch(Exception e)
										{}
										
										for(int j =6;j>=i;j--)
										{
											e_elevator.current_floor--; // ���� ��� �ø���
											try
											{
												Thread.sleep(2000); // 2�� ���� -> ����¿�µ� ��� �ð�
											}
											catch(Exception e)
											{}
										}
										
										e_elevator.inner_people = e_front[i-1].people_num;
										e_front[i-1].setPeople(false, 0);
										e_front[i-1].moving = 0;
										
										if(e_elevator.inner_people != 0)
											isPeople = true;
										
										break;
									}
								}
							}
						}
						
						
						// �� ������ ��ġ������, �¿� ����� ���ٸ�. �� 3������ ������� �����µ� 4 5 6���� �� �ϰ��̾��ٸ�
						if(e_elevator.isEmpty() == true) // ����ִٸ�
						{
							if(e_front[e_elevator.current_floor-1].people_num!=0) // ���� ���� 0�� �ƴ϶��, 
							{
								e_elevator.inner_people = e_front[e_elevator.current_floor-1].people_num;
								e_elevator.moving = e_front[e_elevator.current_floor-1].moving;
								
								e_front[e_elevator.current_floor-1].setPeople(false, 0);
								e_front[e_elevator.current_floor-1].moving = 0;
								
								if(e_elevator.inner_people != 0)
									isPeople = true;
							}
							else
							{
								if(e_front[upper-1].people_num!=0) // ���� ���� 0�� �ƴ϶��, 
								{
									e_elevator.inner_people = e_front[upper-1].people_num;
									e_elevator.moving = e_front[upper-1].moving;
									
									e_front[upper-1].setPeople(false, 0);
									e_front[upper-1].moving = 0;
									
									if(e_elevator.inner_people != 0)
										isPeople = true;
								}
								else if(e_front[low-1].people_num!=0) // ���� ���� 0�� �ƴ϶��, 
								{
									e_elevator.inner_people = e_front[low-1].people_num;
									e_elevator.moving = e_front[low-1].moving;
									
									e_front[low-1].setPeople(false, 0);
									e_front[low-1].moving = 0;
									
									if(e_elevator.inner_people != 0)
										isPeople = true;
								}
								
								
								if(upper>6)
									upper = 6;
								if(low<1)
									low = 1;
							}
						}	
							
						//}
						
					}
				}
				else
				{
					// �� �ִٸ�, ���� �� �⼭ �������� �Ѵ�. 
					// �����̰�, ����� ������ ���� �´��� Ȯ���ϰ�, ������ , �¿�� ������Ʈ����
					// ������ ���� 1���̰ų� 6���̸�, �� ������ �ϸ�ȴ�. �׸��� �ʱ�ȭ
					// ���������Ѵ�. 
					
					if(e_elevator.moving == 1) // ������̶��
					{
						e_elevator.current_floor++; // ���� �ö󰡰�.
						
						try
						{
							Thread.sleep(1500);
						}
						catch(Exception e)
						{}
						
						if(e_elevator.current_floor==6) // ����� ���̶��
						{
							e_elevator.moving = 0;
							e_elevator.inner_people = 0; // �� ������
						}
						else
						{
							int removePeople = (int) (Math.random()*(e_elevator.inner_people+1));
							
							e_elevator.inner_people -= removePeople; // �׸�ŭ ������
							
							if(e_elevator.isEmpty() == true)
							{
								//e_elevator.moving = 0; -> ������������ �����ִ� ������������� ��Ƴ���.
								e_elevator.inner_people = 0;
								// ���⵵ ����� ä�����Ѵ�. -> �̰� ������ �ٽ� ä��Եȴ�.
							}
							else
							{
								// �������� ����� ��� �����ʴٸ�
								if(e_front[e_elevator.current_floor-1].moving == e_elevator.moving) // �̵������� ���ٸ�
								{
									int maxleft = 15 - e_elevator.inner_people; // 15���� ���� �ο��� ����ȴ�. 
									
									if(maxleft < e_front[e_elevator.current_floor-1].people_num) // ���� �ڸ� ������ ����� �������
									{
										e_front[e_elevator.current_floor-1].setPeople(false, e_front[e_elevator.current_floor-1].people_num-maxleft);
										// �ϴ� �ִ��� ����.
									}
									else
									{
										e_front[e_elevator.current_floor-1].setPeople(false, 0);
										e_front[e_elevator.current_floor-1].check = false;
										e_front[e_elevator.current_floor-1].moving = 0; // �ȿ�����.
									}
								}
							}
						}
						
					}
					else if(e_elevator.moving == -1) // ������̶��
					{
						e_elevator.current_floor--; // ���� �ö󰡰�.
						
						try
						{
							Thread.sleep(1500);
						}
						catch(Exception e)
						{}
						
						if(e_elevator.current_floor==1) // ����� ���̶��
						{
							e_elevator.moving = 0;
							e_elevator.inner_people = 0; // �� ������
						}
						else
						{
							int removePeople = (int) (Math.random()*(e_elevator.inner_people+1));
							
							e_elevator.inner_people -= removePeople; // �׸�ŭ ������
							
							if(e_elevator.isEmpty() == true)
							{
								//e_elevator.moving = 0;
								e_elevator.inner_people = 0;
								// ���⵵ ����� ä�����Ѵ�. -> �̰� ������ �ٽ� ä��Եȴ�.
							}
							else
							{
								// �������� ����� ��� �����ʴٸ�
								if(e_front[e_elevator.current_floor-1].moving == e_elevator.moving) // �̵������� ���ٸ�
								{
									int maxleft = 15 - e_elevator.inner_people; // 15���� ���� �ο��� ����ȴ�. 
									
									if(maxleft < e_front[e_elevator.current_floor-1].people_num) // ���� �ڸ� ������ ����� �������
									{
										e_front[e_elevator.current_floor-1].setPeople(false, e_front[e_elevator.current_floor-1].people_num-maxleft);
										// �ϴ� �ִ��� ����.
									}
									else
									{
										e_front[e_elevator.current_floor-1].setPeople(false, 0);
										e_front[e_elevator.current_floor-1].check = false;
										e_front[e_elevator.current_floor-1].moving = 0; // �ȿ�����.
									}
								}
							}
						}
						
					}
					
				}
			}
		}
	}




