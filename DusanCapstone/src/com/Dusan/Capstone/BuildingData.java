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
		// num�� ������������ ����
		
		elevator = new Elevator[num]; // num������ŭ ���������� �迭�� �����Ѵ�.
		
		// ����ٰ� ������ �迭�� ���ο� ��ü�� �� �����ؾ��Ѵ�. 
	}
	
	BuildingData(int num, String name)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name); // �� ������ �̸��� �����Ѵ�.
		
		
	}
	
	BuildingData(int num, String name,int low, int high)
	{
		elevator = new Elevator[num];
		
		this.building_name = new String(name);
		
		this.low_floor = low;
		this.high_floor = high;
		
		
	}
}
