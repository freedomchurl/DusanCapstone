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
					
					if(p_floor==1 && (p_front.people_num !=0)) // 1���̶��, ��¸� ����
						p_front.moving = 1;
					
					if(p_floor==6 && (p_front.people_num !=0)) // 1���̶��, ��¸� ����
						p_front.moving = -1;
					
					//���鵵�� �Ѵ�. 								
					System.out.println((p_elev_num) +"��° ���������� " + (p_floor) + "���� " + p_front.people_num + "���� �����");
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
							for(int i=current_floor;i>=1;i++)
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
}



