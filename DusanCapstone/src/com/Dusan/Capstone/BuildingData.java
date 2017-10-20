package com.Dusan.Capstone;

public class BuildingData {

	public Elevator[] elevator = null;
	
	public String building_name = null;
	
	public int low_floor;
	public int high_floor;
	
	public People front[][]= null;
	
	public int num_elev = 0;
	
	public Thread peopleThread = null;
	public Thread elevThread = null;
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
			}
		}
		
	}
	
	public void SimulStart()
	{
		// �̺κ� ä���־���Ѵ�. 
	}
}
