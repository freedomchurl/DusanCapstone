package com.Dusan.Capstone;

public class BuildingData {

	public Elevator[] elevator = null;
	
	public String building_name = null;
	
	public int low_floor;
	public int high_floor;
	
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
		
		
	}
	
	BuildingData(int num, String name,int low, int high)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name);
		
		this.low_floor = low;
		this.high_floor = high;
		
		
	}
}
