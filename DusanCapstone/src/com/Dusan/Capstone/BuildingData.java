package com.Dusan.Capstone;

public class BuildingData {

	public Elevator[] elevator = null;
	
	public String building_name = null;
	
	public int low_floor;
	public int high_floor;
	
	public People front[][]= null;
	
	public int num_elev = 0;
	
	public PeopleThread [][] peopleThread = null;
	public ElevThread [] elevThread = null;
	// 이 부분 추가하여야 한다. 
	
	BuildingData()
	{
		
	}
	
	BuildingData(int num)
	{
		// num은 엘리베이터의 개수
		
		elevator = new Elevator[num]; // num개수만큼 엘리베이터 배열을 생성한다.
		
		// 여기다가 각각의 배열에 새로운 객체를 또 생성해야한다. 
	}
	
	BuildingData(int num, String name)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name); // 이 빌딩의 이름을 저장한다.
		
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
		// front 데이터를 초기화 한다.
		
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<high-low+1;j++)
			{
				front[i][j] = new People();
				front[i][j].floor = j+1;
			}
		}
		
	}
	
	public void SimulStart() throws InterruptedException
	{
		// 이부분 채워넣어야한다. 
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
				if(p_front.check==false) // 만들어진데이터가 없다면
				{
					p_front.setPeople(true,0);
					
					if(p_floor==1 && (p_front.people_num !=0)) // 1층이라면, 상승만 가능
						p_front.moving = 1;
					
					if(p_floor==6 && (p_front.people_num !=0)) // 1층이라면, 상승만 가능
						p_front.moving = -1;
					
					//만들도록 한다. 								
					System.out.println((p_elev_num) +"번째 엘리베이터 " + (p_floor) + "층에 " + p_front.people_num + "명이 생겼다");
				}					
				// 랜덤시간을 기다리도록 한다. 0~1초					
				int randTime = (int)(Math.random() * 3) + 3;
				
				try
				{
					Thread.sleep(randTime*1000);
					// 3~5초
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
					// 비어있으면, 가장 가까운 곳으로 이동해야한다. 
					boolean isPeople = false;
					
					int current_floor = e_elevator.current_floor;
					
					int upper = current_floor+1, low = current_floor-1;
					
					if(upper>6)
						upper = 6;
					if(low<1)
						low = 1;
					
					while(isPeople == false)
					{

						if(e_elevator.moving == 1) // 상승중이었다면
						{
							for(int i=current_floor;i<=6;i++)
							{
								if(e_front[i-1].moving == 1 && e_front[i-1].people_num!=0) // 그 앞의 사람들의 목표도 위라면, 그리고  0이 아니라면
								{
									// 거기로 가야해 중간은 무시하고.
									try
									{
										Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
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
						else if(e_elevator.moving == -1) // 하강중이었다면
						{
							for(int i=current_floor;i>=1;i++)
							{
								if(e_front[i-1].moving == -1 && e_front[i-1].people_num!=0) // 그 앞의 사람들의 목표도 위라면, 그리고  0이 아니라면
								{
									// 거기로 가야해 중간은 무시하고.
									try
									{
										Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
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
							// 멈춰있던 과정이라면 , 1층에 있던 느낌, 혹은 6층에 있는 느낌
							if(e_elevator.current_floor ==1) // 1층이라면
							{
								for(int i=1;i<=6;i++) // 반대로 가장 먼곳부터 탐색해야겠다.
								{
									if( e_front[i-1].moving == 1 && e_front[i-1].people_num!=0) // 그 앞의 사람들의 목표도 위라면, 그리고  0이 아니라면
									{
										// 거기로 가야해 중간은 무시하고.
										
										e_elevator.moving = e_front[i-1].moving;
										// 그 움직임을 얻고
										try
										{
											Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
										}
										catch(Exception e)
										{}
										
										for(int j =1;j<=i;j++)
										{
											e_elevator.current_floor++; // 층을 계속 올리고
											try
											{
												Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
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
							else if(e_elevator.current_floor == 6) // 6층이라면
							{
								for(int i=6;i>=1;i--) // 반대로 가장 먼곳부터 탐색해야겠다.
								{
									if( e_front[i-1].moving == -1 && e_front[i-1].people_num!=0) // 그 앞의 사람들의 목표도 위라면, 그리고  0이 아니라면
									{
										// 거기로 가야해 중간은 무시하고.
										
										e_elevator.moving = e_front[i-1].moving;
										// 그 움직임을 얻고
										try
										{
											Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
										}
										catch(Exception e)
										{}
										
										for(int j =6;j>=i;j--)
										{
											e_elevator.current_floor--; // 층을 계속 올리고
											try
											{
												Thread.sleep(2000); // 2초 쉬고 -> 사람태우는데 드는 시간
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
						
						
						// 이 과정을 거치고나서도, 태운 사람이 없다면. 즉 3층에서 상승으로 끝낫는데 4 5 6층이 다 하강이었다면
						if(e_elevator.isEmpty() == true) // 비어있다면
						{
							if(e_front[e_elevator.current_floor-1].people_num!=0) // 현재 층이 0이 아니라면, 
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
								if(e_front[upper-1].people_num!=0) // 현재 층이 0이 아니라면, 
								{
									e_elevator.inner_people = e_front[upper-1].people_num;
									e_elevator.moving = e_front[upper-1].moving;
									
									e_front[upper-1].setPeople(false, 0);
									e_front[upper-1].moving = 0;
									
									if(e_elevator.inner_people != 0)
										isPeople = true;
								}
								else if(e_front[low-1].people_num!=0) // 현재 층이 0이 아니라면, 
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
					// 차 있다면, 이제 여 기서 움직여야 한다. 
					// 움직이고, 사람의 방향이 나랑 맞는지 확인하고, 내리고 , 태우고 업데이트까지
					// 도착한 층이 1층이거나 6층이면, 다 내리게 하면된다. 그리고 초기화
					// 움직여야한다. 
					
					if(e_elevator.moving == 1) // 상승중이라면
					{
						e_elevator.current_floor++; // 한층 올라가고.
						
						try
						{
							Thread.sleep(1500);
						}
						catch(Exception e)
						{}
						
						if(e_elevator.current_floor==6) // 꼭대기 층이라면
						{
							e_elevator.moving = 0;
							e_elevator.inner_people = 0; // 다 내리고
						}
						else
						{
							int removePeople = (int) (Math.random()*(e_elevator.inner_people+1));
							
							e_elevator.inner_people -= removePeople; // 그만큼 내리고
							
							if(e_elevator.isEmpty() == true)
							{
								//e_elevator.moving = 0; -> 엘리베이터의 가고있던 운행방향정보는 담아놓자.
								e_elevator.inner_people = 0;
								// 여기도 사람을 채워야한다. -> 이건 위에서 다시 채우게된다.
							}
							else
							{
								// 내렷지만 사람이 비어 있지않다면
								if(e_front[e_elevator.current_floor-1].moving == e_elevator.moving) // 이동방향이 같다면
								{
									int maxleft = 15 - e_elevator.inner_people; // 15에서 현재 인원을 빼면된다. 
									
									if(maxleft < e_front[e_elevator.current_floor-1].people_num) // 남은 자리 수보다 사람이 많을경우
									{
										e_front[e_elevator.current_floor-1].setPeople(false, e_front[e_elevator.current_floor-1].people_num-maxleft);
										// 일단 최대한 뺀다.
									}
									else
									{
										e_front[e_elevator.current_floor-1].setPeople(false, 0);
										e_front[e_elevator.current_floor-1].check = false;
										e_front[e_elevator.current_floor-1].moving = 0; // 안움직임.
									}
								}
							}
						}
						
					}
					else if(e_elevator.moving == -1) // 상승중이라면
					{
						e_elevator.current_floor--; // 한층 올라가고.
						
						try
						{
							Thread.sleep(1500);
						}
						catch(Exception e)
						{}
						
						if(e_elevator.current_floor==1) // 꼭대기 층이라면
						{
							e_elevator.moving = 0;
							e_elevator.inner_people = 0; // 다 내리고
						}
						else
						{
							int removePeople = (int) (Math.random()*(e_elevator.inner_people+1));
							
							e_elevator.inner_people -= removePeople; // 그만큼 내리고
							
							if(e_elevator.isEmpty() == true)
							{
								//e_elevator.moving = 0;
								e_elevator.inner_people = 0;
								// 여기도 사람을 채워야한다. -> 이건 위에서 다시 채우게된다.
							}
							else
							{
								// 내렷지만 사람이 비어 있지않다면
								if(e_front[e_elevator.current_floor-1].moving == e_elevator.moving) // 이동방향이 같다면
								{
									int maxleft = 15 - e_elevator.inner_people; // 15에서 현재 인원을 빼면된다. 
									
									if(maxleft < e_front[e_elevator.current_floor-1].people_num) // 남은 자리 수보다 사람이 많을경우
									{
										e_front[e_elevator.current_floor-1].setPeople(false, e_front[e_elevator.current_floor-1].people_num-maxleft);
										// 일단 최대한 뺀다.
									}
									else
									{
										e_front[e_elevator.current_floor-1].setPeople(false, 0);
										e_front[e_elevator.current_floor-1].check = false;
										e_front[e_elevator.current_floor-1].moving = 0; // 안움직임.
									}
								}
							}
						}
						
					}
					
				}
			}
		}
	}
}



