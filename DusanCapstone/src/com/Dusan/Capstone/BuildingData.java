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
			}
		}
		
	}
	
	public void SimulStart()
	{
		// 이부분 채워넣어야한다. 
	}
}
